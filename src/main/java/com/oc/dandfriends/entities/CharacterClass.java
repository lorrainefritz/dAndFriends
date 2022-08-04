package com.oc.dandfriends.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table(name = "CHARACTERCLASS")
public class CharacterClass {
    @Id
    @Column(name="CHARACTERCLASS_ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name")
    @Size(max = 65,message = " 65 characters allowed")
    @NotBlank(message = "field can't be empty")
    private String characterClassName;

}
