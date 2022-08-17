package com.oc.dandfriends.controllers;

import com.oc.dandfriends.dtos.SpellFullDescriptionDto;
import com.oc.dandfriends.dtos.SpellShortDescriptionDto;
import com.oc.dandfriends.entities.AppUser;
import com.oc.dandfriends.entities.CharacterClass;
import com.oc.dandfriends.entities.ComponentOfSpell;
import com.oc.dandfriends.entities.Spell;
import com.oc.dandfriends.mappers.SpellFullDescriptionDtoMapper;
import com.oc.dandfriends.mappers.SpellShortDescriptionDtoMapper;
import com.oc.dandfriends.services.*;
import com.oc.dandfriends.token.TokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@Log4j2
public class SpellController {
    private final SpellService spellService;
    private final AppUserService appUserService;
    private final SpellFullDescriptionDtoMapper spellFullDescriptionDtoMapper;
    private final SpellShortDescriptionDtoMapper spellShortDescriptionDtoMapper;
    private final CustomTypeOfSpellService customTypeOfSpellService;
    private final CharacterClassService characterClassService;
    private final ComponentService componentOfSpellService;
    private final TokenUtil tokenUtil;


    @GetMapping(value = "/spells")
    public ResponseEntity<List<SpellFullDescriptionDto>> findAllSpells(@CookieValue(value = "tokenDandFriends") String token) throws Exception {
        log.info("HTTP GET request received at /spells with findAllSpells");
        return new ResponseEntity<>(spellFullDescriptionDtoMapper.spellsToSpellsFullDescriptionDto(spellService.findAllSpells()), HttpStatus.OK);
    }


    @GetMapping(value = "/spells/byCustomTypeOfSpell/{customTypeOfSpellId}")
    public ResponseEntity<List<SpellShortDescriptionDto>> findSpellsForCustomTypeOfSpell(@PathVariable Integer customTypeOfSpellId, @CookieValue(value = "tokenDandFriends") String token) throws Exception {
        log.info("HTTP GET request received at spells/byCustomTypeOfSpell/{} with findSpellsForCustomTypeOfSpell", customTypeOfSpellId);
        return new ResponseEntity<>(spellShortDescriptionDtoMapper.spellsToSpellsShortDescriptionDto(spellService.findAllSpellsOfACustomTypeOfSpell(customTypeOfSpellId)), HttpStatus.OK);
    }

    @GetMapping(value = "/spells/get/{id}")
    public ResponseEntity getASpellById(@PathVariable Integer id, @CookieValue(value = "tokenDandFriends") String token) throws Exception {
        log.info("HTTP GET request received at /spells/get/" + id + " with getSpellById");
        if (id == null) {
            log.info("HTTP GET request received at /spells/get/id where id is null");
            return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(spellFullDescriptionDtoMapper.spellToSpellFullDescriptionDto(spellService.findASpellById(id)), HttpStatus.OK);
    }


    @PostMapping(value = "/spells/add")
    public ResponseEntity<SpellFullDescriptionDto> saveASpell(@RequestBody @Validated SpellFullDescriptionDto spellDto, BindingResult bindingResult) throws Exception {
        log.info("HTTP POST request received at /spells with saveASpell");
        if (spellDto == null) {
            log.info("HTTP POST request received at /spells with saveASpell where SpellDto is null");
            return new ResponseEntity<>(spellDto, HttpStatus.NO_CONTENT);
        } else if (bindingResult.hasErrors()) {
            log.info("HTTP POST request received at /spells with saveASpell where spellDto is not valid");
            return new ResponseEntity<>(spellDto, HttpStatus.FORBIDDEN);
        } else {
            Spell spell = getAValidSpellFromDto(spellDto);
            spellService.saveASpell(spell);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(spellDto);
    }

    private Spell getAValidSpellFromDto(SpellFullDescriptionDto spellDto) throws Exception {
        log.info(" in SpellController in getAValidSpellFromDto method");
        Spell spell = spellFullDescriptionDtoMapper.spellFullDescriptionDtoToSpell(spellDto);
        spell.setCustomTypeOfSpell(customTypeOfSpellService.findACustomTypeOfSpellByName(spellDto.getCustomTypeOfSpellName()));

        List<ComponentOfSpell> componentOfSpellsList = new ArrayList<>();
        List<String> componentsNameFromDto = spellDto.getComponentsNames();
        for (String componentName : componentsNameFromDto) {
            ComponentOfSpell componentOfSpell = componentOfSpellService.findAComponentByName(componentName);
            componentOfSpellsList.add(componentOfSpell);
        }
        spell.setComponentOfSpells(componentOfSpellsList);

        List<CharacterClass> characterClassesList = new ArrayList<>();
        List<String> characterClassNameListFromDto = spellDto.getCharacterClassesNames();
        for (String characterClassName : characterClassNameListFromDto) {
            CharacterClass characterClass = characterClassService.findACharacterClassByName(characterClassName);
            characterClassesList.add(characterClass);
        }
        spell.setCharacterClasses(characterClassesList);
        return spell;
    }


    @DeleteMapping(value = "/spells/delete/{id}")
    public ResponseEntity deleteASpellById(@PathVariable Integer id, @CookieValue(value = "tokenDandFriends") String token) throws Exception {
        log.info("HTTP DELETE request received at /spells/delete/" + id + " with deleteASpell");
        if (id == null) {
            log.info("HTTP DELETE request received at /spells/delete/id where id is null");
            return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
        }
        String username = tokenUtil.checkTokenAndRetrieveUsernameFromIt(token);
        AppUser appUser = appUserService.findAnAppUserByName(username);
        String role = appUser.getRole().getRoleName();
        if (!role.equals("ROLE_MJ")) {
            log.info("HTTP DELETE request received at /spells/delete/id where user has not role MJ" + appUser.getRole().getRoleName());
            return new ResponseEntity<>(id, HttpStatus.FORBIDDEN);
        }
        spellService.deleteASpell(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
