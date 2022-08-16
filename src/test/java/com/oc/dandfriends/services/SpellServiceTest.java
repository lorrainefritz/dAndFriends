package com.oc.dandfriends.services;

import com.oc.dandfriends.entities.*;
import com.oc.dandfriends.enums.School;
import com.oc.dandfriends.repositories.SpellRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SpellServiceTest {

    private SpellService spellServiceUnderTest;
    private AutoCloseable autoCloseable;
    private CustomTypeOfSpellService customTypeOfSpellService;
    @Mock
    SpellRepository spellRepository;

    @BeforeEach
    void setup() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        spellServiceUnderTest = new SpellService(spellRepository,customTypeOfSpellService);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    public void checkFindAllSpells_shouldCalSpellRepository() {
        //GIVEN WHEN
        spellServiceUnderTest.findAllSpells();
        //THEN
        verify(spellRepository).findAll();
    }

    @Test
    public void checkFindAllSpellsByACustomTypeOfSpell_shouldCallSpellRepository_whenGivenAValidId() throws Exception {
        //GIVEN WHEN
        spellServiceUnderTest.findAllSpellsOfACustomTypeOfSpell(1);
        //THEN
        verify(spellRepository).findSpellsByCustomTypeOfSpell(1);
    }


    @Test
    public void checkFindASpellById_WhenIdIsNull_shouldThrowAnException() throws Exception {
        assertThatExceptionOfType(Exception.class).isThrownBy(() -> {
            spellServiceUnderTest.findASpellById(null);
        }).withMessage("Invalid id");
    }

    @Test
    public void checkSaveASpell_WhenRoleIsValid_shouldCallSpellRepository() throws Exception {
        //GIVEN WHEN
        List<CharacterClass> characterClasses = new ArrayList<>();
        List<ComponentOfSpell> componentsOfSpell = new ArrayList<>();
        Spell spell = new Spell(1, "Boule de feu", new CustomTypeOfSpell(), School.EVOCATION, 3,componentsOfSpell,characterClasses, "1 action simple", "longue", "6m de rayon", "instantanée", "reflexes", true, "shortDescription", "FullDescription", "null");
        spellServiceUnderTest.saveASpell(spell);
        //THEN
        verify(spellRepository).save(spell);
    }

    @Test
    public void checkSaveASpell_WhenSpellIsNull_shouldThrowAnException() throws Exception {
        Spell spell = null;
        assertThatExceptionOfType(Exception.class).isThrownBy(() -> {
            spellServiceUnderTest.saveASpell(spell);
        }).withMessage("Spell can't be null");
    }

    @Test
    public void checkDeleteASpell_shouldCallSpellRepository() throws Exception {
        //GIVEN WHEN
        List<CharacterClass> characterClasses = new ArrayList<>();
        List<ComponentOfSpell> componentsOfSpell = new ArrayList<>();
        Spell spell = new Spell(1, "Boule de feu", new CustomTypeOfSpell(), School.EVOCATION, 3,componentsOfSpell,characterClasses, "1 action simple", "longue", "6m de rayon", "instantanée", "reflexes", true, "shortDescription", "FullDescription", "null");
        spellServiceUnderTest.deleteASpell(spell.getId());
        //THEN
        verify(spellRepository).deleteById(1);
    }

    @Test
    public void checkDeleteASpell_WhenSpellIsNull_ShouldThrowAnException() throws Exception {
        //GIVEN WHEN
        Spell spell = null;
        assertThatExceptionOfType(Exception.class).isThrownBy(() -> {
            spellServiceUnderTest.deleteASpell(null);
        }).withMessage("Invalid id");
    }
}
