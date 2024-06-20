package com.globant.granmaRestaurant.services;

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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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

    @Autowired
    private com.globant.granmaRestaurant.services.util.OrderServiceMethod orderServiceMethod;

    @Override
    public OrderDTO createOrder(OrderCreationDTO orderCreationDTO) {
        CustomerEntity customerEntity = orderServiceMethod.validateCustomerExistence(orderCreationDTO.getCustomerDocument(), customerRepository);
        ComboEntity comboEntity = orderServiceMethod.validateComboExistence(orderCreationDTO.getComboUuid(), comboRepository);

        OrderEntity orderEntity = orderMapper.orderConvertToEntity(orderCreationDTO);


        Double subtotal = Math.round(comboEntity.getPrice() * orderCreationDTO.getQuantity() * 100.0) / 100.0;
        Double vatTax = Math.round(subtotal * 0.19 * 100.0) / 100.0;
        Double grandTotal = Math.round((subtotal + vatTax) * 100.0) / 100.0;

        orderEntity.setUuid(orderServiceMethod.generateUUID());
        orderEntity.setCreationDateTime(orderServiceMethod.getCurrentTimestamp());
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
            orderEntity.setDeliveredDate(orderServiceMethod.convertToTimestamp(deliveredDateTime));

            OrderEntity updatedOrder = orderRepository.save(orderEntity);
            return orderMapper.orderConvertToDTO(updatedOrder);
        } else {
            return null;
        }
    }
}