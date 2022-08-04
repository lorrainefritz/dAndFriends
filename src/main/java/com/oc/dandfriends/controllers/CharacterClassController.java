package com.oc.dandfriends.controllers;

import com.oc.dandfriends.dtos.CharacterClassDto;
import com.oc.dandfriends.mappers.CharacterClassDtoMapper;
import com.oc.dandfriends.services.CharacterClassService;
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
public class CharacterClassController {
    private final CharacterClassService characterClassService;
    private final CharacterClassDtoMapper characterClassDtoMapper;


    @GetMapping(value = "/characterClasses")
    public ResponseEntity<List<CharacterClassDto>> findAllCharacterClasses(){
        log.info("HTTP GET request received at /characterClasses with findAllCharacterClasses");
        return new ResponseEntity<>(characterClassDtoMapper.characterClassesToAllCharacterClassesDto(characterClassService.findAllCharacterClasses()), HttpStatus.OK);
    }

    @GetMapping(value = "/characterClasses/get/{id}")
    public ResponseEntity getACharacterClassById(@PathVariable Integer id) throws Exception {
        log.info("HTTP GET request received at /characterClasses/get/" + id + " with getCharacterClassById");
        if (id == null) {
            log.info("HTTP GET request received at /characterClasses/get/id where id is null");
            return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity <>(characterClassDtoMapper.characterClassToCharacterClassDto(characterClassService.findACharacterClassById(id)), HttpStatus.OK);
    }

    @PostMapping(value = "/characterClasses/add")
    public ResponseEntity<CharacterClassDto> saveACharacterClass(@RequestBody @Validated CharacterClassDto characterClassDto, BindingResult bindingResult) throws Exception {
        log.info("HTTP POST request received at /characterClasses with saveACharacterClass");
        if (characterClassDto == null) {
            log.info("HTTP POST request received at /characterClasses with saveACharacterClass where characterClassDto is null");
            return new ResponseEntity<>(characterClassDto, HttpStatus.NO_CONTENT);
        }
        else if (bindingResult.hasErrors()){
            log.info("HTTP POST request received at /characterClasses with saveACharacterClass where characterClassDto is not valid");
            return new ResponseEntity<>(characterClassDto, HttpStatus.FORBIDDEN);
        }
        else {
            characterClassService.saveACharacterClass(characterClassDtoMapper.characterClassDtoToCharacterClass(characterClassDto));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(characterClassDto);
    }

    @DeleteMapping(value = "/characterClasses/delete/{id}")
    public ResponseEntity deleteACharacterClass(@PathVariable Integer id) throws Exception {
        log.info("HTTP DELETE request received at /characterClasses/delete/" + id + " with deleteACharacterClass");
        if (id == null) {
            log.info("HTTP DELETE request received at /characterClasses/delete/id where id is null");
            return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
        }
        characterClassService.deleteACharacterClassById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
