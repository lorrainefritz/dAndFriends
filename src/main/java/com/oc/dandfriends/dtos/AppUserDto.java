package com.oc.dandfriends.dtos;

import com.oc.dandfriends.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppUserDto {
    private Integer id;
    private String username;
    private String pseudo;
    private Role role;
}
