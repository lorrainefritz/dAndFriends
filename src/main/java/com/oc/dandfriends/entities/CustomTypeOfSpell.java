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
@Table(name = "CUSTOM_TYPE")
public class CustomTypeOfSpell {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name="NAME")
    @Size(max = 65,message = " 65 characters allowed")
    @NotBlank(message = "field can't be empty")
    private String name;

    @Lob
    @Column (name = "ICON", length= Integer.MAX_VALUE, nullable= true)
    private String icon;
}
