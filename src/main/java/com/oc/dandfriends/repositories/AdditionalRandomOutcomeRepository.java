package com.oc.dandfriends.repositories;

import com.oc.dandfriends.entities.AdditionalRandomOutcome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AdditionalRandomOutcomeRepository extends JpaRepository<AdditionalRandomOutcome,Integer> {
}
