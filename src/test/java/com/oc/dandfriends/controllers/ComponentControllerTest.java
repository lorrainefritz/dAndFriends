package com.oc.dandfriends.controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oc.dandfriends.dtos.ComponentOfSpellDto;
import com.oc.dandfriends.entities.ComponentOfSpell;
import com.oc.dandfriends.mappers.ComponentOfSpellDtoMapper;
import com.oc.dandfriends.services.ComponentService;

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

@ContextConfiguration(classes = {ComponentController.class})
@ExtendWith(SpringExtension.class)
class ComponentControllerTest {
    @Autowired
    private ComponentController componentController;

    @MockBean
    private ComponentOfSpellDtoMapper componentOfSpellDtoMapper;

    @MockBean
    private ComponentService componentService;


    @Test
    void testDeleteAComponent() throws Exception {
        doNothing().when(this.componentService).deleteAComponentById((Integer) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/components/delete/{id}", 1);
        MockHttpServletRequestBuilder requestBuilder = deleteResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.componentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted());
    }


    @Test
    void testFindAllComponents() throws Exception {
        when(this.componentService.findAllComponents()).thenReturn(new ArrayList<>());
        when(this.componentOfSpellDtoMapper
                .componentsOfSpellToComponentsOfSpellDto((java.util.List<ComponentOfSpell>) any()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/components");
        MockHttpServletRequestBuilder requestBuilder = getResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        MockMvcBuilders.standaloneSetup(this.componentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testFindAllComponents2() throws Exception {
        when(this.componentService.findAllComponents()).thenReturn(new ArrayList<>());
        when(this.componentOfSpellDtoMapper
                .componentsOfSpellToComponentsOfSpellDto((java.util.List<ComponentOfSpell>) any()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/components");
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = getResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        MockMvcBuilders.standaloneSetup(this.componentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testSaveAComponent() throws Exception {
        ComponentOfSpell componentOfSpell = new ComponentOfSpell();
        componentOfSpell.setComponentName("Component Name");
        componentOfSpell.setIcon("Icon");
        componentOfSpell.setId(1);
        when(this.componentService.saveAComponent((ComponentOfSpell) any())).thenReturn(componentOfSpell);

        ComponentOfSpell componentOfSpell1 = new ComponentOfSpell();
        componentOfSpell1.setComponentName("Component Name");
        componentOfSpell1.setIcon("Icon");
        componentOfSpell1.setId(1);
        when(this.componentOfSpellDtoMapper.componentOfSpellDtoToComponentOfSpell((ComponentOfSpellDto) any()))
                .thenReturn(componentOfSpell1);

        ComponentOfSpellDto componentOfSpellDto = new ComponentOfSpellDto();
        componentOfSpellDto.setComponentName("Component Name");
        componentOfSpellDto.setIcon("Icon");
        componentOfSpellDto.setId(1);
        String content = (new ObjectMapper()).writeValueAsString(componentOfSpellDto);
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/components/add");
        MockHttpServletRequestBuilder requestBuilder = postResult.cookie(new Cookie("tokenDandFriends", "ABC123"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.componentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"componentName\":\"Component Name\",\"icon\":\"Icon\"}"));
    }


    @Test
    void testDeleteAComponent2() throws Exception {
        doNothing().when(this.componentService).deleteAComponentById((Integer) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/components/delete/{id}", 1);
        deleteResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = deleteResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.componentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted());
    }


    @Test
    void testGetAComponentById() throws Exception {
        ComponentOfSpell componentOfSpell = new ComponentOfSpell();
        componentOfSpell.setComponentName("Component Name");
        componentOfSpell.setIcon("Icon");
        componentOfSpell.setId(1);
        when(this.componentService.findAComponentById((Integer) any())).thenReturn(componentOfSpell);
        when(this.componentOfSpellDtoMapper.componentOfSpellToComponentOfSpellDto((ComponentOfSpell) any()))
                .thenReturn(new ComponentOfSpellDto());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/components/get/{id}", 1);
        MockHttpServletRequestBuilder requestBuilder = getResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        MockMvcBuilders.standaloneSetup(this.componentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":null,\"componentName\":null,\"icon\":null}"));
    }


    @Test
    void testGetAComponentById2() throws Exception {
        ComponentOfSpell componentOfSpell = new ComponentOfSpell();
        componentOfSpell.setComponentName("Component Name");
        componentOfSpell.setIcon("Icon");
        componentOfSpell.setId(1);
        when(this.componentService.findAComponentById((Integer) any())).thenReturn(componentOfSpell);
        when(this.componentOfSpellDtoMapper.componentOfSpellToComponentOfSpellDto((ComponentOfSpell) any()))
                .thenReturn(new ComponentOfSpellDto());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/components/get/{id}", 1);
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = getResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        MockMvcBuilders.standaloneSetup(this.componentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":null,\"componentName\":null,\"icon\":null}"));
    }
}

