package com.oc.dandfriends.services;

import com.oc.dandfriends.entities.ComponentOfSpell;
import com.oc.dandfriends.repositories.ComponentOfSpellRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ComponentServiceTest {
    private AutoCloseable autoCloseable;
    private ComponentService componentServiceUnderTest;

    @Mock
    ComponentOfSpellRepository componentOfSpellRepository;

    @BeforeEach
    void setup() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        componentServiceUnderTest = new ComponentService(componentOfSpellRepository);

    }
    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    public void checkFindAllComponents_shouldCallComponentRepository() {
        //GIVEN WHEN
        componentServiceUnderTest.findAllComponents();
        //THEN
        verify(componentOfSpellRepository).findAll();
    }



    @Test
    public void checkFindAComponentById_WhenIdIsNull_shouldThrowAnException() throws Exception {
        assertThatExceptionOfType(Exception.class).isThrownBy(() -> {
            componentServiceUnderTest.findAComponentById(null);
        }).withMessage("Invalid id");
    }

    @Test
    public void checkSaveAComponent_WhenComponentIsValid_shouldCallComponentRepository() throws Exception {
        //GIVEN WHEN
        ComponentOfSpell componentOfSpell = new ComponentOfSpell(1,"main","123");
        componentServiceUnderTest.saveAComponent(componentOfSpell);
        //THEN
        verify(componentOfSpellRepository).save(componentOfSpell);
    }

    @Test
    public void checkSaveAComponent_WhenComponentIsNull_shouldThrowAnException() throws Exception {
        ComponentOfSpell componentOfSpell = null;
        assertThatExceptionOfType(Exception.class).isThrownBy(() -> {
            componentServiceUnderTest.saveAComponent(componentOfSpell);
        }).withMessage("component can't be null");
    }

    @Test
    public void checkDeleteAComponent_shouldCallComponentRepository() throws Exception {
        //GIVEN WHEN
        ComponentOfSpell componentOfSpell = new ComponentOfSpell(1,"main","123");
        componentServiceUnderTest.deleteAComponentById(componentOfSpell.getId());
        //THEN
        verify(componentOfSpellRepository).deleteById(1);
    }

    @Test
    public void checkDeleteAComponent_WhenComponentIsNull_ShouldThrowAnException() throws Exception {
        //GIVEN WHEN
        ComponentOfSpell componentOfSpell = null;
        assertThatExceptionOfType(Exception.class).isThrownBy(() -> {
            componentServiceUnderTest.deleteAComponentById(null);
        }).withMessage("Invalid id");
    }


}