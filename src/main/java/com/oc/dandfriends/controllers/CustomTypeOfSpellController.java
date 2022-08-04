package com.oc.dandfriends.controllers;

import com.oc.dandfriends.dtos.CustomTypeOfSpellDto;
import com.oc.dandfriends.mappers.CustomTypeOfSpellDtoMapper;
import com.oc.dandfriends.services.CustomTypeOfSpellService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@Log4j2
public class CustomTypeOfSpellController {

    private final CustomTypeOfSpellService customTypeOfSpellService;
    private final CustomTypeOfSpellDtoMapper customTypeOfSpellDtoMapper;

    @GetMapping(value = "/customTypeOfSpells")
    public ResponseEntity<List<CustomTypeOfSpellDto>> findAllCustomTypeOfSpells(){
        log.info("HTTP GET request received at /customTypeOfSpells with findAllCustomTypeOfSpells");
        return new ResponseEntity<>(customTypeOfSpellDtoMapper.customTypesOfSpellToCustomTypesOfSpellDto(customTypeOfSpellService.findAllCustomTypeOfSpell()), HttpStatus.OK);
    }

    @GetMapping(value = "/customTypeOfSpells/get/{id}")
    public ResponseEntity getACustomTypeOfSpellById(@PathVariable Integer id) throws Exception {
        log.info("HTTP GET request received at /customTypeOfSpells/get/" + id + " with getCustomTypeOfSpellById");
        if (id == null) {
            log.info("HTTP GET request received at /customTypeOfSpells/get/id where id is null");
            return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity <>(customTypeOfSpellDtoMapper.customTypeOfSpellToCustomTypeOfSpellDto(customTypeOfSpellService.findACustomTypeOfSpellById(id)), HttpStatus.OK);
    }

    @PostMapping(value = "/customTypeOfSpells/add")
    public ResponseEntity<CustomTypeOfSpellDto> saveACustomTypeOfSpell(@RequestBody @Validated CustomTypeOfSpellDto customTypeOfSpellDto, BindingResult bindingResult) throws Exception {
        log.info("HTTP POST request received at /customTypeOfSpells with saveACustomTypeOfSpell");
        if (customTypeOfSpellDto == null) {
            log.info("HTTP POST request received at /customTypeOfSpells with saveACustomTypeOfSpell where customTypeOfSpellDto is null");
            return new ResponseEntity<>(customTypeOfSpellDto, HttpStatus.NO_CONTENT);
        }
        else if (bindingResult.hasErrors()){
            log.info("HTTP POST request received at /customTypeOfSpells with saveACustomTypeOfSpell where customTypeOfSpellDto is not valid");
            return new ResponseEntity<>(customTypeOfSpellDto, HttpStatus.FORBIDDEN);
        }
        else {
            customTypeOfSpellService.saveACustomTypeOfSpell(customTypeOfSpellDtoMapper.customTypeOfSpellDtoToCustomTypeOfSpell(customTypeOfSpellDto));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(customTypeOfSpellDto);
    }

    @DeleteMapping(value = "/customTypeOfSpells/delete/{id}")
    public ResponseEntity deleteACustomTypeOfSpell(@PathVariable Integer id) throws Exception {
        log.info("HTTP DELETE request received at /customTypeOfSpells/delete/" + id + " with deleteACustomTypeOfSpell");
        if (id == null) {
            log.info("HTTP DELETE request received at /customTypeOfSpells/delete/id where id is null");
            return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
        }
        customTypeOfSpellService.deleteACustomTypeOfSpellById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
