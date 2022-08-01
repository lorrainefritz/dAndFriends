package com.oc.dandfriends.mappers;

import com.oc.dandfriends.dtos.AppUserDto;
import com.oc.dandfriends.entities.AppUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AppUserDtoMapper {


    @Mapping(source = "appUser.id",target = "id")
    @Mapping(source = "appUser.username",target = "username")
    @Mapping(source = "appUser.pseudo",target = "pseudo")
    @Mapping(source = "appUser.role",target = "role")

    AppUserDto appUserToAppUserDto(AppUser appUser);
    List<AppUser> appUsersDtoToAllAppUsers(List<AppUserDto> appUsersDto);
    List<AppUserDto> appUsersToAllAppUsersDto(List<AppUser> appUsers);
    AppUser appUserDtoToAppUser(AppUserDto appUserDto);

}
