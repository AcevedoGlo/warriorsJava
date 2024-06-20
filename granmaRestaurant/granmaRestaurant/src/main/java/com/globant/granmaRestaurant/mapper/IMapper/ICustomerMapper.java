package com.globant.granmaRestaurant.mapper.IMapper;

import com.globant.granmaRestaurant.model.DTO.CustomerDTO;
import com.globant.granmaRestaurant.model.entity.CustomerEntity;


public interface ICustomerMapper {

    CustomerDTO customerConvertToDTO(CustomerEntity customerEntity);

    CustomerEntity customerConvertToEntity (CustomerDTO customerDTO);

}

