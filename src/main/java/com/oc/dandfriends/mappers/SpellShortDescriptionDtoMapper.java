package com.oc.dandfriends.mappers;

import com.oc.dandfriends.dtos.SpellFullDescriptionDto;
import com.oc.dandfriends.dtos.SpellShortDescriptionDto;
import com.oc.dandfriends.entities.CharacterClass;
import com.oc.dandfriends.entities.ComponentOfSpell;
import com.oc.dandfriends.entities.CustomTypeOfSpell;
import com.oc.dandfriends.entities.Spell;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
@Mapper(componentModel = "spring")
public interface SpellShortDescriptionDtoMapper {
        @Mappings({
                @Mapping(source = "spell.id", target = "id"),
                @Mapping(source = "spell.title", target = "title"),
                @Mapping(source = "spell.shortDescription", target = "shortDescription"),
                @Mapping(source = "spell.icon", target = "icon")
        })
        SpellShortDescriptionDto spellToSpellShortDescriptionDto(Spell spell);
        Spell spellShortDescriptionDtoToSpell(SpellShortDescriptionDto spellShortDescriptionDto);
        List<Spell> spellsShortDescriptionDtoToSpells(List<SpellShortDescriptionDto> spellsShortDescriptionDto);
        List<SpellShortDescriptionDto> spellsToSpellsShortDescriptionDto(List<Spell> spells);

    }
