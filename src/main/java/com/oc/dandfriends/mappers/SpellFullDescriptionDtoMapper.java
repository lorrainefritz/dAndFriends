package com.oc.dandfriends.mappers;

import com.oc.dandfriends.dtos.SpellFullDescriptionDto;
import com.oc.dandfriends.entities.CharacterClass;
import com.oc.dandfriends.entities.ComponentOfSpell;
import com.oc.dandfriends.entities.CustomTypeOfSpell;
import com.oc.dandfriends.entities.Spell;
import com.oc.dandfriends.services.CustomTypeOfSpellService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SpellFullDescriptionDtoMapper {
    @Mappings({
            @Mapping(source = "spell.id", target = "id"),
            @Mapping(source = "spell.title", target = "title"),
            @Mapping(source = "customTypeOfSpell", target = "customTypeOfSpellName"),
            @Mapping(source = "school", target = "school"),
            @Mapping(source = "spell.level", target = "level"),
            @Mapping(source = "componentOfSpells", target = "componentsNames"),
            @Mapping(source = "characterClasses", target = "characterClassesNames"),
            @Mapping(source = "spell.castingTime", target = "castingTime"),
            @Mapping(source = "spell.range", target = "range"),
            @Mapping(source = "spell.target", target = "target"),
            @Mapping(source = "spell.duration", target = "duration"),
            @Mapping(source = "spell.savingThrow", target = "savingThrow"),
            @Mapping(source = "spell.spellResistance", target = "spellResistance"),
            @Mapping(source = "spell.shortDescription", target = "shortDescription"),
            @Mapping(source = "spell.fullDescription", target = "fullDescription"),
            @Mapping(source = "spell.icon", target = "icon")
    })
    SpellFullDescriptionDto spellToSpellFullDescriptionDto(Spell spell);
    Spell spellFullDescriptionDtoToSpell(SpellFullDescriptionDto spellFullDescriptionDto);
    List<Spell> spellsFullDescriptionDtoToSpells(List<SpellFullDescriptionDto> spellsFullDescriptionDto);
    List<SpellFullDescriptionDto> spellsToSpellsFullDescriptionDto(List<Spell> spells);
    CustomTypeOfSpell spellFullDescriptionDtoToCustomTypeOfSpell(SpellFullDescriptionDto spellFullDescriptionDto);

    default String mapComponentOfSpellToString(ComponentOfSpell componentOfSpell) {
        return componentOfSpell.getComponentName();
    }
    ComponentOfSpell ComponentOfSpellFromString(String componentOfSpell);

    default String mapCharacterToString(CharacterClass characterClass) {
        return characterClass.getCharacterClassName();
    }
   CharacterClass characterClassFromString(String characterClass);

    default String mapCustomTypeOfSpellToString(CustomTypeOfSpell customTypeOfSpell) {
        return customTypeOfSpell.getCustomTypeOfSpellName();
    }
   CustomTypeOfSpell customTypeOfSpelFromString(String customTypeOfSpell);



}
