package com.oc.dandfriends.services;

import com.oc.dandfriends.entities.Role;
import com.oc.dandfriends.repositories.RoleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RoleServiceTest {

    private AutoCloseable autoCloseable;
    private RoleService roleServiceUnderTest;

    @Mock
    RoleRepository roleRepository;

    @BeforeEach
    void setup() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        roleServiceUnderTest = new RoleService(roleRepository);

    }
    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    public void checkFindAllRoles_shouldCallRoleRepository() {
        //GIVEN WHEN
        roleServiceUnderTest.findAllRole();
        //THEN
        verify(roleRepository).findAll();
    }



    @Test
    public void checkFindARoleById_WhenIdIsNull_shouldThrowAnException() throws Exception {
        assertThatExceptionOfType(Exception.class).isThrownBy(() -> {
            roleServiceUnderTest.findARoleById(null);
        }).withMessage("Invalid id");
    }

    @Test
    public void checkSaveARole_WhenRoleIsValid_shouldCallRoleRepository() throws Exception {
        //GIVEN WHEN
        Role roleUndertest = new Role(1,"ADMIN",null);
        roleServiceUnderTest.saveARole(roleUndertest);
        //THEN
        verify(roleRepository).save(roleUndertest);
    }

    @Test
    public void checkSaveARole_WhenRoleIsNull_shouldThrowAnException() throws Exception {
        Role roleUndertest = null;
        assertThatExceptionOfType(Exception.class).isThrownBy(() -> {
            roleServiceUnderTest.saveARole(roleUndertest);
        }).withMessage("Role can't be null");
    }

    @Test
    public void checkDeleteARole_shouldCallRoleRepository() throws Exception {
        //GIVEN WHEN
        Role roleUndertest = new Role(1,"ADMIN",null);
        roleServiceUnderTest.deleteARoleById(roleUndertest.getId());
        //THEN
        verify(roleRepository).deleteById(1);
    }

    @Test
    public void checkDeleteARole_WhenRoleIsNull_ShouldThrowAnException() throws Exception {
        //GIVEN WHEN
        Role roleUndertest = null;
        assertThatExceptionOfType(Exception.class).isThrownBy(() -> {
            roleServiceUnderTest.deleteARoleById(null);
        }).withMessage("Invalid id");
    }

}