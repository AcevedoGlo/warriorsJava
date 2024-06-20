package com.globant.granmaRestaurant.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreationDTO {
    private String customerDocument;
    private String comboUuid;
    private Integer quantity;
    private String extraInformation;
    private Integer customerId;
    private Integer comboId;
    private Double comboPrice;


    }
