package com.oc.dandfriends.services;

import com.oc.dandfriends.entities.ComponentOfSpell;
import com.oc.dandfriends.exceptions.EntityNotFoundException;
import com.oc.dandfriends.repositories.ComponentOfSpellRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Log4j2
public class ComponentService {
    private final ComponentOfSpellRepository componentOfSpellRepository;


    public List<ComponentOfSpell> findAllComponents(){
        log.info("in ComponentService in findAllComponents method");
        return componentOfSpellRepository.findAll();
    }

    public ComponentOfSpell findAComponentById(Integer id) throws Exception {
        log.info("in ComponentService in getAComponentById method");
        if (id==null){
            log.info("in ComponentService in getAComponentById method where id is null");
            throw new Exception("Invalid id");
        }
        return componentOfSpellRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Component by id " +id+" was not found"));
    }

    public ComponentOfSpell saveAComponent(@Valid ComponentOfSpell componentOfSpell) throws Exception{
        log.info("in ComponentService in saveAComponent method");
        if (componentOfSpell == null){
            log.info("in ComponentService in saveAComponent method where component is null");
            throw new Exception("component can't be null");
        }
        return componentOfSpellRepository.save(componentOfSpell);
    }

    public void deleteAComponentById(Integer id) throws Exception{
        log.info("in ComponentService in deleteAComponentById method");
        if (id==null){
            log.info("in ComponentService in deleteAComponentById method where id is null");
            throw new Exception("Invalid id");
        }
        componentOfSpellRepository.deleteById(id);
    }

    public ComponentOfSpell findAComponentByName(String name) throws Exception {
        log.info("in ComponentService in findAComponentByName method");
        if (name == null){
            log.info("in ComponentService in findAComponentByName method where name is null");
            throw new Exception("name can't be null");
        }
        ComponentOfSpell componentOfSpell = componentOfSpellRepository.findByComponentName(name);
        if (componentOfSpell == null){
            log.info("in ComponentService in findAComponentByName method where componentOfSpell is null/notFound");
            throw new Exception("componentOfSpellNotFound !");
        }
        return componentOfSpell;
    }
}
