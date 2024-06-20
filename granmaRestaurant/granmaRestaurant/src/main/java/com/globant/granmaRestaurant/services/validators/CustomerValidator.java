package com.globant.granmaRestaurant.services.validators;

import com.globant.granmaRestaurant.exception.custonException.CustomException;
import com.globant.granmaRestaurant.exception.enums.ExceptionCode;
import com.globant.granmaRestaurant.model.DTO.CustomerDTO;
import com.globant.granmaRestaurant.model.entity.CustomerEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

@Component
public class CustomerValidator {
    public void validateCustomerData(CustomerDTO customerDTO) {
        validateDocument(customerDTO.getDocument());
        validatePhone(customerDTO.getPhone());
        validateEmail(customerDTO.getEmail());
        validateName(customerDTO.getName());
        validateDeliveryAddress(customerDTO.getDeliveryAddress());
    }
    public void validateDocument(String document) {
        if (document == null || !Pattern.matches("^(CC|CE|P)-[0-9]{1,20}$", document)) {
            throw new CustomException(
                    ExceptionCode.INVALID_DOCUMENT,
                    LocalDateTime.now(),
                    HttpStatus.BAD_REQUEST,
                    "Formato de documento soportado: (CC-CE-P) (-) (1234) Ejemplo: CC-1000234576"

            );
        }
    }
    public void validatePhone(String phone) {
        if (phone == null || !Pattern.matches("^[0-9]{3}-[0-9]{7}$", phone)) {
            throw new CustomException(
                    ExceptionCode.INCOMPLETE_OR_INCORRECT_INFORMATION,
                    LocalDateTime.now(),
                    HttpStatus.BAD_REQUEST,
                    "Formato esperado: '312-4567890' "
            );
        }
    }
    public void validateEmail(String email) {
        if (email == null || !Pattern.matches("^[^@]+@[^@]+\\.[^@]+$", email)) {
            throw new CustomException(
                    ExceptionCode.INCOMPLETE_OR_INCORRECT_INFORMATION,
                    LocalDateTime.now(),
                    HttpStatus.BAD_REQUEST,
                    "Correo electrónico no es válido. Debe contener un '@' y un '.'."
            );
        }
    }
    public void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new CustomException(
                    ExceptionCode.INCOMPLETE_OR_INCORRECT_INFORMATION,
                    LocalDateTime.now(),
                    HttpStatus.BAD_REQUEST,
                    "El nombre es obligatorio."
            );
        }
    }
    public void validateDeliveryAddress(String deliveryAddress) {
        if (deliveryAddress == null || deliveryAddress.trim().isEmpty()) {
            throw new CustomException(
                    ExceptionCode.INCOMPLETE_OR_INCORRECT_INFORMATION,
                    LocalDateTime.now(),
                    HttpStatus.BAD_REQUEST,
                    "La dirección de entrega es obligatoria."
            );
        }
    }
    public void validateNoChanges(CustomerDTO customerDTO, CustomerEntity existingCustomer) {
        if (customerDTO.getName().equals(existingCustomer.getName()) &&
                customerDTO.getEmail().equals(existingCustomer.getEmail()) &&
                customerDTO.getPhone().equals(existingCustomer.getPhone()) &&
                customerDTO.getDeliveryAddress().equals(existingCustomer.getDeliveryAddress()) &&
                customerDTO.getDocument().equals(existingCustomer.getDocument())) {
            throw new CustomException(
                    ExceptionCode.NO_CHANGES,
                    LocalDateTime.now(),
                    HttpStatus.BAD_REQUEST,
                    "No se realizaron cambios en el cliente."
            );
        }
    }
    public void validateUpdateCustomerData(CustomerDTO customerDTO, CustomerEntity existingCustomer) {
        validateDocument(customerDTO.getDocument());
        validatePhone(customerDTO.getPhone());
        validateEmail(customerDTO.getEmail());
        validateName(customerDTO.getName());
        validateDeliveryAddress(customerDTO.getDeliveryAddress());
        validateNoChanges(customerDTO, existingCustomer);
    }
}