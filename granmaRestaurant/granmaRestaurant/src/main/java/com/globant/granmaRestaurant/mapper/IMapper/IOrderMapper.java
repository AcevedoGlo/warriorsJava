package com.globant.granmaRestaurant.mapper.IMapper;

import com.globant.granmaRestaurant.model.DTO.OrderCreationDTO;
import com.globant.granmaRestaurant.model.DTO.OrderDTO;
import com.globant.granmaRestaurant.model.entity.OrderEntity;

public interface IOrderMapper {
    OrderDTO orderConvertToDTO(OrderEntity orderEntity);
    OrderEntity orderConvertToEntity(OrderCreationDTO orderCreationDTO);
}