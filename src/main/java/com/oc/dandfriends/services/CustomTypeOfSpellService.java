package com.oc.dandfriends.services;

import com.oc.dandfriends.entities.Component;
import com.oc.dandfriends.entities.CustomTypeOfSpell;
import com.oc.dandfriends.repositories.CustomTypeOfSpellRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Log4j2
public class CustomTypeOfSpellService {
    @Autowired
    private final CustomTypeOfSpellRepository customTypeOfSpellRepository;

    public List<CustomTypeOfSpell> findAllCustomTypeOfSpell(){
        log.info("in CustomTypeOfSpellService in findAllCustomTypeOfSpell method");
        return customTypeOfSpellRepository.findAll();
    }

    public CustomTypeOfSpell getACustomTypeOfSpellById(Integer id) throws Exception {
        log.info("in CustomTypeOfSpellService in getACustomTypeOfSpellById method");
        if (id==null){
            log.info("in CustomTypeOfSpellService in getACustomTypeOfSpellById method where id is null");
            throw new Exception("Invalid id");
        }
        return customTypeOfSpellRepository.getById(id);
    }

    public CustomTypeOfSpell saveACustomTypeOfSpell(@Valid CustomTypeOfSpell customTypeOfSpell) throws Exception{
        log.info("in CustomTypeOfSpellService in saveACCustomTypeOfSpell method");
        if (customTypeOfSpell == null){
            log.info("in CustomTypeOfSpellService in saveACustomTypeOfSpell method where CustomTypeOfSpell is null");
            throw new Exception("CustomTypeOfSpell can't be null");
        }
        return customTypeOfSpellRepository.save(customTypeOfSpell);
    }

    public void deleteACustomTypeOfSpellById(Integer id) throws Exception{
        log.info("in CustomTypeOfSpellService in deleteACustomTypeOfSpellById method");
        if (id==null){
            log.info("in CustomTypeOfSpellService in deleteACustomTypeOfSpellById method where id is null");
            throw new Exception("Invalid id");
        }
        customTypeOfSpellRepository.deleteById(id);
    }
}
