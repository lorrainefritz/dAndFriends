package com.oc.dandfriends.entities;

import com.oc.dandfriends.enums.School;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table(name = "SPELL")
public class Spell {
    @Id
    @Column(name="SPELL_ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name="TITLE")
    @Size(max = 65,message = " 65 characters allowed")
    @NotBlank(message = "field can't be empty")
    private String title;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(columnDefinition = "ID", nullable = false)
    private CustomTypeOfSpell customTypeOfSpell;

    @Column(name="SCHOOL")
    @Enumerated(EnumType.STRING)
    private School school;

    @Column(name="LEVEL")
    @Min(value=0, message="must be between 0 and 100")
    @Max(value=100, message="must be between 0 and 100")
    @NotNull(message="Ce champ ne doit pas être vide")
    private int level;

    @OneToMany
    private List<Component> components;

    @OneToMany
    private List<CharacterClass> characterClasses;

    @Column(name="CASTING_TIME")
    @Size(max = 65,message = " 65 characters allowed")
    @NotBlank(message = "field can't be empty")
    private String castingTime;

    @Column(name="RANGE")
    @Size(max = 65,message = " 65 characters allowed")
    @NotBlank(message = "field can't be empty")
    private String range;

    @Column(name="TARGET")
    @Size(max = 65,message = " 65 characters allowed")
    @NotBlank(message = "field can't be empty")
    private String target;

    @Column(name="DURATION")
    @Size(max = 65,message = " 65 characters allowed")
    @NotBlank(message = "field can't be empty")
    private String duration;

    @Column(name="SAVING_THROW")
    @Size(max = 65,message = " 65 characters allowed")
    @NotBlank(message = "field can't be empty")
    private String savingThrow;

    @Column(name="SPELL_RESISTANCE")
    private boolean spellResistance;

    @Column(name="SHORT_DESCRIPTION")
    @Size(max = 250,message = " 250 characters allowed")
    @NotBlank(message = "field can't be empty")
    private String shortDescription;

    @Column(name="FULL_DESCRIPTION")
    @NotBlank(message = "field can't be empty")
    private String fullDescription;

    @Lob
    @Column (name = "ICON", length= Integer.MAX_VALUE, nullable= true)
    private String icon;
}
