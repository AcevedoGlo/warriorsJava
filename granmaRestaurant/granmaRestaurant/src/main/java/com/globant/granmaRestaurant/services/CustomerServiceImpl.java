package com.globant.granmaRestaurant.services;

import com.globant.granmaRestaurant.exception.custonException.CustomException;
import com.globant.granmaRestaurant.exception.enums.ExceptionCode;
import com.globant.granmaRestaurant.mapper.IMapper.ICustomerMapper;
import com.globant.granmaRestaurant.model.DTO.CustomerDTO;
import com.globant.granmaRestaurant.repositories.CustomerRepository;
import com.globant.granmaRestaurant.model.entity.CustomerEntity;
import com.globant.granmaRestaurant.services.IServices.ICustomerService;
import com.globant.granmaRestaurant.services.validators.CustomerValidator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ICustomerMapper customerMapper;

    @Autowired
    private CustomerValidator customerValidator;

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customerMapper::customerConvertToDTO)
                .collect(Collectors.toList());
    }
    @Override
    public CustomerDTO getCustomer(String document) {
        customerValidator.validateDocument(document);
        return customerRepository.findByDocument(document)
                .map(customerMapper::customerConvertToDTO)
                .orElseThrow(() -> new CustomException(
                        ExceptionCode.CUSTOMER_NOT_FOUND,
                        LocalDateTime.now(),
                        HttpStatus.NOT_FOUND,
                        "Cliente con documento: " + document + " no encontrado."
                ));
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        customerValidator.validateCustomerData(customerDTO);
        customerRepository.findByDocument(customerDTO.getDocument())
                .ifPresent(existingCustomer -> {
                    throw new CustomException(
                            ExceptionCode.USER_ALREADY_EXISTS,
                            LocalDateTime.now(),
                            HttpStatus.CONFLICT,
                            "Cliente con documento: " + customerDTO.getDocument() + " ya existe."
                    );
                });
        CustomerEntity customerEntity = customerMapper.customerConvertToEntity(customerDTO);
        CustomerEntity savedEntity = customerRepository.save(customerEntity);
        return customerMapper.customerConvertToDTO(savedEntity);
    }


    @Override
    @Transactional
    public void deleteCustomer(String document) {
        customerValidator.validateDocument(document);
        Optional<CustomerEntity> existingCustomer = customerRepository.findByDocument(document);
        if (existingCustomer.isPresent()) {
            customerRepository.deleteByDocument(document);
        } else {
            throw new CustomException(
                    ExceptionCode.CUSTOMER_NOT_FOUND,
                    LocalDateTime.now(),
                    HttpStatus.NOT_FOUND,
                    "Cliente con documento: " + document + " no encontrado."
            );
        }
    }
    @Override
    public void updateCustomer(String document, CustomerDTO customerDTO) {
        Optional<CustomerEntity> existingCustomerOpt = customerRepository.findByDocument(document);
        if (existingCustomerOpt.isPresent()) {
            CustomerEntity existingCustomer = existingCustomerOpt.get();
            customerValidator.validateUpdateCustomerData(customerDTO, existingCustomer); // Validar datos y cambios

            existingCustomer.setName(customerDTO.getName());
            existingCustomer.setEmail(customerDTO.getEmail());
            existingCustomer.setPhone(customerDTO.getPhone());
            existingCustomer.setDeliveryAddress(customerDTO.getDeliveryAddress());

            customerRepository.save(existingCustomer);
        } else {
            throw new CustomException(
                    ExceptionCode.CUSTOMER_NOT_FOUND,
                    LocalDateTime.now(),
                    HttpStatus.NOT_FOUND,
                    "Cliente con documento: " + document + " no encontrado."
            );
        }
    }
}