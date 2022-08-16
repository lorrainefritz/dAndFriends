package com.oc.dandfriends.controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oc.dandfriends.dtos.AppUserDto;
import com.oc.dandfriends.entities.AppUser;
import com.oc.dandfriends.entities.Role;
import com.oc.dandfriends.mappers.AppUserDtoMapper;
import com.oc.dandfriends.security.UserAuthentication;
import com.oc.dandfriends.services.AppUserService;

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

@ContextConfiguration(classes = {AppUserController.class})
@ExtendWith(SpringExtension.class)
class AppUserControllerTest {
    @Autowired
    private AppUserController appUserController;

    @MockBean
    private AppUserDtoMapper appUserDtoMapper;

    @MockBean
    private AppUserService appUserService;

    @MockBean
    private UserAuthentication userAuthentication;


    @Test
    void testAuthenticate() throws Exception {
        when(this.userAuthentication.successfulAuthentication((String) any(), (String) any()))
                .thenReturn("Successful Authentication");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login")
                .param("password", "123")
                .param("username", "paul");
        MockMvcBuilders.standaloneSetup(this.appUserController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Successful Authentication"));
    }


    @Test
    void testFindAllAppUsers() throws Exception {
        when(this.appUserService.findAllAppUsers()).thenReturn(new ArrayList<>());
        when(this.appUserDtoMapper.appUsersToAllAppUsersDto((java.util.List<AppUser>) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/appUsers");
        MockHttpServletRequestBuilder requestBuilder = getResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        MockMvcBuilders.standaloneSetup(this.appUserController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testFindAllAppUsers2() throws Exception {
        when(this.appUserService.findAllAppUsers()).thenReturn(new ArrayList<>());
        when(this.appUserDtoMapper.appUsersToAllAppUsersDto((java.util.List<AppUser>) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/appUsers");
        getResult.contentType("https://pomme.org/pomme");
        MockHttpServletRequestBuilder requestBuilder = getResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        MockMvcBuilders.standaloneSetup(this.appUserController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testSaveAnAppUser() throws Exception {
        Role role = new Role();
        role.setAppUsers(new ArrayList<>());
        role.setId(1);
        role.setRoleName("Role Name");

        AppUser appUser = new AppUser();
        appUser.setId(1);
        appUser.setPassword("iloveyou");
        appUser.setPseudo("Pseudo");
        appUser.setRole(role);
        appUser.setUsername("janedoe");
        when(this.appUserService.saveAnAppUser((AppUser) any())).thenReturn(appUser);

        Role role1 = new Role();
        role1.setAppUsers(new ArrayList<>());
        role1.setId(1);
        role1.setRoleName("Role Name");

        AppUser appUser1 = new AppUser();
        appUser1.setId(1);
        appUser1.setPassword("iloveyou");
        appUser1.setPseudo("Pseudo");
        appUser1.setRole(role1);
        appUser1.setUsername("janedoe");
        when(this.appUserDtoMapper.appUserDtoToAppUser((AppUserDto) any())).thenReturn(appUser1);

        Role role2 = new Role();
        role2.setAppUsers(new ArrayList<>());
        role2.setId(1);
        role2.setRoleName("Role Name");

        AppUserDto appUserDto = new AppUserDto();
        appUserDto.setId(1);
        appUserDto.setPseudo("Pseudo");
        appUserDto.setRole(role2);
        appUserDto.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(appUserDto);
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/appUsers/add");
        MockHttpServletRequestBuilder requestBuilder = postResult.cookie(new Cookie("tokenDandFriends", "ABC123"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.appUserController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"username\":\"janedoe\",\"pseudo\":\"Pseudo\",\"role\":{\"id\":1,\"roleName\":\"Role Name\",\"appUsers\":[]}}"));
    }


    @Test
    void testAuthenticate2() throws Exception {
        when(this.userAuthentication.successfulAuthentication((String) any(), (String) any()))
                .thenReturn("Successful Authentication");
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/login");
        postResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = postResult.param("password", "foo").param("username", "foo");
        MockMvcBuilders.standaloneSetup(this.appUserController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Successful Authentication"));
    }


    @Test
    void testGetAnAppUserById() throws Exception {
        Role role = new Role();
        role.setAppUsers(new ArrayList<>());
        role.setId(1);
        role.setRoleName("Role Name");

        AppUser appUser = new AppUser();
        appUser.setId(1);
        appUser.setPassword("iloveyou");
        appUser.setPseudo("Pseudo");
        appUser.setRole(role);
        appUser.setUsername("janedoe");
        when(this.appUserService.findAnAppUserById((Integer) any())).thenReturn(appUser);
        when(this.appUserDtoMapper.appUserToAppUserDto((AppUser) any())).thenReturn(new AppUserDto());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/appUsers/get/{id}", 1);
        MockHttpServletRequestBuilder requestBuilder = getResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        MockMvcBuilders.standaloneSetup(this.appUserController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"id\":null,\"username\":null,\"pseudo\":null,\"role\":null}"));
    }


    @Test
    void testGetAnAppUserById2() throws Exception {
        Role role = new Role();
        role.setAppUsers(new ArrayList<>());
        role.setId(1);
        role.setRoleName("Role Name");

        AppUser appUser = new AppUser();
        appUser.setId(1);
        appUser.setPassword("iloveyou");
        appUser.setPseudo("Pseudo");
        appUser.setRole(role);
        appUser.setUsername("janedoe");
        when(this.appUserService.findAnAppUserById((Integer) any())).thenReturn(appUser);
        when(this.appUserDtoMapper.appUserToAppUserDto((AppUser) any())).thenReturn(new AppUserDto());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/appUsers/get/{id}", 1);
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = getResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        MockMvcBuilders.standaloneSetup(this.appUserController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"id\":null,\"username\":null,\"pseudo\":null,\"role\":null}"));
    }
}

