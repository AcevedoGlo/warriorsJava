package com.globant.granmaRestaurant.mapper;

import com.globant.granmaRestaurant.mapper.IMapper.IOrderMapper;
import com.globant.granmaRestaurant.model.DTO.OrderCreationDTO;
import com.globant.granmaRestaurant.model.DTO.OrderDTO;
import com.globant.granmaRestaurant.model.entity.OrderEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderMapperImpl implements IOrderMapper {
    @Override
    public OrderDTO orderConvertToDTO(OrderEntity orderEntity) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUuid(orderEntity.getUuid());
        orderDTO.setCustomerDocument(orderEntity.getCustomerDocument());
        orderDTO.setComboUuid(orderEntity.getComboUuid());
        orderDTO.setQuantity(orderEntity.getQuantity());
        orderDTO.setExtraInformation(orderEntity.getExtraInformation());
        orderDTO.setCreationDateTime(orderEntity.getCreationDateTime().toLocalDateTime());
        orderDTO.setSubtotal(orderEntity.getSubtotal());
        orderDTO.setVatTax(orderEntity.getVatTax());
        orderDTO.setGrandTotal(orderEntity.getGrandTotal());
        orderDTO.setDelivered(orderEntity.getDelivered());
        if (orderEntity.getDeliveredDate() != null) {
            orderDTO.setDeliveredDate(orderEntity.getDeliveredDate().toLocalDateTime());
        }

        return orderDTO;
    }

    @Override
    public OrderEntity orderConvertToEntity(OrderCreationDTO orderCreationDTO) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setQuantity(orderCreationDTO.getQuantity());
        orderEntity.setExtraInformation(orderCreationDTO.getExtraInformation());
        orderEntity.setCustomerDocument(orderCreationDTO.getCustomerDocument());
        orderEntity.setComboUuid(orderCreationDTO.getComboUuid());

        return orderEntity;
    }
}