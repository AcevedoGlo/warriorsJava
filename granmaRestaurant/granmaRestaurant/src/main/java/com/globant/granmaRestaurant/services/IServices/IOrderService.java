package com.globant.granmaRestaurant.services.IServices;
import com.globant.granmaRestaurant.model.DTO.OrderCreationDTO;
import com.globant.granmaRestaurant.model.DTO.OrderDTO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IOrderService {
    OrderDTO createOrder(OrderCreationDTO orderCreationDTO);
    List<OrderDTO> getAllOrders();
    Optional<OrderDTO> getOrderByUuid(String uuid);
    OrderDTO updateDeliveryStatus(String uuid, LocalDateTime deliveredDateTime);
}