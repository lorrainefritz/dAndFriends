package com.oc.dandfriends.mappers;

import com.oc.dandfriends.dtos.CustomTypeOfSpellDto;
import com.oc.dandfriends.entities.CustomTypeOfSpell;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomTypeOfSpellDtoMapper {
    @Mapping(source = "customTypeOfSpell.id",target = "id")
    @Mapping(source = "customTypeOfSpell.name",target = "name")
    @Mapping(source = "customTypeOfSpell.icon",target = "icon")
    CustomTypeOfSpellDto customTypeOfSpellToCustomTypeOfSpellDto(CustomTypeOfSpellDto customTypeOfSpellDto);
    CustomTypeOfSpell customTypeOfSpellDtoToCustomTypeOfSpell(CustomTypeOfSpell customTypeOfSpell);
    List<CustomTypeOfSpellDto> customTypesOfSpellToCustomTypesOfSpellDto(List<CustomTypeOfSpell> customTypeOfSpellList);
    List<CustomTypeOfSpell> customTypesOfSpellDtoToCustomTypesOfSpell(List<CustomTypeOfSpellDto> customTypeOfSpellDtoList);
}
