package com.globant.granmaRestaurant.services;

import com.globant.granmaRestaurant.exception.ComboNotFoundException;
import com.globant.granmaRestaurant.exception.CustomerNotFoundException;
import com.globant.granmaRestaurant.mapper.OrderMapperImpl;
import com.globant.granmaRestaurant.model.DTO.OrderCreationDTO;
import com.globant.granmaRestaurant.model.DTO.OrderDTO;
import com.globant.granmaRestaurant.model.entity.ComboEntity;
import com.globant.granmaRestaurant.model.entity.CustomerEntity;
import com.globant.granmaRestaurant.model.entity.OrderEntity;
import com.globant.granmaRestaurant.repositories.ComboRepository;
import com.globant.granmaRestaurant.repositories.CustomerRepository;
import com.globant.granmaRestaurant.repositories.OrderRepository;
import com.globant.granmaRestaurant.services.IServices.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ComboRepository comboRepository;

    @Autowired
    private OrderMapperImpl orderMapper;

    @Override
    public OrderDTO createOrder(OrderCreationDTO orderCreationDTO) {
        CustomerEntity customerEntity = CustomerExistance(orderCreationDTO.getCustomerDocument());
        ComboEntity comboEntity = ComboExistance(orderCreationDTO.getComboUuid());

        OrderEntity orderEntity = orderMapper.orderConvertToEntity(orderCreationDTO);

        Double subtotal = comboEntity.getPrice() * orderCreationDTO.getQuantity();
        Double vatTax = subtotal * 0.19;
        Double grandTotal = subtotal + vatTax;

        String uuid = UUID.randomUUID().toString();
        orderEntity.setUuid(uuid);
        orderEntity.setCreationDateTime(getCurrentTimestamp());
        orderEntity.setSubtotal(subtotal);
        orderEntity.setVatTax(vatTax);
        orderEntity.setGrandTotal(grandTotal);
        orderEntity.setDelivered(false);
        orderEntity.setDeliveredDate(null);

        orderEntity.setCustomer(customerEntity);
        orderEntity.setCombo(comboEntity);

        orderRepository.save(orderEntity);

        return orderMapper.orderConvertToDTO(orderEntity);
    }

    private CustomerEntity CustomerExistance(String document) {
        return customerRepository.findByDocument(document)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
    }

    private ComboEntity ComboExistance(String uuid) {
        return comboRepository.findByUuid(uuid)
                .orElseThrow(() -> new ComboNotFoundException("Combo not found"));
    }

    private Timestamp getCurrentTimestamp() {
        ZonedDateTime zonedDateTime = LocalDateTime.now().atZone(ZoneId.of("America/Bogota"));
        return Timestamp.from(zonedDateTime.toInstant());
    }



    @Override
    public List<OrderDTO> getAllOrders() {
        List<OrderEntity> orderEntities = orderRepository.findAll();
        return orderEntities.stream()
                .map(orderMapper::orderConvertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OrderDTO> getOrderByUuid(String uuid) {
        Optional<OrderEntity> optionalOrder = orderRepository.findByUuid(uuid);
        return optionalOrder.map(orderMapper::orderConvertToDTO);
    }

    @Override
    public OrderDTO updateDeliveryStatus(String uuid, LocalDateTime deliveredDateTime) {
        Optional<OrderEntity> optionalOrder = orderRepository.findByUuid(uuid);
        if (optionalOrder.isPresent()) {
            OrderEntity orderEntity = optionalOrder.get();
            orderEntity.setDelivered(true);
            ZonedDateTime zonedDateTime = deliveredDateTime.atZone(ZoneId.of("America/Bogota"));
            Timestamp deliveredTimestamp = Timestamp.from(zonedDateTime.toInstant());
            orderEntity.setDeliveredDate(deliveredTimestamp);

            OrderEntity updatedOrder = orderRepository.save(orderEntity);
            return orderMapper.orderConvertToDTO(updatedOrder);
        } else {
            return null;
        }
    }

    private Double calculateSubtotal(Double price, Integer quantity) {
        return price * quantity;
    }

    private Double calculateVATTax(Double subtotal) {
        return subtotal * 0.19;
    }
}