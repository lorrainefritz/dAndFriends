package com.oc.dandfriends.services;

import com.oc.dandfriends.entities.AppUser;
import com.oc.dandfriends.entities.Role;
import com.oc.dandfriends.repositories.AppUserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class AppUserServiceTest {
    private AutoCloseable autoCloseable;
    private AppUserService userServiceUndertest;
    private Role role;

    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AppUserRepository userRepository;

    @BeforeEach
    void setup() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        userServiceUndertest = new AppUserService(userRepository);
    }
    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
        role = new Role();
    }


    @Test
    public void checkFindAllUsers_shouldCallUserRepository() {
        //GIVEN WHEN
        userServiceUndertest.findAllAppUsers();
        //THEN
        verify(userRepository).findAll();
    }
    @Test
    public void checkSaveAUser_WhenUserIsValid_shouldCallUserRepository() throws Exception {
        //GIVEN WHEN
        AppUser userUnderTest = new AppUser(1,"paul@gmail.com","Paul","123",role);
        userServiceUndertest.saveAnAppUser(userUnderTest);
        //THEN
        verify(userRepository).save(userUnderTest);
    }



    @Test
    public void checkFindAUserById_WhenIdIsNull_shouldThrowAnException() throws Exception {
        assertThatExceptionOfType(Exception.class).isThrownBy(() -> {
            userServiceUndertest.findAnAppUserById(null);
        }).withMessage("Invalid id");
    }



    @Test
    public void checkSaveAUser_WhenUserIsNull_shouldThrowAnException() throws Exception {
        AppUser userUnderTest = null;
        assertThatExceptionOfType(Exception.class).isThrownBy(() -> {
            userServiceUndertest.saveAnAppUser(userUnderTest);
        }).withMessage("AppUser can't be null");
    }


    @Test
    public void checkLoadUserByUserName_whenGivenAValidUsername_shouldReturnAUser() throws Exception {
        //GIVEN

        role = new Role(1,"ROLE_USER",null);
        AppUser userUnderTest = new AppUser(1,"paul@gmail.com","Paul","123",role);
        String username ="paul@gmail.com";
        //WHEN
        when(userRepository.findByUsername(username)).thenReturn(userUnderTest);

        //THEN
        userServiceUndertest.findAnAppUserByUsername(username);
        verify(userRepository).findByUsername(username);
    }

    @Test
    public void checkLoadUserByUserName_WhenUserIsNull_ShouldUsernameNotFoundException() throws Exception {
        //GIVEN WHEN
        assertThatExceptionOfType(UsernameNotFoundException.class).isThrownBy(() -> {
            userServiceUndertest.loadUserByUsername("");
        }).withMessage("Invalid email or Password");
    }


}