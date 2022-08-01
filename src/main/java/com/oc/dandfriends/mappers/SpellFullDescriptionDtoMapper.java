package com.oc.dandfriends.mappers;

import com.oc.dandfriends.dtos.ComponentDto;
import com.oc.dandfriends.dtos.SpellFullDescriptionDto;
import com.oc.dandfriends.entities.CharacterClass;
import com.oc.dandfriends.entities.Component;
import com.oc.dandfriends.entities.CustomTypeOfSpell;
import com.oc.dandfriends.entities.Spell;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SpellFullDescriptionDtoMapper {


    @Mapping(source = "spell.id",target = "id")
    @Mapping(source = "spell.title",target = "title")
    @Mapping(source = "customTypeOfSpell.description",target = "customTypeOfSpellDescription")
    @Mapping(source = "school",target = "school")
    @Mapping(source = "spell.level",target = "level")
    @Mapping(source = "component.name",target = "componentsName")
    @Mapping(source = "characterClass.name",target = "characterClassesName")
    @Mapping(source = "spell.castingTime",target = "castingTime")
    @Mapping(source = "spell.range",target = "range")
    @Mapping(source = "spell.target",target = "target")
    @Mapping(source = "spell.duration",target = "duration")
    @Mapping(source = "spell.savingThrow",target = "savingThrow")
    @Mapping(source = "spell.spellResistance",target = "spellResistance")
    @Mapping(source = "spell.shortDescription",target = "shortDescription")
    @Mapping(source = "spell.fullDescription",target = "fullDescription")
    @Mapping(source = "spell.icon",target = "icon")

    SpellFullDescriptionDto spellToSpellFullDescriptionDto(Spell spell);
    Spell spellFullDescriptionDtoToSpell(SpellFullDescriptionDto spellFullDescriptionDto);
    List<Spell> spellsFullDescriptionDtoToSpells(List<SpellFullDescriptionDto> spellsFullDescriptionDto);
    List<SpellFullDescriptionDto> spellsToSpellsFullDescriptionDto(List<Spell> spells);
    CustomTypeOfSpell spellFullDescriptionDtoToCustomTypeOfSpell(SpellFullDescriptionDto spellFullDescriptionDto);
    List<Component> spellFullDescriptionDtoToComponents(SpellFullDescriptionDto spellFullDescriptionDto);
    List<CharacterClass> spellFullDescriptionDtoToCharacterClasses(SpellFullDescriptionDto spellFullDescriptionDto);
}
