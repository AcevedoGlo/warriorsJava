package com.globant.granmaRestaurant.mapper.IMapper;

import com.globant.granmaRestaurant.model.DTO.ComboDTO;
import com.globant.granmaRestaurant.model.entity.ComboEntity;

public interface IComboMapper {
    ComboDTO comboConvertToDTO(ComboEntity comboEntity);

    ComboEntity comboConvertToEntity(ComboDTO comboDTO);
}
