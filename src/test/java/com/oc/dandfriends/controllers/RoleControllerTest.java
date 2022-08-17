package com.oc.dandfriends.controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oc.dandfriends.dtos.RoleDto;
import com.oc.dandfriends.entities.Role;
import com.oc.dandfriends.mappers.RoleDtoMapper;
import com.oc.dandfriends.services.RoleService;

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

@ContextConfiguration(classes = {RoleController.class})
@ExtendWith(SpringExtension.class)
class RoleControllerTest {
    @Autowired
    private RoleController roleController;

    @MockBean
    private RoleDtoMapper roleDtoMapper;

    @MockBean
    private RoleService roleService;


    @Test
    void testAddARole() throws Exception {
        Role role = new Role();
        role.setAppUsers(new ArrayList<>());
        role.setId(1);
        role.setRoleName("Role Name");
        when(this.roleService.saveARole((Role) any())).thenReturn(role);

        Role role1 = new Role();
        role1.setAppUsers(new ArrayList<>());
        role1.setId(1);
        role1.setRoleName("Role Name");
        when(this.roleDtoMapper.roleDtoToRole((RoleDto) any())).thenReturn(role1);

        RoleDto roleDto = new RoleDto();
        roleDto.setId(1);
        roleDto.setRoleName("Role Name");
        String content = (new ObjectMapper()).writeValueAsString(roleDto);
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/roles/add");
        MockHttpServletRequestBuilder requestBuilder = postResult.cookie(new Cookie("tokenDandFriends", "ABC123"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.roleController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"roleName\":\"Role Name\"}"));
    }


    @Test
    void testDeleteARole() throws Exception {
        doNothing().when(this.roleService).deleteARoleById((Integer) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/roles/delete/{id}", 1);
        MockHttpServletRequestBuilder requestBuilder = deleteResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.roleController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted());
    }


    @Test
    void testDeleteARole2() throws Exception {
        doNothing().when(this.roleService).deleteARoleById((Integer) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/roles/delete/{id}", 1);
        deleteResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = deleteResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.roleController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isAccepted());
    }


    @Test
    void testFindAllRoles() throws Exception {
        when(this.roleService.findAllRole()).thenReturn(new ArrayList<>());
        when(this.roleDtoMapper.rolesToAllRolesDto((java.util.List<Role>) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/roles");
        MockHttpServletRequestBuilder requestBuilder = getResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        MockMvcBuilders.standaloneSetup(this.roleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testFindAllRoles2() throws Exception {
        when(this.roleService.findAllRole()).thenReturn(new ArrayList<>());
        when(this.roleDtoMapper.rolesToAllRolesDto((java.util.List<Role>) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/roles");
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = getResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        MockMvcBuilders.standaloneSetup(this.roleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testGetARoleById() throws Exception {
        Role role = new Role();
        role.setAppUsers(new ArrayList<>());
        role.setId(1);
        role.setRoleName("Role Name");
        when(this.roleService.findARoleById((Integer) any())).thenReturn(role);
        when(this.roleDtoMapper.roleToRoleDto((Role) any())).thenReturn(new RoleDto());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/roles/get/{id}", 1);
        MockHttpServletRequestBuilder requestBuilder = getResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        MockMvcBuilders.standaloneSetup(this.roleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":null,\"roleName\":null}"));
    }


    @Test
    void testGetARoleById2() throws Exception {
        Role role = new Role();
        role.setAppUsers(new ArrayList<>());
        role.setId(1);
        role.setRoleName("Role Name");
        when(this.roleService.findARoleById((Integer) any())).thenReturn(role);
        when(this.roleDtoMapper.roleToRoleDto((Role) any())).thenReturn(new RoleDto());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/roles/get/{id}", 1);
        getResult.contentType("https://example.org/example");
        MockHttpServletRequestBuilder requestBuilder = getResult.cookie(new Cookie("tokenDandFriends", "ABC123"));
        MockMvcBuilders.standaloneSetup(this.roleController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":null,\"roleName\":null}"));
    }
}

