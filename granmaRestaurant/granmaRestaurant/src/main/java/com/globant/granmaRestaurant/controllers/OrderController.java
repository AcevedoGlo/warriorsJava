package com.globant.granmaRestaurant.controllers;

import com.globant.granmaRestaurant.exception.ComboNotFoundException;
import com.globant.granmaRestaurant.exception.CustomerNotFoundException;
import com.globant.granmaRestaurant.model.DTO.OrderCreationDTO;
import com.globant.granmaRestaurant.model.DTO.OrderDTO;
import com.globant.granmaRestaurant.services.IServices.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<Object> createOrder(@RequestBody OrderCreationDTO orderCreationDTO, String idCustomer, String idCombo) {
        try {
            OrderDTO createdOrder = orderService.createOrder(orderCreationDTO);
            return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
        } catch (CustomerNotFoundException | ComboNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while creating the order.");
        }
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Optional<OrderDTO>> getOrderByUuid(@PathVariable String uuid) {
        Optional<OrderDTO> order = orderService.getOrderByUuid(uuid);
        if (order.isPresent()) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PatchMapping("/{uuid}/delivered/{timestamp}")
    public ResponseEntity<OrderDTO> updateDeliveryStatus(
            @PathVariable String uuid,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timestamp
    ) {
        OrderDTO updatedOrder = orderService.updateDeliveryStatus(uuid, timestamp);
        if (updatedOrder != null) {
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @ExceptionHandler(ComboNotFoundException.class)
    public ResponseEntity<String> handleComboNotFoundException(ComboNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}