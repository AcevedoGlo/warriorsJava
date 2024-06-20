package com.globant.granmaRestaurant.services.IServices;

import com.globant.granmaRestaurant.model.DTO.ComboDTO;

public interface IComboService {
    ComboDTO createCombo(ComboDTO comboDTO);

    ComboDTO getCombo (String uuid);

    void updateCombo(String uuid, ComboDTO comboDTO);

    void deleteCombo(String uuid);
}