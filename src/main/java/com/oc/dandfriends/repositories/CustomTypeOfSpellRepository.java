package com.oc.dandfriends.repositories;

import com.oc.dandfriends.entities.CustomTypeOfSpell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface CustomTypeOfSpellRepository extends JpaRepository<CustomTypeOfSpell,Integer> {
    CustomTypeOfSpell findByCustomTypeOfSpellName(String name);
}
