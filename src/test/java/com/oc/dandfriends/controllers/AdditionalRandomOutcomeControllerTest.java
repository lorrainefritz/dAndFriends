package com.oc.dandfriends.controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oc.dandfriends.dtos.AdditionalRandomOutcomeDto;
import com.oc.dandfriends.entities.AdditionalRandomOutcome;
import com.oc.dandfriends.mappers.AdditionalRandomOutcomeDtoMapper;
import com.oc.dandfriends.services.AdditionalRandomOutcomeService;

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

@ContextConfiguration(classes = {AdditionalRandomOutcomeController.class})
@ExtendWith(SpringExtension.class)
class  pAdditionalRandomOutcomeControllerTest {
    @Autowired
    private AdditionalRandomOutcomeController additionalRandomOutcomeController;

    @MockBean
    private AdditionalRandomOutcomeDtoMapper additionalRandomOutcomeDtoMapper;

    @MockBean
    private AdditionalRandomOutcomeService additionalRandomOutcomeService;


    @Test
    void testSaveAnAdditionalRandomOutcome() throws Exception {
        AdditionalRandomOutcome additionalRandomOutcome = new AdditionalRandomOutcome();
        additionalRandomOutcome.setDescription("Tutu rose");
        additionalRandomOutcome.setIcon("Icon");
        additionalRandomOutcome.setId(1);
        when(this.additionalRandomOutcomeService.saveAnAdditionalRandomOutcome((AdditionalRandomOutcome) any()))
                .thenReturn(additionalRandomOutcome);

        AdditionalRandomOutcome additionalRandomOutcome1 = new AdditionalRandomOutcome();
        additionalRandomOutcome1.setDescription("Tutu rose");
        additionalRandomOutcome1.setIcon("Icon");
        additionalRandomOutcome1.setId(1);
        when(this.additionalRandomOutcomeDtoMapper
                .additionalRandomOutcomeDtoToAdditionalRandomOutcome((AdditionalRandomOutcomeDto) any()))
                .thenReturn(additionalRandomOutcome1);

        AdditionalRandomOutcomeDto additionalRandomOutcomeDto = new AdditionalRandomOutcomeDto();
        additionalRandomOutcomeDto.setDescription("Tutu rose");
        additionalRandomOutcomeDto.setIcon("Icon");
        additionalRandomOutcomeDto.setId(1);
        String content = (new ObjectMapper()).writeValueAsString(additionalRandomOutcomeDto);
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/additionalRandomOutcomes/add");
        MockHttpServletRequestBuilder requestBuilder = postResult.cookie(new Cookie("tokenDandFriends", "ABC123"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.additionalRandomOutcomeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"description\":\"Tutu rose\",\"icon\":\"Icon\"}"));
    }


    @Test
    void testDeleteAnAdditionalRandomOutcome() throws Exception {
        doNothing().when(this.additionalRandomOutcomeService).deleteAnAdditionalRandomOutcome((Integer) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/additionalRandomOutcomes/delete/{id}",
                1);
        MockHttpServletRequestBuilder requestBuilder = deleteResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.additionalRandomOutcomeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted());
    }


    @Test
    void testDeleteAnAdditionalRandomOutcome2() throws Exception {
        doNothing().when(this.additionalRandomOutcomeService).deleteAnAdditionalRandomOutcome((Integer) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/additionalRandomOutcomes/delete/{id}",
                1);
        deleteResult.contentType("https://pomme.org/pomme");
        MockHttpServletRequestBuilder requestBuilder = deleteResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.additionalRandomOutcomeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted());
    }


    @Test
    void testFindAllAdditionalRandomOutcomes() throws Exception {
        when(this.additionalRandomOutcomeService.findAllAdditionalRandomOutcome()).thenReturn(new ArrayList<>());
        when(this.additionalRandomOutcomeDtoMapper
                .additionalRandomOutcomesToAllAdditionalRandomOutcomesDto((java.util.List<AdditionalRandomOutcome>) any()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/additionalRandomOutcomes");
        MockHttpServletRequestBuilder requestBuilder = getResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        MockMvcBuilders.standaloneSetup(this.additionalRandomOutcomeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testFindAllAdditionalRandomOutcomes2() throws Exception {
        when(this.additionalRandomOutcomeService.findAllAdditionalRandomOutcome()).thenReturn(new ArrayList<>());
        when(this.additionalRandomOutcomeDtoMapper
                .additionalRandomOutcomesToAllAdditionalRandomOutcomesDto((java.util.List<AdditionalRandomOutcome>) any()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/additionalRandomOutcomes");
        getResult.contentType("https://pomme.org/pomme");
        MockHttpServletRequestBuilder requestBuilder = getResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        MockMvcBuilders.standaloneSetup(this.additionalRandomOutcomeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testGenerataAnAdditionalOutcome() throws Exception {
        AdditionalRandomOutcome additionalRandomOutcome = new AdditionalRandomOutcome();
        additionalRandomOutcome.setDescription("Tutu rose");
        additionalRandomOutcome.setIcon("Icon");
        additionalRandomOutcome.setId(1);
        when(this.additionalRandomOutcomeService.generateAnAdditionalRandomOutcome()).thenReturn(additionalRandomOutcome);
        when(this.additionalRandomOutcomeDtoMapper
                .additionalRandomOutcomeToAdditionalRandomOutcomeDto((AdditionalRandomOutcome) any()))
                .thenReturn(new AdditionalRandomOutcomeDto());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/spellCastingOutcomes/additionalRandomOutcomes/generate");
        MockMvcBuilders.standaloneSetup(this.additionalRandomOutcomeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":null,\"description\":null,\"icon\":null}"));
    }


    @Test
    void testGetAnAdditionalRandomOutcomeById() throws Exception {
        AdditionalRandomOutcome additionalRandomOutcome = new AdditionalRandomOutcome();
        additionalRandomOutcome.setDescription("Tutu rose");
        additionalRandomOutcome.setIcon("Icon");
        additionalRandomOutcome.setId(1);
        when(this.additionalRandomOutcomeService.findAnAdditionalRandomOutcomeById((Integer) any()))
                .thenReturn(additionalRandomOutcome);
        when(this.additionalRandomOutcomeDtoMapper
                .additionalRandomOutcomeToAdditionalRandomOutcomeDto((AdditionalRandomOutcome) any()))
                .thenReturn(new AdditionalRandomOutcomeDto());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/additionalRandomOutcomes/get/{id}", 1);
        MockHttpServletRequestBuilder requestBuilder = getResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        MockMvcBuilders.standaloneSetup(this.additionalRandomOutcomeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":null,\"description\":null,\"icon\":null}"));
    }


    @Test
    void testGetAnAdditionalRandomOutcomeById2() throws Exception {
        AdditionalRandomOutcome additionalRandomOutcome = new AdditionalRandomOutcome();
        additionalRandomOutcome.setDescription("Tutu rose");
        additionalRandomOutcome.setIcon("Icon");
        additionalRandomOutcome.setId(1);
        when(this.additionalRandomOutcomeService.findAnAdditionalRandomOutcomeById((Integer) any()))
                .thenReturn(additionalRandomOutcome);
        when(this.additionalRandomOutcomeDtoMapper
                .additionalRandomOutcomeToAdditionalRandomOutcomeDto((AdditionalRandomOutcome) any()))
                .thenReturn(new AdditionalRandomOutcomeDto());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/additionalRandomOutcomes/get/{id}", 1);
        getResult.contentType("https://pomme.org/pomme");
        MockHttpServletRequestBuilder requestBuilder = getResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        MockMvcBuilders.standaloneSetup(this.additionalRandomOutcomeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":null,\"description\":null,\"icon\":null}"));
    }
}

