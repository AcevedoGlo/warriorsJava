package com.globant.granmaRestaurant.services.validators;

import com.globant.granmaRestaurant.exception.custonException.CustomException;
import com.globant.granmaRestaurant.exception.enums.ExceptionCode;
import com.globant.granmaRestaurant.model.DTO.ComboDTO;
import com.globant.granmaRestaurant.model.enums.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ComboValidator {

    public void validateComboData(ComboDTO comboDTO) {
        validateFields(comboDTO);
        validateFantasyName(comboDTO.getFantasyName());
        validateCategory(comboDTO.getCategory());
        validatePrice(comboDTO.getPrice());
    }



    public void validateFields(ComboDTO comboDTO) {
        if (comboDTO.getFantasyName() == null || comboDTO.getFantasyName().trim().isEmpty() ||
                comboDTO.getCategory() == null || comboDTO.getDescription() == null ||
                comboDTO.getPrice() == null || comboDTO.getAvailable() == null || comboDTO.getActive() == null) {
            throw new CustomException(
                    ExceptionCode.INCOMPLETE_OR_INCORRECT_INFORMATION,
                    LocalDateTime.now(),
                    HttpStatus.BAD_REQUEST,
                    "Todos los campos son obligatorios."
            );
        }
    }

    public void validateFantasyName(String fantasyName) {
        if (fantasyName == null || fantasyName.trim().isEmpty()) {
            throw new CustomException(
                    ExceptionCode.INCOMPLETE_OR_INCORRECT_INFORMATION,
                    LocalDateTime.now(),
                    HttpStatus.BAD_REQUEST,
                    "El nombre de fantasía es obligatorio."
            );
        }
    }

    public void validateCategory(Category category) {
        if (category == null) {
            throw new CustomException(
                    ExceptionCode.INCOMPLETE_OR_INCORRECT_INFORMATION,
                    LocalDateTime.now(),
                    HttpStatus.BAD_REQUEST,
                    "La categoría es incorrecta."
            );
        }
    }

   public void validatePrice(Double price) {
        if (price == null || price < 0) {
            throw new CustomException(
                    ExceptionCode.INCOMPLETE_OR_INCORRECT_INFORMATION,
                    LocalDateTime.now(),
                    HttpStatus.BAD_REQUEST,
                    "El precio no puede ser negativo."
            );
        }
    }
}