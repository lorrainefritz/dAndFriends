package com.oc.dandfriends.controllers;

import com.oc.dandfriends.dtos.ComponentOfSpellDto;
import com.oc.dandfriends.mappers.ComponentOfSpellDtoMapper;
import com.oc.dandfriends.services.ComponentService;
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
public class ComponentController {
private final ComponentService componentService;
private final ComponentOfSpellDtoMapper componentOfSpellDtoMapper;

    @GetMapping(value = "/components")
    public ResponseEntity<List<ComponentOfSpellDto>> findAllComponents(){
        log.info("HTTP GET request received at /components with findAllComponents");
        return new ResponseEntity<>(componentOfSpellDtoMapper.componentsOfSpellToComponentsOfSpellDto(componentService.findAllComponents()), HttpStatus.OK);
    }

    @GetMapping(value = "/components/get/{id}")
    public ResponseEntity getAComponentById(@PathVariable Integer id) throws Exception {
        log.info("HTTP GET request received at /components/get/" + id + " with getComponentById");
        if (id == null) {
            log.info("HTTP GET request received at /components/get/id where id is null");
            return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity <>(componentOfSpellDtoMapper.componentOfSpellToComponentOfSpellDto(componentService.findAComponentById(id)), HttpStatus.OK);
    }

    @PostMapping(value = "/components/add")
    public ResponseEntity<ComponentOfSpellDto> saveAComponent(@RequestBody @Validated ComponentOfSpellDto componentOfSpellDto, BindingResult bindingResult) throws Exception {
        log.info("HTTP POST request received at /components with saveAComponent");
        if (componentOfSpellDto == null) {
            log.info("HTTP POST request received at /components with saveAComponent where componentDto is null");
            return new ResponseEntity<>(componentOfSpellDto, HttpStatus.NO_CONTENT);
        }
        else if (bindingResult.hasErrors()){
            log.info("HTTP POST request received at /components with saveAComponent where componentDto is not valid");
            return new ResponseEntity<>(componentOfSpellDto, HttpStatus.FORBIDDEN);
        }
        else {
            componentService.saveAComponent(componentOfSpellDtoMapper.componentOfSpellDtoToComponentOfSpell(componentOfSpellDto));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(componentOfSpellDto);
    }

    @DeleteMapping(value = "/components/delete/{id}")
    public ResponseEntity deleteAComponent(@PathVariable Integer id) throws Exception {
        log.info("HTTP DELETE request received at /components/delete/" + id + " with deleteAComponent");
        if (id == null) {
            log.info("HTTP DELETE request received at /components/delete/id where id is null");
            return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
        }
        componentService.deleteAComponentById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }



}
