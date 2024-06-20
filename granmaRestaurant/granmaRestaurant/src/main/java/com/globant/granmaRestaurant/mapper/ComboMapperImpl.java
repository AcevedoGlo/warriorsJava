package com.globant.granmaRestaurant.mapper;

import com.globant.granmaRestaurant.mapper.IMapper.IComboMapper;
import com.globant.granmaRestaurant.model.DTO.ComboDTO;
import com.globant.granmaRestaurant.model.entity.ComboEntity;
import com.globant.granmaRestaurant.model.enums.Category;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ComboMapperImpl implements IComboMapper {

    @Override
    public ComboDTO comboConvertToDTO(ComboEntity comboEntity){
        return new ComboDTO(comboEntity.getUuid(),comboEntity.getFantasyName(), comboEntity.getCategory(), comboEntity.getDescription(), comboEntity.getPrice(), comboEntity.getAvailable(),comboEntity.getActive());
    }

    @Override
    public ComboEntity comboConvertToEntity(ComboDTO comboDTO) {
        ComboEntity comboEntity = new ComboEntity();
        comboEntity.setUuid(UUID.randomUUID().toString()); // Generate a unique UUID
        comboEntity.setFantasyName(comboDTO.getFantasyName().toUpperCase());
        if (comboDTO.getCategory() != null) {
            comboEntity.setCategory(Category.valueOf(comboDTO.getCategory().name().toUpperCase()));
        } else {
            comboEntity.setCategory(null);
        }
        comboEntity.setDescription(comboDTO.getDescription());
        comboEntity.setPrice(comboDTO.getPrice());
        comboEntity.setAvailable(comboDTO.getAvailable());
        comboEntity.setActive(comboDTO.getActive());
        return comboEntity;
    }

}


