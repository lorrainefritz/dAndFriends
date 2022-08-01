package com.oc.dandfriends.repositories;

import com.oc.dandfriends.entities.Component;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ComponentRepository extends JpaRepository <Component,Integer> {
}
