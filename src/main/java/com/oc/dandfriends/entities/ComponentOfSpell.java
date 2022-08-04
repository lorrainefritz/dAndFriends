package com.oc.dandfriends.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Table(name = "COMPONENT")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
public class ComponentOfSpell {
    @Id
    @Column(name="COMPONENT_ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="NAME")
    @NotBlank(message = "field can't be empty")
    private String componentName;

    @Column (name = "ICON")
    private String icon;


}
