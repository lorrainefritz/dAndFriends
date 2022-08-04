package com.oc.dandfriends.repositories;

import com.oc.dandfriends.entities.ComponentOfSpell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ComponentOfSpellRepository extends JpaRepository <ComponentOfSpell,Integer> {
    ComponentOfSpell findByComponentName(String name);
}
