package com.globant.granmaRestaurant.mapper;



import com.globant.granmaRestaurant.mapper.IMapper.ICustomerMapper;
import com.globant.granmaRestaurant.model.DTO.CustomerDTO;
import com.globant.granmaRestaurant.model.entity.CustomerEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapperImpl implements ICustomerMapper {

    @Override
    public CustomerDTO customerConvertToDTO(CustomerEntity customerEntity) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setDocument(customerEntity.getDocument());
        customerDTO.setName(customerEntity.getName());
        customerDTO.setEmail(customerEntity.getEmail());
        customerDTO.setPhone(customerEntity.getPhone());
        customerDTO.setDeliveryAddress(customerEntity.getDeliveryAddress());
        customerDTO.setActive(customerEntity.getActive());
        return customerDTO;

    }
    @Override
    public CustomerEntity customerConvertToEntity(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setDocument(customerDTO.getDocument());
        customerEntity.setName(customerDTO.getName());
        customerEntity.setEmail(customerDTO.getEmail());
        customerEntity.setPhone(customerDTO.getPhone());
        customerEntity.setDeliveryAddress(customerDTO.getDeliveryAddress());
        return customerEntity;
    }

}