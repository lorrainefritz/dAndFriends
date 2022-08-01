package com.oc.dandfriends.repositories;

import com.oc.dandfriends.entities.SpellCastingOutcome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface SpellCastingOutcomeRepository extends JpaRepository<SpellCastingOutcome,Integer> {
    List<SpellCastingOutcome> findBySpellWasASuccess(Boolean spellSucceed);
}
