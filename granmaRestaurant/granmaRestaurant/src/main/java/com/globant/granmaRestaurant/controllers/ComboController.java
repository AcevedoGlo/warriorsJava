package com.globant.granmaRestaurant.controllers;

import com.globant.granmaRestaurant.model.DTO.ComboDTO;
import com.globant.granmaRestaurant.services.IServices.IComboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/combo")
public class ComboController {

    @Autowired
    private IComboService comboService;


    @PostMapping("/createCombo")
    ResponseEntity<ComboDTO> createCombo(@RequestBody ComboDTO comboDTO) {
        ComboDTO createCombo = comboService.createCombo(comboDTO);
        return new ResponseEntity<>(createCombo, HttpStatus.CREATED);
    }
    @GetMapping("/comboConsultar/{uuid}")
    ResponseEntity<ComboDTO> getCombo (@PathVariable String uuid){
        ComboDTO getComboUuid = comboService.getCombo(uuid);
        return new ResponseEntity<>(getComboUuid, HttpStatus.OK);
    }
    @PutMapping("/comboUpdate/{uuid}")
    ResponseEntity<Void> updateCombo(@PathVariable String uuid, @RequestBody ComboDTO comboDTO){
        comboService.updateCombo(uuid,comboDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @DeleteMapping("/comboDelete/{uuid}")
    ResponseEntity<Void> comboDelete(@PathVariable String uuid){
        comboService.deleteCombo(uuid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
