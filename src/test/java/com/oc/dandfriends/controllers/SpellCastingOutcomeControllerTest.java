package com.oc.dandfriends.controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oc.dandfriends.dtos.SpellCastingOutcomeDto;
import com.oc.dandfriends.entities.SpellCastingOutcome;
import com.oc.dandfriends.mappers.SpellCastingOutcomeDtoMapper;
import com.oc.dandfriends.services.SpellCastingOutcomeService;

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

@ContextConfiguration(classes = {SpellCastingOutcomeController.class})
@ExtendWith(SpringExtension.class)
class SpellCastingOutcomeControllerTest {
    @Autowired
    private SpellCastingOutcomeController spellCastingOutcomeController;

    @MockBean
    private SpellCastingOutcomeDtoMapper spellCastingOutcomeDtoMapper;

    @MockBean
    private SpellCastingOutcomeService spellCastingOutcomeService;

    /**
     * Method under test: {@link SpellCastingOutcomeController#deleteASpellCastingOutcome(Integer, String)}
     */
    @Test
    void testDeleteASpellCastingOutcome() throws Exception {
        doNothing().when(this.spellCastingOutcomeService).deleteASpellCastingOutcomeById((Integer) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/spellCastingOutcomes/delete/{id}", 1);
        MockHttpServletRequestBuilder requestBuilder = deleteResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.spellCastingOutcomeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    /**
     * Method under test: {@link SpellCastingOutcomeController#saveASpellCastingOutcome(SpellCastingOutcomeDto, org.springframework.validation.BindingResult, String)}
     */
    @Test
    void testSaveASpellCastingOutcome() throws Exception {
        SpellCastingOutcome spellCastingOutcome = new SpellCastingOutcome();
        spellCastingOutcome.setDescription("The characteristics of someone or something");
        spellCastingOutcome.setHasARandomAdditionalEffect(true);
        spellCastingOutcome.setIcon("Icon");
        spellCastingOutcome.setId(1);
        spellCastingOutcome.setSpellWasASuccess(true);
        when(this.spellCastingOutcomeService.saveASpellCastingOutcome((SpellCastingOutcome) any()))
                .thenReturn(spellCastingOutcome);

        SpellCastingOutcome spellCastingOutcome1 = new SpellCastingOutcome();
        spellCastingOutcome1.setDescription("The characteristics of someone or something");
        spellCastingOutcome1.setHasARandomAdditionalEffect(true);
        spellCastingOutcome1.setIcon("Icon");
        spellCastingOutcome1.setId(1);
        spellCastingOutcome1.setSpellWasASuccess(true);
        when(this.spellCastingOutcomeDtoMapper.spellCastingOutcomeDtoToSpellCastingOutcome((SpellCastingOutcomeDto) any()))
                .thenReturn(spellCastingOutcome1);

        SpellCastingOutcomeDto spellCastingOutcomeDto = new SpellCastingOutcomeDto();
        spellCastingOutcomeDto.setDescription("The characteristics of someone or something");
        spellCastingOutcomeDto.setHasARandomAdditionalEffect(true);
        spellCastingOutcomeDto.setIcon("Icon");
        spellCastingOutcomeDto.setId(1);
        spellCastingOutcomeDto.setSpellWasASuccess(true);
        String content = (new ObjectMapper()).writeValueAsString(spellCastingOutcomeDto);
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/spellCastingOutcomes/add");
        MockHttpServletRequestBuilder requestBuilder = postResult.cookie(new Cookie("tokenDandFriends", "ABC123"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.spellCastingOutcomeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"description\":\"The characteristics of someone or something\",\"spellWasASuccess\":true,"
                                        + "\"hasARandomAdditionalEffect\":true,\"icon\":\"Icon\"}"));
    }

    /**
     * Method under test: {@link SpellCastingOutcomeController#deleteASpellCastingOutcome(Integer, String)}
     */
    @Test
    void testDeleteASpellCastingOutcome2() throws Exception {
        doNothing().when(this.spellCastingOutcomeService).deleteASpellCastingOutcomeById((Integer) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/spellCastingOutcomes/delete/{id}", 1);
        deleteResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = deleteResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.spellCastingOutcomeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    /**
     * Method under test: {@link SpellCastingOutcomeController#findAllSpellCastingOutcomes(String)}
     */
    @Test
    void testFindAllSpellCastingOutcomes() throws Exception {
        when(this.spellCastingOutcomeService.findAllSpellCastingOutcome()).thenReturn(new ArrayList<>());
        when(this.spellCastingOutcomeDtoMapper
                .spellCastingOutcomesToAllSpellCastingOutcomesDto((java.util.List<SpellCastingOutcome>) any()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/spellCastingOutcomes");
        MockHttpServletRequestBuilder requestBuilder = getResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        MockMvcBuilders.standaloneSetup(this.spellCastingOutcomeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link SpellCastingOutcomeController#findAllSpellCastingOutcomes(String)}
     */
    @Test
    void testFindAllSpellCastingOutcomes2() throws Exception {
        when(this.spellCastingOutcomeService.findAllSpellCastingOutcome()).thenReturn(new ArrayList<>());
        when(this.spellCastingOutcomeDtoMapper
                .spellCastingOutcomesToAllSpellCastingOutcomesDto((java.util.List<SpellCastingOutcome>) any()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/spellCastingOutcomes");
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = getResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        MockMvcBuilders.standaloneSetup(this.spellCastingOutcomeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link SpellCastingOutcomeController#generateASpellCastingOutcome(Boolean, String)}
     */
    @Test
    void testGenerateASpellCastingOutcome() throws Exception {
        SpellCastingOutcome spellCastingOutcome = new SpellCastingOutcome();
        spellCastingOutcome.setDescription("The characteristics of someone or something");
        spellCastingOutcome.setHasARandomAdditionalEffect(true);
        spellCastingOutcome.setIcon("Icon");
        spellCastingOutcome.setId(1);
        spellCastingOutcome.setSpellWasASuccess(true);
        when(this.spellCastingOutcomeService.generateASpellOutcome((Boolean) any())).thenReturn(spellCastingOutcome);
        when(this.spellCastingOutcomeDtoMapper.spellCastingOutcomeToSpellCastingOutcomeDto((SpellCastingOutcome) any()))
                .thenReturn(new SpellCastingOutcomeDto());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/spellCastingOutcomes/generate/{spellWasASuccess}", true);
        MockHttpServletRequestBuilder requestBuilder = getResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        MockMvcBuilders.standaloneSetup(this.spellCastingOutcomeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"description\":null,\"spellWasASuccess\":null,\"hasARandomAdditionalEffect\":null,\"icon\":null}"));
    }

    /**
     * Method under test: {@link SpellCastingOutcomeController#generateASpellCastingOutcome(Boolean, String)}
     */
    @Test
    void testGenerateASpellCastingOutcome2() throws Exception {
        SpellCastingOutcome spellCastingOutcome = new SpellCastingOutcome();
        spellCastingOutcome.setDescription("The characteristics of someone or something");
        spellCastingOutcome.setHasARandomAdditionalEffect(true);
        spellCastingOutcome.setIcon("Icon");
        spellCastingOutcome.setId(1);
        spellCastingOutcome.setSpellWasASuccess(true);
        when(this.spellCastingOutcomeService.generateASpellOutcome((Boolean) any())).thenReturn(spellCastingOutcome);
        when(this.spellCastingOutcomeDtoMapper.spellCastingOutcomeToSpellCastingOutcomeDto((SpellCastingOutcome) any()))
                .thenReturn(new SpellCastingOutcomeDto());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders
                .get("/spellCastingOutcomes/generate/{spellWasASuccess}", true);
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = getResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        MockMvcBuilders.standaloneSetup(this.spellCastingOutcomeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"description\":null,\"spellWasASuccess\":null,\"hasARandomAdditionalEffect\":null,\"icon\":null}"));
    }

    /**
     * Method under test: {@link SpellCastingOutcomeController#getASpellCastingOutcomeById(Integer, String)}
     */
    @Test
    void testGetASpellCastingOutcomeById() throws Exception {
        SpellCastingOutcome spellCastingOutcome = new SpellCastingOutcome();
        spellCastingOutcome.setDescription("The characteristics of someone or something");
        spellCastingOutcome.setHasARandomAdditionalEffect(true);
        spellCastingOutcome.setIcon("Icon");
        spellCastingOutcome.setId(1);
        spellCastingOutcome.setSpellWasASuccess(true);
        when(this.spellCastingOutcomeService.findASpellCastingOutcomeById((Integer) any())).thenReturn(spellCastingOutcome);
        when(this.spellCastingOutcomeDtoMapper.spellCastingOutcomeToSpellCastingOutcomeDto((SpellCastingOutcome) any()))
                .thenReturn(new SpellCastingOutcomeDto());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/spellCastingOutcomes/get/{id}", 1);
        MockHttpServletRequestBuilder requestBuilder = getResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        MockMvcBuilders.standaloneSetup(this.spellCastingOutcomeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"description\":null,\"spellWasASuccess\":null,\"hasARandomAdditionalEffect\":null,\"icon\":null}"));
    }

    /**
     * Method under test: {@link SpellCastingOutcomeController#getASpellCastingOutcomeById(Integer, String)}
     */
    @Test
    void testGetASpellCastingOutcomeById2() throws Exception {
        SpellCastingOutcome spellCastingOutcome = new SpellCastingOutcome();
        spellCastingOutcome.setDescription("The characteristics of someone or something");
        spellCastingOutcome.setHasARandomAdditionalEffect(true);
        spellCastingOutcome.setIcon("Icon");
        spellCastingOutcome.setId(1);
        spellCastingOutcome.setSpellWasASuccess(true);
        when(this.spellCastingOutcomeService.findASpellCastingOutcomeById((Integer) any())).thenReturn(spellCastingOutcome);
        when(this.spellCastingOutcomeDtoMapper.spellCastingOutcomeToSpellCastingOutcomeDto((SpellCastingOutcome) any()))
                .thenReturn(new SpellCastingOutcomeDto());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/spellCastingOutcomes/get/{id}", 1);
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = getResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        MockMvcBuilders.standaloneSetup(this.spellCastingOutcomeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":null,\"description\":null,\"spellWasASuccess\":null,\"hasARandomAdditionalEffect\":null,\"icon\":null}"));
    }
}

