package com.oc.dandfriends.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table(name = "CUSTOM_TYPE_OF_SPELL")
public class CustomTypeOfSpell {
    @Id
    @Column(name="CUSTOM_TYPE_OF_SPELL_ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name="NAME")
    @Size(max = 65,message = " 65 characters allowed")
    @NotBlank(message = "field can't be empty")
    private String customTypeOfSpellName;

    @Column (name = "ICON")
    private String icon;
}
