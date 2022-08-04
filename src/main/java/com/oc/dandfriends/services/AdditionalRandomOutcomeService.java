package com.oc.dandfriends.services;

import com.oc.dandfriends.entities.AdditionalRandomOutcome;
import com.oc.dandfriends.exceptions.EntityNotFoundException;
import com.oc.dandfriends.repositories.AdditionalRandomOutcomeRepository;
import com.oc.dandfriends.util.GenerateARandomUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Log4j2
public class AdditionalRandomOutcomeService {
    private final AdditionalRandomOutcomeRepository additionalRandomOutcomeRepository;
    private final GenerateARandomUtil generateARandomUtil;

    public AdditionalRandomOutcome generateAnAdditionalRandomOutcome() {
        log.info("in AdditionalRandomOutcomeService in generateAdditionalRandomOutcome method ");
        List<AdditionalRandomOutcome> additionalRandomOutcomes = additionalRandomOutcomeRepository.findAll();
        int randomlyGeneratedPositionInTheList = generateARandomUtil.generateARandom(additionalRandomOutcomes.size());
        return additionalRandomOutcomes.get(randomlyGeneratedPositionInTheList);
    }

    public List<AdditionalRandomOutcome> findAllAdditionalRandomOutcome(){
        log.info("in AdditionalRandomOutcomeService in findAllAdditionalRandomOutcome method");
        return additionalRandomOutcomeRepository.findAll();
    }

    public AdditionalRandomOutcome findAnAdditionalRandomOutcomeById(Integer id) throws Exception {
        log.info("in AdditionalRandomOutcomeService in getAnAdditionalRandomOutcomeById method");
        if (id==null){
            log.info("in AdditionalRandomOutcomeService in getAnAdditionalRandomOutcomeById method where id is null");
            throw new Exception("Invalid id");
        }
        return additionalRandomOutcomeRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("AdditionalRandomOutcome by id " +id+" was not found"));
    }

    public AdditionalRandomOutcome saveAnAdditionalRandomOutcome(@Valid AdditionalRandomOutcome additionalRandomOutcome) throws Exception{
        log.info("in AdditionalRandomOutcomeService in saveAnAdditionalRandomOutcome method");
        if (additionalRandomOutcome == null){
            log.info("in AdditionalRandomOutcomeService in saveAnAdditionalRandomOutcome method where role is null");
            throw new Exception("AdditionalRandomOutcome can't be null");
        }
        return additionalRandomOutcomeRepository.save(additionalRandomOutcome);
    }

    public void deleteAnAdditionalRandomOutcome(Integer id) throws Exception{
        log.info("in AdditionalRandomOutcomeService in deleteAnAdditionalRandomOutcomeById method");
        if (id==null){
            log.info("in AdditionalRandomOutcomeService in deleteAnAdditionalRandomOutcomeById method where id is null");
            throw new Exception("Invalid id");
        }
        additionalRandomOutcomeRepository.deleteById(id);
    }

}
