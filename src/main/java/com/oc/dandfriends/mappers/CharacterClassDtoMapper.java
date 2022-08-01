package com.oc.dandfriends.mappers;

import com.oc.dandfriends.dtos.AppUserDto;
import com.oc.dandfriends.dtos.CharacterClassDto;
import com.oc.dandfriends.entities.AppUser;
import com.oc.dandfriends.entities.CharacterClass;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CharacterClassDtoMapper {
    @Mapping(source = "characterClass.id",target = "id")
    @Mapping(source = "characterClass.username",target = "name")

    CharacterClassDto characterClassToCharacterClassDto(CharacterClass characterClass);
    CharacterClass characterClassDtoToCharacterClass(CharacterClassDto characterClassDto);
    List<CharacterClass> characterClassesDtoToAllCharacterClasses(List<CharacterClassDto> characterClassesDto);
    List<CharacterClassDto> characterClassesToAllCharacterClassesDto(List<CharacterClass> characterClasses);
}
