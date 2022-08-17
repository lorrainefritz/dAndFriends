package com.oc.dandfriends.controllers;

import com.oc.dandfriends.dtos.AppUserDto;
import com.oc.dandfriends.entities.AppUser;
import com.oc.dandfriends.mappers.AppUserDtoMapper;
import com.oc.dandfriends.security.UserAuthentication;
import com.oc.dandfriends.services.AppUserService;
import com.oc.dandfriends.token.TokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@Log4j2
public class AppUserController {
    private final AppUserService appUserService;
    private  final AppUserDtoMapper appUserDtoMapper;
    private final UserAuthentication userAuthentication;

    @GetMapping(value = "/appUsers")
    public ResponseEntity<List<AppUserDto>> findAllAppUsers(@CookieValue(value = "tokenDandFriends") String token) throws Exception{
        log.info("HTTP GET request received at /appUsers with findAllAppUsers");
        return new ResponseEntity<>(appUserDtoMapper.appUsersToAllAppUsersDto(appUserService.findAllAppUsers()), HttpStatus.OK);
    }

    @GetMapping(value = "/appUsers/get/{id}")
    public ResponseEntity getAnAppUserById(@PathVariable Integer id,@CookieValue(value = "tokenDandFriends") String token) throws Exception {
        log.info("HTTP GET request received at /appUsers/get/" + id + " with getAnAppUserById");
        if (id == null) {
            log.info("HTTP GET request received at /appUsers/get/id where id is null");
            return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity <>(appUserDtoMapper.appUserToAppUserDto(appUserService.findAnAppUserById(id)), HttpStatus.OK);
    }

    @PostMapping(value = "/appUsers/add")
    public ResponseEntity<AppUserDto> saveAnAppUser(@RequestBody @Validated AppUserDto appUserDto, BindingResult bindingResult,@CookieValue(value = "tokenDandFriends") String token) throws Exception {
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

    @PostMapping(value = "/login")
    public ResponseEntity authenticate(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password, HttpServletResponse response) throws Exception {
        log.info("in UserController in AUTHENTICATE with username {}", username);
        AppUser appUser= appUserService.findAnAppUserByName(username);
        log.info("in UserController in AUTHENTICATE where user pseudo is {}",appUser.getPseudo());
        String token = userAuthentication.successfulAuthentication(username, password);
        List<String> tokenToPass = new ArrayList<>();
        tokenToPass.add(token);
        tokenToPass.add(appUser.getRole().getRoleName());
        tokenToPass.add(appUser.getPseudo());
        return new ResponseEntity(tokenToPass, HttpStatus.OK);
    }


}
