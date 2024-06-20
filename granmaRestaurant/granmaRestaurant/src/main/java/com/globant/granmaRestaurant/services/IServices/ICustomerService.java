package com.globant.granmaRestaurant.services.IServices;

import com.globant.granmaRestaurant.model.DTO.CustomerDTO;

import java.util.List;



public interface ICustomerService {
    CustomerDTO getCustomer(String document);
    List<CustomerDTO> getAllCustomers();
    CustomerDTO createCustomer(CustomerDTO customerDTO);
    void deleteCustomer(String document);
    void updateCustomer(String document, CustomerDTO customerDTO);
    List<CustomerDTO> getSortedCustomers(String sortBy, String order, String document, String name);
}