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
@Table(name = "ADDITIONAL_RANDOM_OUTCOME")
public class AdditionalRandomOutcome {
    @Id
    @Column(name="ADDITIONAL_RANDOM_OUTCOME_ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name="DESCRIPTION")
    @Size(max = 250,message = " 250 characters allowed")
    @NotBlank(message = "field can't be empty")
    private String description;

    @Column (name = "ICON")
    private String icon;

}
