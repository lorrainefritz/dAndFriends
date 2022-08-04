package com.oc.dandfriends.services;

import com.oc.dandfriends.entities.CharacterClass;
import com.oc.dandfriends.exceptions.EntityNotFoundException;
import com.oc.dandfriends.repositories.CharacterClassRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Log4j2
public class CharacterClassService {
    private final CharacterClassRepository characterClassRepository;

    public List<CharacterClass> findAllCharacterClasses(){
        log.info("in CharacterClassService in findAllCharacterClass method");
        return characterClassRepository.findAll();
    }

    public CharacterClass findACharacterClassById(Integer id) throws Exception {
        log.info("in CharacterClassService in getACharacterClassById method");
        if (id==null){
            log.info("in CharacterClassService in getACharacterClassById method where id is null");
            throw new Exception("Invalid id");
        }
        return characterClassRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("CharacterClass by id " +id+" was not found"));
    }

    public CharacterClass saveACharacterClass(@Valid CharacterClass characterClass ) throws Exception{
        log.info("in CharacterClassService in saveACharacterClass method");
        if (characterClass == null){
            log.info("in CharacterClassService in saveACharacterClass method where characterClass is null");
            throw new Exception("characterClass can't be null");
        }
        return characterClassRepository.save(characterClass);
    }

    public void deleteACharacterClassById(Integer id) throws Exception{
        log.info("in CharacterClassService in deleteACharacterClassById method");
        if (id==null){
            log.info("in CharacterClassService in deleteACharacterClassById method where id is null");
            throw new Exception("Invalid id");
        }
        characterClassRepository.deleteById(id);
    }


    public CharacterClass findACharacterClassByName(String name) throws Exception {
        log.info("in CharacterClassService in findACharacterClassByName method");
        if (name == null){
            log.info("in CharacterClassService in findACharacterClassByName method where name is null");
            throw new Exception("name can't be null");
        }
          CharacterClass characterClass = characterClassRepository.findByCharacterClassName(name);
        if (characterClass == null){
            log.info("in CharacterClassService in findACharacterClassByName method where characterClass is null/notFound");
            throw new Exception("characterClassNotFound !");
        }
        return characterClass;
    }
}
