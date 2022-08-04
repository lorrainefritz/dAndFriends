package com.oc.dandfriends.mappers;

import com.oc.dandfriends.dtos.AppUserDto;
import com.oc.dandfriends.dtos.SpellCastingOutcomeDto;
import com.oc.dandfriends.entities.AppUser;
import com.oc.dandfriends.entities.SpellCastingOutcome;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SpellCastingOutcomeDtoMapper {

    @Mapping(source = "spellCastingOutcome.id",target = "id")
    @Mapping(source = "spellCastingOutcome.description",target = "description")
    @Mapping(source = "spellCastingOutcome.spellWasASuccess",target = "spellWasASuccess")
    @Mapping(source = "spellCastingOutcome.hasARandomAdditionalEffect",target = "hasARandomAdditionalEffect")
    @Mapping(source = "spellCastingOutcome.icon",target = "icon")
    SpellCastingOutcomeDto spellCastingOutcomeToSpellCastingOutcomeDto(SpellCastingOutcome spellCastingOutcome);
    List<SpellCastingOutcome> spellCastingOutcomesDtoToAllSpellCastingOutcomes(List<SpellCastingOutcomeDto> spellCastingOutcomesDto);
    List<SpellCastingOutcomeDto> spellCastingOutcomesToAllSpellCastingOutcomesDto(List<SpellCastingOutcome> spellCastingOutcomes);
    SpellCastingOutcome spellCastingOutcomeDtoToSpellCastingOutcome(SpellCastingOutcomeDto spellCastingOutcomeDto);
    

}
