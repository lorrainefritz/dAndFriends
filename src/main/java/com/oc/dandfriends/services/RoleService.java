package com.oc.dandfriends.services;

import com.oc.dandfriends.entities.CustomTypeOfSpell;
import com.oc.dandfriends.entities.Role;
import com.oc.dandfriends.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Log4j2
public class RoleService {
    private final RoleRepository roleRepository;

    public List<Role> findAllRole(){
        log.info("in RoleService in findAllRole method");
        return roleRepository.findAll();
    }

    public Role getARoleById(Integer id) throws Exception {
        log.info("in RoleService in getARoleById method");
        if (id==null){
            log.info("in RoleService in getARoleById method where id is null");
            throw new Exception("Invalid id");
        }
        return roleRepository.getById(id);
    }

    public Role saveARole(@Valid Role role) throws Exception{
        log.info("in RoleService in saveARole method");
        if (role == null){
            log.info("in RoleService in saveARole method where role is null");
            throw new Exception("Role can't be null");
        }
        return roleRepository.save(role);
    }

    public void deleteARole(Integer id) throws Exception{
        log.info("in RoleService in deleteARoleById method");
        if (id==null){
            log.info("in RoleService in deleteARoleById method where id is null");
            throw new Exception("Invalid id");
        }
        roleRepository.deleteById(id);
    }
}
