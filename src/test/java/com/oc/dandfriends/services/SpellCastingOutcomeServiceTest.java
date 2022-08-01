package com.oc.dandfriends.services;

import com.oc.dandfriends.entities.SpellCastingOutcome;
import com.oc.dandfriends.repositories.SpellCastingOutcomeRepository;
import com.oc.dandfriends.repositories.SpellRepository;
import com.oc.dandfriends.util.GenerateARandomUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SpellCastingOutcomeServiceTest {

    private AutoCloseable autoCloseable;
    private SpellCastingOutcomeService spellCastingOutcomeService;

    @Mock
    private SpellCastingOutcomeRepository spellCastingOutcomeRepository;
    @Mock
    private GenerateARandomUtil generateARandomUtil;

    @BeforeEach
    void setup() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        spellCastingOutcomeService = new SpellCastingOutcomeService(spellCastingOutcomeRepository, generateARandomUtil);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void checkGenerateASpellOutcome_WhenSpellWasASuccessIsTrue_ShouldReturnASpellCastingOutcome() {
        //GIVEN

        SpellCastingOutcome spellCastingOutcome1 = new SpellCastingOutcome(1, "A", true, false, null);
        SpellCastingOutcome spellCastingOutcome2 = new SpellCastingOutcome(2, "B", true, false, null);
        SpellCastingOutcome spellCastingOutcome3 = new SpellCastingOutcome(3, "C", true, false, null);
        SpellCastingOutcome spellCastingOutcome4 = new SpellCastingOutcome(4, "D", true, false, null);
        SpellCastingOutcome spellCastingOutcome5 = new SpellCastingOutcome(5, "E", true, false, null);

        List<SpellCastingOutcome> spellCastingOutcomes = new ArrayList<>();
        spellCastingOutcomes.add(spellCastingOutcome1);
        spellCastingOutcomes.add(spellCastingOutcome2);
        spellCastingOutcomes.add(spellCastingOutcome3);
        spellCastingOutcomes.add(spellCastingOutcome4);
        spellCastingOutcomes.add(spellCastingOutcome5);

        //WHEN
        when(spellCastingOutcomeRepository.findBySpellWasASuccess(true)).thenReturn(spellCastingOutcomes);
        when(generateARandomUtil.generateARandom(spellCastingOutcomes.size())).thenReturn(3);
        //THEN
        SpellCastingOutcome spellCastingOutcomeUnderTest = spellCastingOutcomeService.generateASpellOutcome(true);
        assertThat(spellCastingOutcomeUnderTest.getDescription()).isEqualTo(spellCastingOutcome4.getDescription());
    }

}