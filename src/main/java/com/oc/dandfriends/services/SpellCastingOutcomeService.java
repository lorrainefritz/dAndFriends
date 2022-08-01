package com.oc.dandfriends.services;

import com.oc.dandfriends.entities.Role;
import com.oc.dandfriends.entities.SpellCastingOutcome;
import com.oc.dandfriends.repositories.SpellCastingOutcomeRepository;
import com.oc.dandfriends.util.GenerateARandomUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Random;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Log4j2
public class SpellCastingOutcomeService {
    private final SpellCastingOutcomeRepository spellCastingOutcomeRepository;
    private final GenerateARandomUtil generateARandomUtil;

    public SpellCastingOutcome getASpellCastingOutcomeById(Integer id) throws Exception {
        log.info("in SpellCastingOutcomeService in getASpellCastingOutcomeById method");
        if (id == null) {
            log.info("n SpellCastingOutcomeService in getASpellCastingOutcomeById method\" where id is null");
            throw new Exception("Invalid Id");
        }
        return spellCastingOutcomeRepository.getById(id);
    }

    public List<SpellCastingOutcome> findAllSpellCastingOutcome(){
        log.info("in SpellCastingOutcomeService in findAllSpellCastingOutcome method");
        return spellCastingOutcomeRepository.findAll();
    }


    public SpellCastingOutcome saveASpellCastingOutcome(@Valid SpellCastingOutcome spellCastingOutcome) throws Exception{
        log.info("in SpellCastingOutcomeService in saveASpellCastingOutcome method");
        if (spellCastingOutcome == null){
            log.info("in SpellCastingOutcomeService in saveASpellCastingOutcome method where spellCastingOutcome is null");
            throw new Exception("SpellCastingOutcome can't be null");
        }
        return spellCastingOutcomeRepository.save(spellCastingOutcome);
    }

    public void deleteASpellCastingOutcome(Integer id) throws Exception{
        log.info("in SpellCastingOutcomeService in deleteASpellCastingOutcomeById method");
        if (id==null){
            log.info("in SpellCastingOutcomeService in deleteASpellCastingOutcomeById method where id is null");
            throw new Exception("Invalid id");
        }
        spellCastingOutcomeRepository.deleteById(id);
    }


    public SpellCastingOutcome generateASpellOutcome(Boolean spellWasASuccess) {
        log.info("in SpellCastingOutcomeService in generateSpellOutcomeFromSpellCastingIsASuccess method where spellWasASuccess is {}",spellWasASuccess);
        List<SpellCastingOutcome> spellCastingOutcomes = spellCastingOutcomeRepository.findBySpellWasASuccess(spellWasASuccess);
        int randomlyGeneratedPositionInTheList = generateARandomUtil.generateARandom(spellCastingOutcomes.size());
        return spellCastingOutcomes.get(randomlyGeneratedPositionInTheList);
    }



}
