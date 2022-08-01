package com.oc.dandfriends.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table(name = "SPELL_CASTING_OUTCOME")
public class SpellCastingOutcome {
    @Id
    @Column(name="SPELL_CASTING_OUTCOME_ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name="DESCRIPTION")
    @Size(max = 250,message = " 250 characters allowed")
    @NotBlank(message = "field can't be empty")
    private String description;

    @Column(name="SPELL_WAS_A_SUCCESS")
    @NotNull
    private Boolean spellWasASuccess;

    @Column(name="HAS_A_RANDOM_ADDITIONAL_EFFECT")
    @NotNull
    private Boolean hasARandomAdditionalEffect;

    @Lob
    @Column (name = "ICON", length= Integer.MAX_VALUE, nullable= true)
    private String icon;



}
