package com.oc.dandfriends.mappers;

import com.oc.dandfriends.dtos.AdditionalRandomOutcomeDto;
import com.oc.dandfriends.entities.AdditionalRandomOutcome;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdditionalRandomOutcomeDtoMapper {

    @Mapping(source = "additionalRandomOutcome.id",target = "id")
    @Mapping(source = "additionalRandomOutcome.description",target = "description")
    @Mapping(source = "additionalRandomOutcome.icon",target = "icon")

    AdditionalRandomOutcomeDto additionalRandomOutcomeToAdditionalRandomOutcomeDto(AdditionalRandomOutcome additionalRandomOutcome);
    List<AdditionalRandomOutcomeDto> additionalRandomOutcomesToAllAdditionalRandomOutcomesDto(List<AdditionalRandomOutcome>additionalRandomOutcomes);
    List<AdditionalRandomOutcome> additionalRandomOutcomesDtoToAllAdditionalRandomOutcomes(List<AdditionalRandomOutcomeDto>additionalRandomOutcomesDto);
    AdditionalRandomOutcome additionalRandomOutcomeDtoToAdditionalRandomOutcome(AdditionalRandomOutcomeDto additionalRandomOutcomeDto);


}
