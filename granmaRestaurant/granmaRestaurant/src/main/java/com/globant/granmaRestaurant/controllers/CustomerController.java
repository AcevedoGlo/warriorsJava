package com.globant.granmaRestaurant.controllers;

import com.globant.granmaRestaurant.controllers.IControllerEndpoints.ICustomerPath;
import com.globant.granmaRestaurant.model.DTO.CustomerDTO;
import com.globant.granmaRestaurant.services.IServices.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ICustomerPath.URL_BASE)
public class CustomerController {

        @Autowired
        private ICustomerService customerService;

        @PostMapping(ICustomerPath.CREATE_CUSTOMER)
        public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerRequest) {
            CustomerDTO resultado = customerService.createCustomer(customerRequest);
            return new ResponseEntity<>(resultado, HttpStatus.CREATED);
        }

        @GetMapping(ICustomerPath.GET_LIST)
        public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
            List<CustomerDTO> customers = customerService.getAllCustomers();
            return new ResponseEntity<>(customers, HttpStatus.OK);
        }

        @GetMapping(ICustomerPath.GET_CUSTOMER)
        public ResponseEntity<CustomerDTO> getCustomer(@PathVariable String document) {
            CustomerDTO customerResponse = customerService.getCustomer(document);
            return new ResponseEntity<>(customerResponse, HttpStatus.OK);
        }

        @DeleteMapping(ICustomerPath.DELETE_CUSTOMER)
        public ResponseEntity<Void> deleteCustomer(@PathVariable String document) {
            customerService.deleteCustomer(document);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        @PutMapping(ICustomerPath.UPDATE_CUSTOMER)
        public ResponseEntity<Void> updateCustomer(@PathVariable String document, @RequestBody CustomerDTO customerRequest) {
            customerService.updateCustomer(document, customerRequest);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }