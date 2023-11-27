package com.oc.dandfriends.repositories;

import com.oc.dandfriends.entities.SpellCastingOutcome;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class SpellCastingOutcomeRepositoryTest {
    @Autowired
    private SpellCastingOutcomeRepository spellCastingOutcomeRepository;

    private SpellCastingOutcome spellCastingOutcome1;
    private SpellCastingOutcome spellCastingOutcome2;
    private SpellCastingOutcome spellCastingOutcome3;
    private SpellCastingOutcome spellCastingOutcome4;
    private SpellCastingOutcome spellCastingOutcome5;
    private SpellCastingOutcome spellCastingOutcome6;
    private SpellCastingOutcome spellCastingOutcome7;
    private SpellCastingOutcome spellCastingOutcome8;


    @AfterEach
    void tearDown() {
        spellCastingOutcomeRepository.deleteAll();
    }

    @Test
    void checkFindBySpellIsASuccess_whenSpellIsASuccessIsFalse_shouldReturnAListOfNegativeOutcomes() {
        //GIVEN

        spellCastingOutcome1 = new SpellCastingOutcome(1,"A",false,false,null);
        spellCastingOutcome2 = new SpellCastingOutcome(2,"B",false,false,null);
        spellCastingOutcome3 = new SpellCastingOutcome(3,"C",true,false,null);
        spellCastingOutcome4 = new SpellCastingOutcome(4,"D",false,false,null);
        spellCastingOutcome5 = new SpellCastingOutcome(5,"E",true,false,null);
        spellCastingOutcome6 = new SpellCastingOutcome(6,"F",false,false,null);
        spellCastingOutcome7 = new SpellCastingOutcome(7,"G",true,false,null);
        spellCastingOutcome8 = new SpellCastingOutcome(8,"H",true,false,null);

        spellCastingOutcomeRepository.save(spellCastingOutcome1);
        spellCastingOutcomeRepository.save(spellCastingOutcome2);
        spellCastingOutcomeRepository.save(spellCastingOutcome3);
        spellCastingOutcomeRepository.save(spellCastingOutcome4);
        spellCastingOutcomeRepository.save(spellCastingOutcome5);
        spellCastingOutcomeRepository.save(spellCastingOutcome6);
        spellCastingOutcomeRepository.save(spellCastingOutcome7);
        spellCastingOutcomeRepository.save(spellCastingOutcome8);

        //WHEN
        List<SpellCastingOutcome> spellCastingOutcomeList = spellCastingOutcomeRepository.findBySpellWasASuccess(false);
        //THEN
        assertThat(spellCastingOutcomeList.size()).isEqualTo(4);
        assertThat(spellCastingOutcomeList.get(0).getDescription()).isEqualTo(spellCastingOutcome1.getDescription());
        assertThat(spellCastingOutcomeList.get(1).getDescription()).isEqualTo(spellCastingOutcome2.getDescription());
        assertThat(spellCastingOutcomeList.get(2).getDescription()).isEqualTo(spellCastingOutcome4.getDescription());
        assertThat(spellCastingOutcomeList.get(3).getDescription()).isEqualTo(spellCastingOutcome6.getDescription());
    }

    @Test
    void checkFindBySpellIsASuccess_whenSpellIsASuccessIsTrue_shouldReturnAListOfPositiveOutcomes() {
        //GIVEN

        spellCastingOutcome1 = new SpellCastingOutcome(1,"A",false,false,null);
        spellCastingOutcome2 = new SpellCastingOutcome(2,"B",true,false,null);
        spellCastingOutcome3 = new SpellCastingOutcome(3,"C",false,false,null);
        spellCastingOutcome4 = new SpellCastingOutcome(4,"D",false,false,null);
        spellCastingOutcome5 = new SpellCastingOutcome(5,"E",true,false,null);
        spellCastingOutcome6 = new SpellCastingOutcome(6,"F",false,false,null);
        spellCastingOutcome7 = new SpellCastingOutcome(7,"G",true,false,null);
        spellCastingOutcome8 = new SpellCastingOutcome(8,"H",true,false,null);

        spellCastingOutcomeRepository.save(spellCastingOutcome1);
        spellCastingOutcomeRepository.save(spellCastingOutcome2);
        spellCastingOutcomeRepository.save(spellCastingOutcome3);
        spellCastingOutcomeRepository.save(spellCastingOutcome4);
        spellCastingOutcomeRepository.save(spellCastingOutcome5);
        spellCastingOutcomeRepository.save(spellCastingOutcome6);
        spellCastingOutcomeRepository.save(spellCastingOutcome7);
        spellCastingOutcomeRepository.save(spellCastingOutcome8);

        //WHEN
        List<SpellCastingOutcome> spellCastingOutcomeList = spellCastingOutcomeRepository.findBySpellWasASuccess(true);
        //THEN
        assertThat(spellCastingOutcomeList.size()).isEqualTo(4);
        assertThat(spellCastingOutcomeList.get(0).getDescription()).isEqualTo(spellCastingOutcome2.getDescription());
        assertThat(spellCastingOutcomeList.get(1).getDescription()).isEqualTo(spellCastingOutcome5.getDescription());
        assertThat(spellCastingOutcomeList.get(2).getDescription()).isEqualTo(spellCastingOutcome7 .getDescription());
        assertThat(spellCastingOutcomeList.get(3).getDescription()).isEqualTo(spellCastingOutcome8.getDescription());
    }
}
