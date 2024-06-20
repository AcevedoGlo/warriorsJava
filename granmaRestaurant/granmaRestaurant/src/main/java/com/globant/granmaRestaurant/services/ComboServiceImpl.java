package com.globant.granmaRestaurant.services;

import com.globant.granmaRestaurant.exception.custonException.CustomException;
import com.globant.granmaRestaurant.exception.enums.ExceptionCode;
import com.globant.granmaRestaurant.mapper.ComboMapperImpl;
import com.globant.granmaRestaurant.model.DTO.ComboDTO;
import com.globant.granmaRestaurant.model.entity.ComboEntity;
import com.globant.granmaRestaurant.repositories.ComboRepository;
import com.globant.granmaRestaurant.services.IServices.IComboService;
import com.globant.granmaRestaurant.services.validators.ComboValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ComboServiceImpl implements IComboService {

    @Autowired
    private ComboMapperImpl mapper;

    @Autowired
    private ComboRepository comboRepository;

    @Autowired
    private ComboValidator comboValidator;

    @Override
    public ComboDTO createCombo(ComboDTO comboDTO) {
        comboValidator.validateComboData(comboDTO);
        comboRepository.findByFantasyName(comboDTO.getFantasyName())
                .ifPresent(existingCombo -> {
                    throw new CustomException(
                            ExceptionCode.COMBO_ALREADY_EXISTS,
                            LocalDateTime.now(),
                            HttpStatus.CONFLICT,
                            "Combo con nombre de fantasía: " + comboDTO.getFantasyName() + " ya existe."
                    );
                });
        ComboEntity comboEntity = mapper.comboConvertToEntity(comboDTO);
        ComboEntity savedCombo = comboRepository.save(comboEntity);
        return mapper.comboConvertToDTO(savedCombo);
    }

    @Override
    public ComboDTO getCombo(String uuid) {
        Optional<ComboEntity> comboPetition = comboRepository.findByUuid(uuid);
        return comboPetition.map(mapper::comboConvertToDTO)
                .orElseThrow(() -> new CustomException(
                        ExceptionCode.COMBO_NOT_FOUND,
                        LocalDateTime.now(),
                        HttpStatus.NOT_FOUND,
                        "Combo con UUID: " + uuid + " no encontrado."
                ));
    }

    @Override
    @Transactional
    public void updateCombo(String uuid, ComboDTO comboDTO) {
        comboValidator.validateComboData(comboDTO);
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
        } else {
            throw new CustomException(
                    ExceptionCode.COMBO_NOT_FOUND,
                    LocalDateTime.now(),
                    HttpStatus.NOT_FOUND,
                    "Combo con UUID: " + uuid + " no encontrado."
            );
        }
    }

    @Override
    @Transactional
    public void deleteCombo(String uuid) {
        Optional<ComboEntity> existingCombo = comboRepository.findByUuid(uuid);
        if (existingCombo.isPresent()) {
            comboRepository.deleteByUuid(uuid);
        } else {
            throw new CustomException(
                    ExceptionCode.COMBO_NOT_FOUND,
                    LocalDateTime.now(),
                    HttpStatus.NOT_FOUND,
                    "Combo con UUID: " + uuid + " no encontrado."
            );
        }
    }
}