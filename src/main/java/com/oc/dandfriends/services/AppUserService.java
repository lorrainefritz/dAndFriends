package com.oc.dandfriends.services;

import com.oc.dandfriends.entities.AppUser;
import com.oc.dandfriends.repositories.AppUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Log4j2
public class AppUserService implements UserDetailsService {
    private final AppUserRepository appUserRepository;

    public List<AppUser> findAllAppUsers(){
        log.info("in AppUserService in findAllUsers method");
        return appUserRepository.findAll();
    }

    public AppUser getAnAppUserById(Integer id) throws Exception {
        log.info("in AppUserService in getAAppUserById method");
        if (id==null){
            log.info("in AppUserService in getAAppUserById method where id is null");
            throw new Exception("Invalid id");
        }
        return appUserRepository.getById(id);
    }

    public AppUser saveAnAppUser(@Valid AppUser appUser) throws Exception{
        log.info("in AppUserService in saveAAppUser method");
        if (appUser == null){
            log.info("in AppUserService in saveAAppUser method where appUser is null");
            throw new Exception("AppUser can't be null");
        }
        return appUserRepository.save(appUser);
    }

    public AppUser findAnAppUserByUsername(String username) throws UsernameNotFoundException {
        log.info("in AppUserService in findAUserByUsername method ");
        AppUser user = appUserRepository.findByUsername(username);
        if (user == null) {
            log.info("in AppUserService in finAUserByUsername method where username is null");
            throw new UsernameNotFoundException("Invalid email or Password");
        }
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("in AppUserService in loadUserByUsername method ");
        AppUser appUser = appUserRepository.findByUsername(username);
        if (appUser == null) {
            log.error("in AppUserService in loadUserByUsername method User not found in DB ");
            throw new UsernameNotFoundException("Invalid email or Password");
        }else{
            log.info("in UserService in loadUserByUsername method, appUser {} found ",username);
        }
        Collection<SimpleGrantedAuthority> authority = new ArrayList<>();
        authority.add(new SimpleGrantedAuthority(appUser.getRole().getName()));
        return new org.springframework.security.core.userdetails.User(appUser.getUsername(), appUser.getPassword(), authority);

    }
}
