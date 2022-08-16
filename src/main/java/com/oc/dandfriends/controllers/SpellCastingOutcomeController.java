package com.oc.dandfriends.controllers;

import com.oc.dandfriends.dtos.SpellCastingOutcomeDto;
import com.oc.dandfriends.mappers.SpellCastingOutcomeDtoMapper;
import com.oc.dandfriends.services.SpellCastingOutcomeService;
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
public class SpellCastingOutcomeController {
    private final SpellCastingOutcomeService spellCastingOutcomeService;
    private final SpellCastingOutcomeDtoMapper spellCastingOutcomeDtoMapper;

    @GetMapping(value = "/spellCastingOutcomes")
    public ResponseEntity<List<SpellCastingOutcomeDto>> findAllSpellCastingOutcomes(@CookieValue(value = "tokenDandFriends") String token)throws Exception{
        log.info("HTTP GET request received at /spellCastingOutcomes with findAllSpellCastingOutcomes");
        return new ResponseEntity<>(spellCastingOutcomeDtoMapper.spellCastingOutcomesToAllSpellCastingOutcomesDto(spellCastingOutcomeService.findAllSpellCastingOutcome()), HttpStatus.OK);
    }

    @GetMapping(value = "/spellCastingOutcomes/get/{id}")
    public ResponseEntity getASpellCastingOutcomeById(@PathVariable Integer id,@CookieValue(value = "tokenDandFriends") String token) throws Exception {
        log.info("HTTP GET request received at /spellCastingOutcomes/get/" + id + " with getSpellCastingOutcomeById");
        if (id == null) {
            log.info("HTTP GET request received at /spellCastingOutcomes/get/id where id is null");
            return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity <>(spellCastingOutcomeDtoMapper.spellCastingOutcomeToSpellCastingOutcomeDto(spellCastingOutcomeService.findASpellCastingOutcomeById(id)), HttpStatus.OK);
    }
    @GetMapping(value = "/spellCastingOutcomes/generate/{spellWasASuccess}")
    public ResponseEntity generateASpellCastingOutcome(@PathVariable Boolean spellWasASuccess,@CookieValue(value = "tokenDandFriends") String token) throws Exception {
        log.info("HTTP GET request received at /spellCastingOutcomes/generateASpellCastingOutcome/" + spellWasASuccess + " with generateSpellCastingOutcome");
        if (spellWasASuccess == null) {
            log.info("HTTP GET request received at /spellCastingOutcomes/generateASpellCastingOutcome/ where spellWasASuccess is null");
            return new ResponseEntity<>(spellWasASuccess, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity <>(spellCastingOutcomeDtoMapper.spellCastingOutcomeToSpellCastingOutcomeDto(spellCastingOutcomeService.generateASpellOutcome(spellWasASuccess)), HttpStatus.OK);
    }


    @PostMapping(value = "/spellCastingOutcomes/add")
    public ResponseEntity<SpellCastingOutcomeDto> saveASpellCastingOutcome(@RequestBody @Validated SpellCastingOutcomeDto spellCastingOutcomeDto, BindingResult bindingResult,@CookieValue(value = "tokenDandFriends") String token) throws Exception {
        log.info("HTTP POST request received at /spellCastingOutcomes with saveASpellCastingOutcome");
        if (spellCastingOutcomeDto == null) {
            log.info("HTTP POST request received at /spellCastingOutcomes with saveASpellCastingOutcome where SpellCastingOutcomeDto is null");
            return new ResponseEntity<>(spellCastingOutcomeDto, HttpStatus.NO_CONTENT);
        }
        else if (bindingResult.hasErrors()){
            log.info("HTTP POST request received at /spellCastingOutcomes with saveASpellCastingOutcome where spellCastingOutcomeDto is not valid");
            return new ResponseEntity<>(spellCastingOutcomeDto, HttpStatus.FORBIDDEN);
        }
        else {
            spellCastingOutcomeService.saveASpellCastingOutcome(spellCastingOutcomeDtoMapper.spellCastingOutcomeDtoToSpellCastingOutcome(spellCastingOutcomeDto));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(spellCastingOutcomeDto);
    }

    @DeleteMapping(value = "/spellCastingOutcomes/delete/{id}")
    public ResponseEntity deleteASpellCastingOutcome(@PathVariable Integer id,@CookieValue(value = "tokenDandFriends") String token) throws Exception {
        log.info("HTTP DELETE request received at /spellCastingOutcomes/delete/" + id + " with deleteASpellCastingOutcome");
        if (id == null) {
            log.info("HTTP DELETE request received at /spellCastingOutcomes/delete/id where id is null");
            return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
        }
        spellCastingOutcomeService.deleteASpellCastingOutcomeById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
