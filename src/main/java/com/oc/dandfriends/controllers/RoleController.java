package com.oc.dandfriends.controllers;

import com.oc.dandfriends.dtos.RoleDto;
import com.oc.dandfriends.mappers.RoleDtoMapper;
import com.oc.dandfriends.services.RoleService;
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
public class RoleController {
    private final RoleService roleService;
    private final RoleDtoMapper roleDtoMapper;

    @GetMapping(value = "/roles")
    public ResponseEntity<List<RoleDto>> findAllRoles(@CookieValue(value = "tokenDandFriends") String token)throws Exception{
        log.info("HTTP GET request received at /roless with findAllRoles");
        return new ResponseEntity<>(roleDtoMapper.rolesToAllRolesDto(roleService.findAllRole()), HttpStatus.OK);
    }

    @GetMapping(value = "/roles/get/{id}")
    public ResponseEntity getARoleById(@PathVariable Integer id,@CookieValue(value = "tokenDandFriends") String token) throws Exception {
        log.info("HTTP GET request received at /roles/get/" + id + " with getRoleById");
        if (id == null) {
            log.info("HTTP GET request received at /roles/get/id where id is null");
            return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity <>(roleDtoMapper.roleToRoleDto(roleService.findARoleById(id)), HttpStatus.OK);
    }

    @PostMapping(value = "/roles/add")
    public ResponseEntity<RoleDto> addARole(@RequestBody @Validated RoleDto roleDto, BindingResult bindingResult,@CookieValue(value = "tokenDandFriends") String token) throws Exception {
        log.info("HTTP POST request received at /roles with saveARole");
        if (roleDto == null) {
            log.info("HTTP POST request received at /roles with saveARole where RoleDto is null");
            return new ResponseEntity<>(roleDto, HttpStatus.NO_CONTENT);
        }
        else if (bindingResult.hasErrors()){
            log.info("HTTP POST request received at /roles with saveARole where roleDto is not valid");
            return new ResponseEntity<>(roleDto, HttpStatus.FORBIDDEN);
        }
        else {
            roleService.saveARole(roleDtoMapper.roleDtoToRole(roleDto));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(roleDto);
    }



    @DeleteMapping(value = "/roles/delete/{id}")
    public ResponseEntity deleteARole(@PathVariable Integer id, @CookieValue(value = "tokenDandFriends") String token) throws Exception {
        log.info("HTTP DELETE request received at /roles/delete/" + id + " with deleteARole");
        if (id == null) {
            log.info("HTTP DELETE request received at /roles/delete/id where id is null");
            return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
        }
        roleService.deleteARoleById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
