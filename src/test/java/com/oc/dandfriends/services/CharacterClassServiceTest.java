package com.oc.dandfriends.services;

import com.oc.dandfriends.entities.CharacterClass;
import com.oc.dandfriends.repositories.CharacterClassRepository;
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
class CharacterClassServiceTest {

    private AutoCloseable autoCloseable;
    private CharacterClassService characterClassServiceUnderTest;

    @Mock
    CharacterClassRepository characterClassRepository;

    @BeforeEach
    void setup() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        characterClassServiceUnderTest = new CharacterClassService(characterClassRepository);

    }
    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }


    @Test
    public void checkFindAllCharacterClasses_shouldCallCharacterClassesRepository() {
        //GIVEN WHEN
        characterClassServiceUnderTest.findAllCharacterClasses();
        //THEN
        verify(characterClassRepository).findAll();
    }



    @Test
    public void checkFindACharacterClassById_WhenIdIsNull_shouldThrowAnException() throws Exception {
        assertThatExceptionOfType(Exception.class).isThrownBy(() -> {
            characterClassServiceUnderTest.findACharacterClassById(null);
        }).withMessage("Invalid id");
    }

    @Test
    public void checkSaveACharacterClass_WhenCharacterClassIsValid_shouldCallCharacterClassRepository() throws Exception {
        //GIVEN WHEN
        CharacterClass characterClass = new CharacterClass(1,"paladin");
        characterClassServiceUnderTest.saveACharacterClass(characterClass);
        //THEN
        verify(characterClassRepository).save(characterClass);
    }

    @Test
    public void checkSaveACharacterClass_WhenCharacterClassIsNull_shouldThrowAnException() throws Exception {
        CharacterClass characterClass = null;
        assertThatExceptionOfType(Exception.class).isThrownBy(() -> {
            characterClassServiceUnderTest.saveACharacterClass(characterClass);
        }).withMessage("characterClass can't be null");
    }

    @Test
    public void checkDeleteACharacterClass_shouldCallCharacterClassRepository() throws Exception {
        //GIVEN WHEN
        CharacterClass characterClass = new CharacterClass(1,"paladin");
        characterClassServiceUnderTest.deleteACharacterClassById(characterClass.getId());
        //THEN
        verify(characterClassRepository).deleteById(1);
    }

    @Test
    public void checkDeleteACharacterClass_WhenCharacterClassIsNull_ShouldThrowAnException() throws Exception {
        //GIVEN WHEN
        CharacterClass characterClass = null;
        assertThatExceptionOfType(Exception.class).isThrownBy(() -> {
            characterClassServiceUnderTest.deleteACharacterClassById(null);
        }).withMessage("Invalid id");
    }


}