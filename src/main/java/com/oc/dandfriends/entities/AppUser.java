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
@Table(name = "APP_USER")
public class AppUser {
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "USERNAME")
    @Size(max = 65, message = "65 charactères maximum")
    @NotBlank(message = "Ce champ ne doit pas être vide")
    private String username;

    @Column(name = "PSEUDO")
    @Size(max = 65, message = "65 charactères maximum")
    @NotBlank(message = "Ce champ ne doit pas être vide")
    private String pseudo;

    @Column(name = "PASSWORD")
    @Size(max = 65, message = "65 charactères maximum")
    @NotBlank(message = "Ce champ ne doit pas être vide")
    private String password;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(columnDefinition = "ID", nullable = false)
    private Role role;
}
