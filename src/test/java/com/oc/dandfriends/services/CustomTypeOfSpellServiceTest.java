package com.oc.dandfriends.services;

import com.oc.dandfriends.entities.CustomTypeOfSpell;
import com.oc.dandfriends.repositories.CustomTypeOfSpellRepository;
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
class CustomTypeOfSpellServiceTest {

    private AutoCloseable autoCloseable;
    private CustomTypeOfSpellService customTypeOfSpellServiceUnderTest;

    @Mock
    CustomTypeOfSpellRepository customTypeOfSpellRepository;

    @BeforeEach
    void setup() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        customTypeOfSpellServiceUnderTest = new CustomTypeOfSpellService(customTypeOfSpellRepository);

    }
    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    public void checkFindAllCustomTypeOfSpells_shouldCallCustomTypeOfSpellRepository() {
        //GIVEN WHEN
        customTypeOfSpellServiceUnderTest.findAllCustomTypeOfSpell();
        //THEN
        verify(customTypeOfSpellRepository).findAll();
    }


    @Test
    public void checkFindACustomTypeOfSpellById_WhenIdIsNull_shouldThrowAnException() throws Exception {
        assertThatExceptionOfType(Exception.class).isThrownBy(() -> {
            customTypeOfSpellServiceUnderTest.findACustomTypeOfSpellById(null);
        }).withMessage("Invalid id");
    }

    @Test
    public void checkSaveACustomTypeOfSpell_WhenCustomTypeOfSpellIsValid_shouldCallCustomTypeOfSpellRepository() throws Exception {
        //GIVEN WHEN
        CustomTypeOfSpell customTypeOfSpell= new CustomTypeOfSpell(1,"def","123");
        customTypeOfSpellServiceUnderTest.saveACustomTypeOfSpell(customTypeOfSpell);
        //THEN
        verify(customTypeOfSpellRepository).save(customTypeOfSpell);
    }

    @Test
    public void checkSaveACustomTypeOfSpell_WhenCustomTypeOfSpellIsNull_shouldThrowAnException() throws Exception {
        CustomTypeOfSpell customTypeOfSpell= null;
        assertThatExceptionOfType(Exception.class).isThrownBy(() -> {
            customTypeOfSpellServiceUnderTest.saveACustomTypeOfSpell(customTypeOfSpell);
        }).withMessage("CustomTypeOfSpell can't be null");
    }

    @Test
    public void checkDeleteACustomTypeOfSpell_shouldCallCustomTypeOfSpellRepository() throws Exception {
        //GIVEN WHEN
        CustomTypeOfSpell customTypeOfSpell= new CustomTypeOfSpell(1,"def","123");
        customTypeOfSpellServiceUnderTest.deleteACustomTypeOfSpellById(customTypeOfSpell.getId());
        //THEN
        verify(customTypeOfSpellRepository).deleteById(1);
    }

    @Test
    public void checkDeleteACustomTypeOfSpell_WhenCustomTypeOfSpellIsNull_ShouldThrowAnException() throws Exception {
        //GIVEN WHEN
        CustomTypeOfSpell customTypeOfSpell = null;
        assertThatExceptionOfType(Exception.class).isThrownBy(() -> {
            customTypeOfSpellServiceUnderTest.deleteACustomTypeOfSpellById(null);
        }).withMessage("Invalid id");
    }
}