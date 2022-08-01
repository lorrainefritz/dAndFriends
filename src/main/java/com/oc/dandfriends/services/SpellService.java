package com.oc.dandfriends.services;

import com.oc.dandfriends.entities.Role;
import com.oc.dandfriends.entities.Spell;
import com.oc.dandfriends.entities.SpellCastingOutcome;
import com.oc.dandfriends.repositories.SpellRepository;
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
public class SpellService {
    @Autowired
    private final SpellRepository spellRepository;
    private  final SpellCastingOutcomeService spellCastingOutcomeService;

    public List<Spell> findAllSpells() {
        log.info("in SpellService in findAllSpells method");
        return spellRepository.findAll();
    }

    public List<Spell> findAllSpellsOfACustomTypeOfSpell(Integer id) throws Exception {
        log.info("in SpellService in findAllSpellsOfACustomTypeOfSpells method");
        if (id == null) {
            log.info("in SpellService in findAllSpellsOfACustomTypeOfSpell method where id is null");
            throw new Exception("Invalid id");
        }
        return spellRepository.findSpellsByCustomTypeOfSpell(id);
    }

    public Spell getASpellById(Integer id) throws Exception {
        log.info("in SpellService in getASpellById method");
        if (id == null) {
            log.info("in SpellService in getASpellById method where id is null");
            throw new Exception("Invalid id");
        }
        return spellRepository.getById(id);
    }

    public Spell saveASpell(@Valid Spell spell) throws Exception {
        log.info("in SpellService in saveASpell method");
        if (spell == null) {
            log.info("in SpellService in saveASpell method where spell is null");
            throw new Exception("Spell can't be null");
        }
        return spellRepository.save(spell);
    }

    public void deleteASpell(Integer id) throws Exception {
        log.info("in SpellService in deleteASpellById method");
        if (id == null) {
            log.info("in SpellService in deleteASpellById method where id is null");
            throw new Exception("Invalid id");
        }
        spellRepository.deleteById(id);
    }


}
