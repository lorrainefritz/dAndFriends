package com.oc.dandfriends.controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oc.dandfriends.dtos.CharacterClassDto;
import com.oc.dandfriends.entities.CharacterClass;
import com.oc.dandfriends.mappers.CharacterClassDtoMapper;
import com.oc.dandfriends.services.CharacterClassService;

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

@ContextConfiguration(classes = {CharacterClassController.class})
@ExtendWith(SpringExtension.class)
class CharacterClassControllerTest {
    @Autowired
    private CharacterClassController characterClassController;

    @MockBean
    private CharacterClassDtoMapper characterClassDtoMapper;

    @MockBean
    private CharacterClassService characterClassService;

    /**
     * Method under test: {@link CharacterClassController#deleteACharacterClass(Integer, String)}
     */
    @Test
    void testDeleteACharacterClass() throws Exception {
        doNothing().when(this.characterClassService).deleteACharacterClassById((Integer) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/characterClasses/delete/{id}", 1);
        MockHttpServletRequestBuilder requestBuilder = deleteResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.characterClassController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    /**
     * Method under test: {@link CharacterClassController#findAllCharacterClasses(String)}
     */
    @Test
    void testFindAllCharacterClasses() throws Exception {
        when(this.characterClassService.findAllCharacterClasses()).thenReturn(new ArrayList<>());
        when(this.characterClassDtoMapper.characterClassesToAllCharacterClassesDto((java.util.List<CharacterClass>) any()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/characterClasses");
        MockHttpServletRequestBuilder requestBuilder = getResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        MockMvcBuilders.standaloneSetup(this.characterClassController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CharacterClassController#findAllCharacterClasses(String)}
     */
    @Test
    void testFindAllCharacterClasses2() throws Exception {
        when(this.characterClassService.findAllCharacterClasses()).thenReturn(new ArrayList<>());
        when(this.characterClassDtoMapper.characterClassesToAllCharacterClassesDto((java.util.List<CharacterClass>) any()))
                .thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/characterClasses");
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = getResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        MockMvcBuilders.standaloneSetup(this.characterClassController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CharacterClassController#saveACharacterClass(CharacterClassDto, org.springframework.validation.BindingResult, String)}
     */
    @Test
    void testSaveACharacterClass() throws Exception {
        CharacterClass characterClass = new CharacterClass();
        characterClass.setCharacterClassName("Character Class Name");
        characterClass.setId(1);
        when(this.characterClassService.saveACharacterClass((CharacterClass) any())).thenReturn(characterClass);

        CharacterClass characterClass1 = new CharacterClass();
        characterClass1.setCharacterClassName("Character Class Name");
        characterClass1.setId(1);
        when(this.characterClassDtoMapper.characterClassDtoToCharacterClass((CharacterClassDto) any()))
                .thenReturn(characterClass1);

        CharacterClassDto characterClassDto = new CharacterClassDto();
        characterClassDto.setCharacterClassName("Character Class Name");
        characterClassDto.setId(1);
        String content = (new ObjectMapper()).writeValueAsString(characterClassDto);
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/characterClasses/add");
        MockHttpServletRequestBuilder requestBuilder = postResult.cookie(new Cookie("tokenDandFriends", "ABC123"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.characterClassController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"id\":1,\"characterClassName\":\"Character Class Name\"}"));
    }

    /**
     * Method under test: {@link CharacterClassController#deleteACharacterClass(Integer, String)}
     */
    @Test
    void testDeleteACharacterClass2() throws Exception {
        doNothing().when(this.characterClassService).deleteACharacterClassById((Integer) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/characterClasses/delete/{id}", 1);
        deleteResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = deleteResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.characterClassController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted());
    }

    /**
     * Method under test: {@link CharacterClassController#getACharacterClassById(Integer, String)}
     */
    @Test
    void testGetACharacterClassById() throws Exception {
        CharacterClass characterClass = new CharacterClass();
        characterClass.setCharacterClassName("Character Class Name");
        characterClass.setId(1);
        when(this.characterClassService.findACharacterClassById((Integer) any())).thenReturn(characterClass);
        when(this.characterClassDtoMapper.characterClassToCharacterClassDto((CharacterClass) any()))
                .thenReturn(new CharacterClassDto());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/characterClasses/get/{id}", 1);
        MockHttpServletRequestBuilder requestBuilder = getResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        MockMvcBuilders.standaloneSetup(this.characterClassController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":null,\"characterClassName\":null}"));
    }

    /**
     * Method under test: {@link CharacterClassController#getACharacterClassById(Integer, String)}
     */
    @Test
    void testGetACharacterClassById2() throws Exception {
        CharacterClass characterClass = new CharacterClass();
        characterClass.setCharacterClassName("Character Class Name");
        characterClass.setId(1);
        when(this.characterClassService.findACharacterClassById((Integer) any())).thenReturn(characterClass);
        when(this.characterClassDtoMapper.characterClassToCharacterClassDto((CharacterClass) any()))
                .thenReturn(new CharacterClassDto());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/characterClasses/get/{id}", 1);
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = getResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        MockMvcBuilders.standaloneSetup(this.characterClassController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":null,\"characterClassName\":null}"));
    }
}

