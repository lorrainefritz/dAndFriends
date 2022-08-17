package com.oc.dandfriends.controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oc.dandfriends.dtos.CustomTypeOfSpellDto;
import com.oc.dandfriends.entities.CustomTypeOfSpell;
import com.oc.dandfriends.mappers.CustomTypeOfSpellDtoMapper;
import com.oc.dandfriends.services.CustomTypeOfSpellService;

import java.util.ArrayList;

import javax.servlet.http.Cookie;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CustomTypeOfSpellController.class})
@ExtendWith(SpringExtension.class)
class CustomTypeOfSpellControllerTest {
    @Autowired
    private CustomTypeOfSpellController customTypeOfSpellController;

    @MockBean
    private CustomTypeOfSpellDtoMapper customTypeOfSpellDtoMapper;

    @MockBean
    private CustomTypeOfSpellService customTypeOfSpellService;


    @Test
    void testDeleteACustomTypeOfSpell() throws Exception {
        doNothing().when(this.customTypeOfSpellService).deleteACustomTypeOfSpellById((Integer) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/customTypeOfSpells/delete/{id}", 1);
        MockHttpServletRequestBuilder requestBuilder = deleteResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.customTypeOfSpellController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted());
    }


    @Test
    void testSaveACustomTypeOfSpell() throws Exception {
        CustomTypeOfSpell customTypeOfSpell = new CustomTypeOfSpell();
        customTypeOfSpell.setCustomTypeOfSpellName("Custom Type Of Spell Name");
        customTypeOfSpell.setIcon("Icon");
        customTypeOfSpell.setId(1);
        when(this.customTypeOfSpellService.saveACustomTypeOfSpell((CustomTypeOfSpell) any())).thenReturn(customTypeOfSpell);

        CustomTypeOfSpell customTypeOfSpell1 = new CustomTypeOfSpell();
        customTypeOfSpell1.setCustomTypeOfSpellName("Custom Type Of Spell Name");
        customTypeOfSpell1.setIcon("Icon");
        customTypeOfSpell1.setId(1);
        when(this.customTypeOfSpellDtoMapper.customTypeOfSpellDtoToCustomTypeOfSpell((CustomTypeOfSpellDto) any()))
                .thenReturn(customTypeOfSpell1);

        CustomTypeOfSpellDto customTypeOfSpellDto = new CustomTypeOfSpellDto();
        customTypeOfSpellDto.setCustomTypeOfSpellName("Custom Type Of Spell Name");
        customTypeOfSpellDto.setIcon("Icon");
        customTypeOfSpellDto.setId(1);
        String content = (new ObjectMapper()).writeValueAsString(customTypeOfSpellDto);
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/customTypeOfSpells/add");
        MockHttpServletRequestBuilder requestBuilder = postResult.cookie(new Cookie("tokenDandFriends", "ABC123"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.customTypeOfSpellController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"customTypeOfSpellName\":\"Custom Type Of Spell Name\",\"icon\":\"Icon\"}"));
    }


    @Test
    void testDeleteACustomTypeOfSpell2() throws Exception {
        doNothing().when(this.customTypeOfSpellService).deleteACustomTypeOfSpellById((Integer) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/customTypeOfSpells/delete/{id}", 1);
        deleteResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = deleteResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.customTypeOfSpellController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted());
    }


    @Test
    void testFindAllCustomTypeOfSpells() throws Exception {
        when(this.customTypeOfSpellService.findAllCustomTypeOfSpell()).thenReturn(new ArrayList<>());
        when(this.customTypeOfSpellDtoMapper
                .customTypesOfSpellToCustomTypesOfSpellDto((java.util.List<CustomTypeOfSpell>) any()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/customTypeOfSpells");
        MockHttpServletRequestBuilder requestBuilder = getResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        MockMvcBuilders.standaloneSetup(this.customTypeOfSpellController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


        @Test
    void testFindAllCustomTypeOfSpells2() throws Exception {
        when(this.customTypeOfSpellService.findAllCustomTypeOfSpell()).thenReturn(new ArrayList<>());
        when(this.customTypeOfSpellDtoMapper
                .customTypesOfSpellToCustomTypesOfSpellDto((java.util.List<CustomTypeOfSpell>) any()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/customTypeOfSpells");
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = getResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        MockMvcBuilders.standaloneSetup(this.customTypeOfSpellController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testGetACustomTypeOfSpellById() throws Exception {
        CustomTypeOfSpell customTypeOfSpell = new CustomTypeOfSpell();
        customTypeOfSpell.setCustomTypeOfSpellName("Custom Type Of Spell Name");
        customTypeOfSpell.setIcon("Icon");
        customTypeOfSpell.setId(1);
        when(this.customTypeOfSpellService.findACustomTypeOfSpellById((Integer) any())).thenReturn(customTypeOfSpell);
        when(this.customTypeOfSpellDtoMapper.customTypeOfSpellToCustomTypeOfSpellDto((CustomTypeOfSpell) any()))
                .thenReturn(new CustomTypeOfSpellDto());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/customTypeOfSpells/get/{id}", 1);
        MockHttpServletRequestBuilder requestBuilder = getResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        MockMvcBuilders.standaloneSetup(this.customTypeOfSpellController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"id\":null,\"customTypeOfSpellName\":null,\"icon\":null}"));
    }


    @Test
    void testGetACustomTypeOfSpellById2() throws Exception {
        CustomTypeOfSpell customTypeOfSpell = new CustomTypeOfSpell();
        customTypeOfSpell.setCustomTypeOfSpellName("Custom Type Of Spell Name");
        customTypeOfSpell.setIcon("Icon");
        customTypeOfSpell.setId(1);
        when(this.customTypeOfSpellService.findACustomTypeOfSpellById((Integer) any())).thenReturn(customTypeOfSpell);
        when(this.customTypeOfSpellDtoMapper.customTypeOfSpellToCustomTypeOfSpellDto((CustomTypeOfSpell) any()))
                .thenReturn(new CustomTypeOfSpellDto());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/customTypeOfSpells/get/{id}", 1);
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = getResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        MockMvcBuilders.standaloneSetup(this.customTypeOfSpellController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"id\":null,\"customTypeOfSpellName\":null,\"icon\":null}"));
    }
}

