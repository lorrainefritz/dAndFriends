package com.oc.dandfriends.mappers;

import com.oc.dandfriends.dtos.ComponentDto;
import com.oc.dandfriends.entities.Component;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ComponentDtoMapper {
    @Mapping(source = "component.id",target = "id")
    @Mapping(source = "component.name",target = "name")
    @Mapping(source = "component.icon",target = "icon")
    ComponentDto componentToComponentDto(Component component);
    Component componentDtoToComponent(ComponentDto componentDto);
    List<ComponentDto> componentsToComponentsDto(List<Component> components);
    List<Component> componentsDtoToComponents(List<ComponentDto> componentsDto);
}
