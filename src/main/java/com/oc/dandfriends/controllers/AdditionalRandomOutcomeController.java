package com.oc.dandfriends.controllers;

import com.oc.dandfriends.dtos.AdditionalRandomOutcomeDto;
import com.oc.dandfriends.entities.AdditionalRandomOutcome;
import com.oc.dandfriends.mappers.AdditionalRandomOutcomeDtoMapper;
import com.oc.dandfriends.repositories.AdditionalRandomOutcomeRepository;
import com.oc.dandfriends.services.AdditionalRandomOutcomeService;
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
public class AdditionalRandomOutcomeController {

    private final AdditionalRandomOutcomeService additionalRandomOutcomeService;
    private final AdditionalRandomOutcomeDtoMapper additionalRandomOutcomeDtoMapper;

    @GetMapping(value = "/additionalRandomOutcomes")
    public ResponseEntity<List<AdditionalRandomOutcomeDto>> findAllAdditionalRandomOutcomes(){
        log.info("HTTP GET request received at /additionalRandomOutcomes with findAllAdditionalRandomOutcomes");
        return new ResponseEntity<>(additionalRandomOutcomeDtoMapper.additionalRandomOutcomesToAllAdditionalRandomOutcomesDto(additionalRandomOutcomeService.findAllAdditionalRandomOutcome()), HttpStatus.OK);
    }

    @GetMapping(value = "/additionalRandomOutcomes/get/{id}")
    public ResponseEntity getAnAdditionalRandomOutcomeById(@PathVariable Integer id) throws Exception {
        log.info("HTTP GET request received at /additionalRandomOutcomes/get/" + id + " with getAnAdditionalRandomOutcome");
        if (id == null) {
            log.info("HTTP GET request received at /additionalRandomOutcomes/get/id where id is null");
            return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity <>(additionalRandomOutcomeDtoMapper.additionalRandomOutcomeToAdditionalRandomOutcomeDto(additionalRandomOutcomeService.getAnAdditionalRandomOutcomeById(id)), HttpStatus.OK);
    }

    @PostMapping(value = "/additionalRandomOutcomes")
    public ResponseEntity<AdditionalRandomOutcomeDto> saveAnAdditionalRandomOutcome(@RequestBody @Validated AdditionalRandomOutcomeDto additionalRandomOutcomeDto, BindingResult bindingResult) throws Exception {
        log.info("HTTP POST request received at /AdditionalRandomOutcomes with saveAnAdditionalRandomOutcome");
        if (additionalRandomOutcomeDto == null) {
            log.info("HTTP POST request received at /AdditionalRandomOutcomes with saveAnAdditionalRandomOutcome where additionalRandomOutcomeDto is null");
            return new ResponseEntity<>(additionalRandomOutcomeDto, HttpStatus.NO_CONTENT);
        }
        else if (bindingResult.hasErrors()){
            log.info("HTTP POST request received at /AdditionalRandomOutcomes with saveAnAdditionalRandomOutcome where additionalRandomOutcomeDto is not valid");
            return new ResponseEntity<>(additionalRandomOutcomeDto, HttpStatus.FORBIDDEN);
        }
        else {
            additionalRandomOutcomeService.saveAnAdditionalRandomOutcome(additionalRandomOutcomeDtoMapper.additionalRandomOutcomeDtoToAdditionalRandomOutcome(additionalRandomOutcomeDto));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(additionalRandomOutcomeDto);
    }

    @DeleteMapping(value = "/additionalRandomOutcomes/delete/{id}")
    public ResponseEntity deleteAnAdditionalRandomOutcome(@PathVariable Integer id) throws Exception {
        log.info("HTTP DELETE request received at /additionalRandomOutcomes/delete/" + id + " with deleteAnAdditionalRandomOutcome");
        if (id == null) {
            log.info("HTTP DELETE request received at /additionalRandomOutcomes/delete/id where id is null");
            return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
        }
        additionalRandomOutcomeService.deleteAnAdditionalRandomOutcome(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
