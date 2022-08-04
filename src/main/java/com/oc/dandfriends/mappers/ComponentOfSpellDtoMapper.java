package com.oc.dandfriends.mappers;

import com.oc.dandfriends.dtos.ComponentOfSpellDto;
import com.oc.dandfriends.entities.ComponentOfSpell;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ComponentOfSpellDtoMapper {
    @Mapping(source = "componentOfSpell.id",target = "id")
    @Mapping(source = "componentOfSpell.componentName",target = "componentName")
    @Mapping(source = "componentOfSpell.icon",target = "icon")
    ComponentOfSpellDto componentOfSpellToComponentOfSpellDto(ComponentOfSpell componentOfSpell);
    ComponentOfSpell componentOfSpellDtoToComponentOfSpell(ComponentOfSpellDto componentOfSpellDto);
    List<ComponentOfSpellDto> componentsOfSpellToComponentsOfSpellDto(List<ComponentOfSpell> componentOfSpells);
    List<ComponentOfSpell> componentsOfSpellDtoToComponentsOfSpell(List<ComponentOfSpellDto> componentsDto);
}
