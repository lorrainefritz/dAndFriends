package com.oc.dandfriends.repositories;

import com.oc.dandfriends.entities.CharacterClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CharacterClassRepository extends JpaRepository<CharacterClass,Integer> {
    CharacterClass findByCharacterClassName(String name);
}
