package com.oc.dandfriends.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SpellShortDescriptionDto {

    private Integer id;
    private String title;
    private String customTypeOfSpellName;
    private  String shortDescription;
    private String icon;

}
