package com.globant.granmaRestaurant.model.DTO;

import com.globant.granmaRestaurant.model.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComboDTO {

    private Integer idCombo;
    private String uuid;
    private String fantasyName;
    private Category category;
    private String description;
    private Double price;
    private Boolean available;
    private Boolean active;

    public ComboDTO(String uuid, String fantasyName, Category category, String description, Double price, Boolean available, Boolean active) {
    }
}
