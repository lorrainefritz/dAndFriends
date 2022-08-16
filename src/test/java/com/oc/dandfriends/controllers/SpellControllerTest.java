package com.oc.dandfriends.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oc.dandfriends.dtos.SpellFullDescriptionDto;
import com.oc.dandfriends.entities.CustomTypeOfSpell;
import com.oc.dandfriends.entities.Spell;
import com.oc.dandfriends.enums.School;
import com.oc.dandfriends.mappers.SpellFullDescriptionDtoMapper;
import com.oc.dandfriends.mappers.SpellFullDescriptionDtoMapperImpl;
import com.oc.dandfriends.mappers.SpellShortDescriptionDtoMapper;
import com.oc.dandfriends.mappers.SpellShortDescriptionDtoMapperImpl;
import com.oc.dandfriends.repositories.AppUserRepository;
import com.oc.dandfriends.repositories.CharacterClassRepository;
import com.oc.dandfriends.repositories.ComponentOfSpellRepository;
import com.oc.dandfriends.repositories.CustomTypeOfSpellRepository;
import com.oc.dandfriends.repositories.SpellRepository;
import com.oc.dandfriends.services.AppUserService;
import com.oc.dandfriends.services.CharacterClassService;
import com.oc.dandfriends.services.ComponentService;
import com.oc.dandfriends.services.CustomTypeOfSpellService;
import com.oc.dandfriends.services.SpellService;
import com.oc.dandfriends.token.TokenUtil;

import java.util.ArrayList;

import javax.servlet.http.Cookie;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindException;

@ContextConfiguration(classes = {SpellController.class})
@ExtendWith(SpringExtension.class)
class SpellControllerTest {
    @MockBean
    private AppUserService appUserService;

    @MockBean
    private CharacterClassService characterClassService;

    @MockBean
    private ComponentService componentService;

    @MockBean
    private CustomTypeOfSpellService customTypeOfSpellService;

    @Autowired
    private SpellController spellController;

    @MockBean
    private SpellFullDescriptionDtoMapper spellFullDescriptionDtoMapper;

    @MockBean
    private SpellService spellService;

    @MockBean
    private SpellShortDescriptionDtoMapper spellShortDescriptionDtoMapper;

    @MockBean
    private TokenUtil tokenUtil;


    @Test
    void testFindAllSpells() throws Exception {
        when(this.spellService.findAllSpells()).thenReturn(new ArrayList<>());
        when(this.spellFullDescriptionDtoMapper.spellsToSpellsFullDescriptionDto((java.util.List<Spell>) any()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/spells");
        MockHttpServletRequestBuilder requestBuilder = getResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        MockMvcBuilders.standaloneSetup(this.spellController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testFindAllSpells2() throws Exception {
        when(this.spellService.findAllSpells()).thenReturn(new ArrayList<>());
        when(this.spellFullDescriptionDtoMapper.spellsToSpellsFullDescriptionDto((java.util.List<Spell>) any()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/spells");
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = getResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        MockMvcBuilders.standaloneSetup(this.spellController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testDeleteASpellById() throws Exception {
        doNothing().when(this.spellService).deleteASpell((Integer) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/spells/delete/{id}", 1);
        MockHttpServletRequestBuilder requestBuilder = deleteResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.spellController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted());
    }


    @Test
    void testSaveASpell() throws Exception {
        CustomTypeOfSpell customTypeOfSpell = new CustomTypeOfSpell();
        customTypeOfSpell.setCustomTypeOfSpellName("Custom Type Of Spell Name");
        customTypeOfSpell.setIcon("Icon");
        customTypeOfSpell.setId(1);

        Spell spell = new Spell();
        spell.setCastingTime("Casting Time");
        spell.setCharacterClasses(new ArrayList<>());
        spell.setComponentOfSpells(new ArrayList<>());
        spell.setCustomTypeOfSpell(customTypeOfSpell);
        spell.setDuration("Duration");
        spell.setFullDescription("Full Description");
        spell.setIcon("Icon");
        spell.setId(1);
        spell.setLevel(1);
        spell.setRange("Range");
        spell.setSavingThrow("Saving Throw");
        spell.setSchool(School.ABJURATION);
        spell.setShortDescription("Short Description");
        spell.setSpellResistance(true);
        spell.setTarget("Target");
        spell.setTitle("Dr");
        when(this.spellService.saveASpell((Spell) any())).thenReturn(spell);

        CustomTypeOfSpell customTypeOfSpell1 = new CustomTypeOfSpell();
        customTypeOfSpell1.setCustomTypeOfSpellName("Custom Type Of Spell Name");
        customTypeOfSpell1.setIcon("Icon");
        customTypeOfSpell1.setId(1);

        Spell spell1 = new Spell();
        spell1.setCastingTime("Casting Time");
        spell1.setCharacterClasses(new ArrayList<>());
        spell1.setComponentOfSpells(new ArrayList<>());
        spell1.setCustomTypeOfSpell(customTypeOfSpell1);
        spell1.setDuration("Duration");
        spell1.setFullDescription("Full Description");
        spell1.setIcon("Icon");
        spell1.setId(1);
        spell1.setLevel(1);
        spell1.setRange("Range");
        spell1.setSavingThrow("Saving Throw");
        spell1.setSchool(School.ABJURATION);
        spell1.setShortDescription("Short Description");
        spell1.setSpellResistance(true);
        spell1.setTarget("Target");
        spell1.setTitle("Dr");
        when(this.spellFullDescriptionDtoMapper.spellFullDescriptionDtoToSpell((SpellFullDescriptionDto) any()))
                .thenReturn(spell1);

        CustomTypeOfSpell customTypeOfSpell2 = new CustomTypeOfSpell();
        customTypeOfSpell2.setCustomTypeOfSpellName("Custom Type Of Spell Name");
        customTypeOfSpell2.setIcon("Icon");
        customTypeOfSpell2.setId(1);
        when(this.customTypeOfSpellService.findACustomTypeOfSpellByName((String) any())).thenReturn(customTypeOfSpell2);

        SpellFullDescriptionDto spellFullDescriptionDto = new SpellFullDescriptionDto();
        spellFullDescriptionDto.setCastingTime("Casting Time");
        spellFullDescriptionDto.setCharacterClassesNames(new ArrayList<>());
        spellFullDescriptionDto.setComponentsNames(new ArrayList<>());
        spellFullDescriptionDto.setCustomTypeOfSpellName("Custom Type Of Spell Name");
        spellFullDescriptionDto.setDuration("Duration");
        spellFullDescriptionDto.setFullDescription("Full Description");
        spellFullDescriptionDto.setIcon("Icon");
        spellFullDescriptionDto.setId(1);
        spellFullDescriptionDto.setLevel(1);
        spellFullDescriptionDto.setRange("Range");
        spellFullDescriptionDto.setSavingThrow("Saving Throw");
        spellFullDescriptionDto.setSchool("School");
        spellFullDescriptionDto.setShortDescription("Short Description");
        spellFullDescriptionDto.setSpellResistance(true);
        spellFullDescriptionDto.setTarget("Target");
        spellFullDescriptionDto.setTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(spellFullDescriptionDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/spells/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.spellController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"title\":\"Dr\",\"customTypeOfSpellName\":\"Custom Type Of Spell Name\",\"school\":\"School\",\"level\":1"
                                        + ",\"componentsNames\":[],\"characterClassesNames\":[],\"castingTime\":\"Casting Time\",\"range\":\"Range\",\"target"
                                        + "\":\"Target\",\"duration\":\"Duration\",\"savingThrow\":\"Saving Throw\",\"spellResistance\":true,\"shortDescription"
                                        + "\":\"Short Description\",\"fullDescription\":\"Full Description\",\"icon\":\"Icon\"}"));
    }


    @Test
    void testDeleteASpellById2() throws Exception {
        doNothing().when(this.spellService).deleteASpell((Integer) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/spells/delete/{id}", 1);
        deleteResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = deleteResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.spellController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted());
    }


    @Test
    void testFindSpellsForCustomTypeOfSpell() throws Exception {
        when(this.spellShortDescriptionDtoMapper.spellsToSpellsShortDescriptionDto((java.util.List<Spell>) any()))
                .thenReturn(new ArrayList<>());
        when(this.spellService.findAllSpellsOfACustomTypeOfSpell((Integer) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/spells/byCustomTypeOfSpell/{customTypeOfSpellId}", 123);
        MockHttpServletRequestBuilder requestBuilder = getResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        MockMvcBuilders.standaloneSetup(this.spellController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testFindSpellsForCustomTypeOfSpell2() throws Exception {
        when(this.spellShortDescriptionDtoMapper.spellsToSpellsShortDescriptionDto((java.util.List<Spell>) any()))
                .thenReturn(new ArrayList<>());
        when(this.spellService.findAllSpellsOfACustomTypeOfSpell((Integer) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/spells/byCustomTypeOfSpell/{customTypeOfSpellId}", 123);
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = getResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        MockMvcBuilders.standaloneSetup(this.spellController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testGetASpellById() throws Exception {
        CustomTypeOfSpell customTypeOfSpell = new CustomTypeOfSpell();
        customTypeOfSpell.setCustomTypeOfSpellName("Custom Type Of Spell Name");
        customTypeOfSpell.setIcon("Icon");
        customTypeOfSpell.setId(1);

        Spell spell = new Spell();
        spell.setCastingTime("Casting Time");
        spell.setCharacterClasses(new ArrayList<>());
        spell.setComponentOfSpells(new ArrayList<>());
        spell.setCustomTypeOfSpell(customTypeOfSpell);
        spell.setDuration("Duration");
        spell.setFullDescription("Full Description");
        spell.setIcon("Icon");
        spell.setId(1);
        spell.setLevel(1);
        spell.setRange("Range");
        spell.setSavingThrow("Saving Throw");
        spell.setSchool(School.ABJURATION);
        spell.setShortDescription("Short Description");
        spell.setSpellResistance(true);
        spell.setTarget("Target");
        spell.setTitle("Dr");
        when(this.spellService.findASpellById((Integer) any())).thenReturn(spell);
        when(this.spellFullDescriptionDtoMapper.spellToSpellFullDescriptionDto((Spell) any()))
                .thenReturn(new SpellFullDescriptionDto());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/spells/get/{id}", 1);
        MockHttpServletRequestBuilder requestBuilder = getResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        MockMvcBuilders.standaloneSetup(this.spellController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"title\":null,\"customTypeOfSpellName\":null,\"school\":null,\"level\":0,\"componentsNames\":null,"
                                        + "\"characterClassesNames\":null,\"castingTime\":null,\"range\":null,\"target\":null,\"duration\":null,\"savingThrow"
                                        + "\":null,\"spellResistance\":false,\"shortDescription\":null,\"fullDescription\":null,\"icon\":null}"));
    }


    @Test
    void testGetASpellById2() throws Exception {
        CustomTypeOfSpell customTypeOfSpell = new CustomTypeOfSpell();
        customTypeOfSpell.setCustomTypeOfSpellName("Custom Type Of Spell Name");
        customTypeOfSpell.setIcon("Icon");
        customTypeOfSpell.setId(1);

        Spell spell = new Spell();
        spell.setCastingTime("Casting Time");
        spell.setCharacterClasses(new ArrayList<>());
        spell.setComponentOfSpells(new ArrayList<>());
        spell.setCustomTypeOfSpell(customTypeOfSpell);
        spell.setDuration("Duration");
        spell.setFullDescription("Full Description");
        spell.setIcon("Icon");
        spell.setId(1);
        spell.setLevel(1);
        spell.setRange("Range");
        spell.setSavingThrow("Saving Throw");
        spell.setSchool(School.ABJURATION);
        spell.setShortDescription("Short Description");
        spell.setSpellResistance(true);
        spell.setTarget("Target");
        spell.setTitle("Dr");
        when(this.spellService.findASpellById((Integer) any())).thenReturn(spell);
        when(this.spellFullDescriptionDtoMapper.spellToSpellFullDescriptionDto((Spell) any()))
                .thenReturn(new SpellFullDescriptionDto());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/spells/get/{id}", 1);
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = getResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        MockMvcBuilders.standaloneSetup(this.spellController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"title\":null,\"customTypeOfSpellName\":null,\"school\":null,\"level\":0,\"componentsNames\":null,"
                                        + "\"characterClassesNames\":null,\"castingTime\":null,\"range\":null,\"target\":null,\"duration\":null,\"savingThrow"
                                        + "\":null,\"spellResistance\":false,\"shortDescription\":null,\"fullDescription\":null,\"icon\":null}"));
    }
}

