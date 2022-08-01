package com.oc.dandfriends.services;

import com.oc.dandfriends.entities.AppUser;
import com.oc.dandfriends.entities.Component;
import com.oc.dandfriends.repositories.ComponentRepository;
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
    private final ComponentRepository componentRepository;


    public List<Component> findAllComponents(){
        log.info("in ComponentService in findAllComponents method");
        return componentRepository.findAll();
    }

    public Component getAComponentById(Integer id) throws Exception {
        log.info("in ComponentService in getAComponentById method");
        if (id==null){
            log.info("in ComponentService in getAComponentById method where id is null");
            throw new Exception("Invalid id");
        }
        return componentRepository.getById(id);
    }

    public Component saveAComponent(@Valid Component component) throws Exception{
        log.info("in ComponentService in saveAComponent method");
        if (component == null){
            log.info("in ComponentService in saveAComponent method where component is null");
            throw new Exception("component can't be null");
        }
        return componentRepository.save(component);
    }

    public void deleteAComponentById(Integer id) throws Exception{
        log.info("in ComponentService in deleteAComponentById method");
        if (id==null){
            log.info("in ComponentService in deleteAComponentById method where id is null");
            throw new Exception("Invalid id");
        }
        componentRepository.deleteById(id);
    }
}
