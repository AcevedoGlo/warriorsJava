package com.globant.granmaRestaurant.controllers;

import com.globant.granmaRestaurant.controllers.IControllerEndpoints.IComboPath;
import com.globant.granmaRestaurant.model.DTO.ComboDTO;
import com.globant.granmaRestaurant.services.IServices.IComboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(IComboPath.URL_BASE)
public class ComboController {

    @Autowired
    private IComboService comboService;


    @PostMapping(IComboPath.CREATE_COMBO)
    ResponseEntity<ComboDTO> createCombo(@RequestBody ComboDTO comboDTO) {
        ComboDTO createCombo = comboService.createCombo(comboDTO);
        return new ResponseEntity<>(createCombo, HttpStatus.CREATED);
    }
    @GetMapping(IComboPath.GET_CUSTOMER)
    ResponseEntity<ComboDTO> getCombo (@PathVariable String uuid){
        ComboDTO getComboUuid = comboService.getCombo(uuid);
        return new ResponseEntity<>(getComboUuid, HttpStatus.OK);
    }
        @PutMapping(IComboPath.UPDATE_CUSTOMER)
    ResponseEntity<Void> updateCombo(@PathVariable String uuid, @RequestBody ComboDTO comboDTO){
        comboService.updateCombo(uuid,comboDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @DeleteMapping(IComboPath.DELETE_CUSTOMER)
    ResponseEntity<Void> comboDelete(@PathVariable String uuid){
        comboService.deleteCombo(uuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
