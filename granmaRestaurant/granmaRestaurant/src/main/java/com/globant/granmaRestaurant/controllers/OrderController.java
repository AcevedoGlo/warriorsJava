package com.globant.granmaRestaurant.controllers;

import com.globant.granmaRestaurant.controllers.IControllerEndpoints.IOrderPath;
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
@RequestMapping(IOrderPath.URL_BASE)
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @PostMapping(IOrderPath.CREATE_ORDER)
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderCreationDTO orderCreationDTO) {
        OrderDTO createdOrder = orderService.createOrder(orderCreationDTO);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping(IOrderPath.GET_ORDER)
    public ResponseEntity<OrderDTO> getOrderByUuid(@PathVariable String uuid) {
        Optional<OrderDTO> order = orderService.getOrderByUuid(uuid);
        return order.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping(IOrderPath.PATCH_ORDER)
    public ResponseEntity<OrderDTO> updateDeliveryStatus(
            @PathVariable String uuid,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timestamp
    ) {
        OrderDTO updatedOrder = orderService.updateDeliveryStatus(uuid, timestamp);
        return updatedOrder != null ? new ResponseEntity<>(updatedOrder, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}