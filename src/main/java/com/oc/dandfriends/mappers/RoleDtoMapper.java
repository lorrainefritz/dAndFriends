package com.oc.dandfriends.mappers;

import com.oc.dandfriends.dtos.RoleDto;
import com.oc.dandfriends.entities.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleDtoMapper {
    @Mapping(source = "role.id",target = "id")
    @Mapping(source = "role.name",target = "name")
    RoleDto roleToRoleDto(Role role);
    List<RoleDto> roleToAllRoleDto(List<Role> roles);
    Role roleDtoToRole(RoleDto roleDto);
}
