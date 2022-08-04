package com.oc.dandfriends.mappers;

import com.oc.dandfriends.dtos.RoleDto;
import com.oc.dandfriends.entities.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleDtoMapper {
    @Mapping(source = "role.id",target = "id")
    @Mapping(source = "role.roleName",target = "roleName")
    RoleDto roleToRoleDto(Role role);
    List<RoleDto> rolesToAllRolesDto(List<Role> roles);
    List<Role> rolesDtoToAllRoles(List<RoleDto> rolesDto);
    Role roleDtoToRole(RoleDto roleDto);
}
