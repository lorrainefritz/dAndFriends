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
public class SpellFullDescriptionDto {

    private Integer id;
    private String title;
    private String customTypeOfSpellName;
    private String school;
    private int level;
    private List<String> componentsNames;
    private List<String> characterClassesNames;
    private String castingTime;
    private String range;
    private String target;
    private String duration;
    private String savingThrow;
    private boolean spellResistance;
    private String shortDescription;
    private String fullDescription;
    private String icon;

}
