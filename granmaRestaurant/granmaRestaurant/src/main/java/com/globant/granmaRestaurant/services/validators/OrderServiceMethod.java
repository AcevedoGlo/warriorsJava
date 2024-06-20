package com.globant.granmaRestaurant.services.validators;


import com.globant.granmaRestaurant.exception.custonException.CustomException;
import com.globant.granmaRestaurant.exception.enums.ExceptionCode;
import com.globant.granmaRestaurant.model.entity.ComboEntity;
import com.globant.granmaRestaurant.model.entity.CustomerEntity;
import com.globant.granmaRestaurant.repositories.ComboRepository;
import com.globant.granmaRestaurant.repositories.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@Component
public class OrderServiceMethod {

    public CustomerEntity validateCustomerExistence(String document, CustomerRepository customerRepository) {
        return customerRepository.findByDocument(document)
                .orElseThrow(() -> new CustomException(ExceptionCode.CUSTOMER_NOT_FOUND, LocalDateTime.now(), HttpStatus.NOT_FOUND, "Customer not found"));
    }

    public ComboEntity validateComboExistence(String uuid, ComboRepository comboRepository) {
        return comboRepository.findByUuid(uuid)
                .orElseThrow(() -> new CustomException(ExceptionCode.COMBO_NOT_FOUND, LocalDateTime.now(), HttpStatus.NOT_FOUND, "Combo not found"));
    }

    public String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public Timestamp getCurrentTimestamp() {
        ZonedDateTime zonedDateTime = LocalDateTime.now().atZone(ZoneId.of("America/Bogota"));
        return Timestamp.from(zonedDateTime.toInstant());
    }

    public Timestamp convertToTimestamp(LocalDateTime localDateTime) {
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("America/Bogota"));
        return Timestamp.from(zonedDateTime.toInstant());
    }
}