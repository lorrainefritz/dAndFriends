package com.oc.dandfriends.controllers;

import com.oc.dandfriends.dtos.AppUserDto;
import com.oc.dandfriends.mappers.AppUserDtoMapper;
import com.oc.dandfriends.services.AppUserService;
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
public class AppUserController {
    private final AppUserService appUserService;
    private  final AppUserDtoMapper appUserDtoMapper;

    @GetMapping(value = "/appUsers")
    public ResponseEntity<List<AppUserDto>> findAllAppUsers(){
        log.info("HTTP GET request received at /appUsers with findAllAppUsers");
        return new ResponseEntity<>(appUserDtoMapper.appUsersToAllAppUsersDto(appUserService.findAllAppUsers()), HttpStatus.OK);
    }

    @GetMapping(value = "/appUsers/get/{id}")
    public ResponseEntity getAnAppUserById(@PathVariable Integer id) throws Exception {
        log.info("HTTP GET request received at /appUsers/get/" + id + " with getAnAppUserById");
        if (id == null) {
            log.info("HTTP GET request received at /appUsers/get/id where id is null");
            return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity <>(appUserDtoMapper.appUserToAppUserDto(appUserService.getAnAppUserById(id)), HttpStatus.OK);
    }

    @PostMapping(value = "/appUsers")
    public ResponseEntity<AppUserDto> saveAnAppUser(@RequestBody @Validated AppUserDto appUserDto, BindingResult bindingResult) throws Exception {
        log.info("HTTP POST request received at /appUsers with saveAnAppUser");
        if (appUserDto == null) {
            log.info("HTTP POST request received at /AdditionalRandomOutcomes with saveAnAdditionalRandomOutcome where additionalRandomOutcomeDto is null");
            return new ResponseEntity<>(appUserDto, HttpStatus.NO_CONTENT);
        }
        else if (bindingResult.hasErrors()){
            log.info("HTTP POST request received at /AdditionalRandomOutcomes with saveAnAdditionalRandomOutcome where additionalRandomOutcomeDto is not valid");
            return new ResponseEntity<>(appUserDto, HttpStatus.FORBIDDEN);
        }
        else {
            appUserService.saveAnAppUser(appUserDtoMapper.appUserDtoToAppUser(appUserDto));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(appUserDto);
    }

}
