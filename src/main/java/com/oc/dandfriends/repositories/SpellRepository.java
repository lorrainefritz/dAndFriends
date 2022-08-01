package com.oc.dandfriends.repositories;

import com.oc.dandfriends.entities.Spell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface SpellRepository extends JpaRepository<Spell, Integer> {
    @Query(value = "SELECT * FROM spell spell WHERE CONCAT (spell.title, spell.short_description, spell.full_description) LIKE lower(concat('%', ?1,'%'))", nativeQuery = true)
    List<Spell> findSpellsByKeywordIgnoreCase(@Param("keyword") String keyword);

    @Query("select spell from Spell spell where spell.customTypeOfSpell.id = ?1")
    List<Spell> findSpellsByCustomTypeOfSpell(Integer id);
}
