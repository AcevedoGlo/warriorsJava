package com.globant.granmaRestaurant.model.DTO;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    private String document;
    private String name;
    private String email;
    private String phone;
    private String deliveryAddress;
    private Boolean active;

    public CustomerDTO(String document, String name, String email, String phone, String deliveryAddress) {
    }
}
