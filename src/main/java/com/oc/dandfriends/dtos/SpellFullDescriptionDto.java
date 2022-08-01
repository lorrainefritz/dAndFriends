package com.oc.dandfriends.dtos;

import com.oc.dandfriends.entities.CharacterClass;
import com.oc.dandfriends.entities.Component;
import com.oc.dandfriends.entities.CustomTypeOfSpell;
import com.oc.dandfriends.enums.School;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SpellFullDescriptionDto {

    private Integer id;
    private String title;
    private String customTypeOfSpellDescription;
    private String school;
    private int level;
    private List<String> componentsName;
    private List<String> characterClassesName;
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
