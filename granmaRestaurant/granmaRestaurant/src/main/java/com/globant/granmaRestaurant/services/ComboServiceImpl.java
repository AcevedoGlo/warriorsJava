package com.globant.granmaRestaurant.services;

import com.globant.granmaRestaurant.mapper.ComboMapperImpl;
import com.globant.granmaRestaurant.model.DTO.ComboDTO;
import com.globant.granmaRestaurant.model.entity.ComboEntity;
import com.globant.granmaRestaurant.repositories.ComboRepository;
import com.globant.granmaRestaurant.services.IServices.IComboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ComboServiceImpl implements IComboService {

    @Autowired
    ComboMapperImpl mapper;
    @Autowired
    ComboRepository comboRepository;

    @Override
    public ComboDTO createCombo(ComboDTO comboDTO) {
        ComboEntity comboEntity = mapper.comboConvertToEntity(comboDTO);
        ComboEntity savedCombo = comboRepository.save(comboEntity);
        return mapper.comboConvertToDTO(savedCombo);

    }
    @Override
    public ComboDTO getCombo(String uuid){
        Optional<ComboEntity> comboPetition = comboRepository.findByUuid(uuid);
        return mapper.comboConvertToDTO(comboPetition.orElse(null));
    }

    @Override
    public void updateCombo(String uuid, ComboDTO comboDTO) {
        Optional<ComboEntity> existingComboOpt = comboRepository.findByUuid(uuid);
        if (existingComboOpt.isPresent()) {
            ComboEntity existingCombo = existingComboOpt.get();
            existingCombo.setFantasyName(comboDTO.getFantasyName());
            existingCombo.setCategory(comboDTO.getCategory());
            existingCombo.setDescription(comboDTO.getDescription());
            existingCombo.setPrice(comboDTO.getPrice());
            existingCombo.setAvailable(comboDTO.getAvailable());
            existingCombo.setActive(comboDTO.getActive());
            comboRepository.save(existingCombo);
        }
    }


    @Override
    public void deleteCombo(String uuid) {comboRepository.deleteByUuid(uuid);
    }

    }

