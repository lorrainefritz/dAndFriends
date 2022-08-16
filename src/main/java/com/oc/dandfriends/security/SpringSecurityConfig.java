package com.oc.dandfriends.security;

import com.oc.dandfriends.services.AppUserService;
import com.oc.dandfriends.token.TokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Log4j2
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private final UserDetailsService userDetailsService;
    private final AppUserService appUserService;
    private final TokenUtil tokenUtil;


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        log.info("in SpringSecurityConfig in configure (AuthenticationManagerBuilder)");
        BCryptPasswordEncoder bCryptPasswordEncoder = bCryptPasswordEncoder();
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        log.info("in SpringSecurityConfig in AuthenticationManagerBean");
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        log.info("in SpringSecurityConfig in configure (HttpSecurity)");
        httpSecurity.csrf().disable();
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.authorizeRequests().antMatchers("/resources/**", "/static/**", "/css/**", "/images/**", "/logos/**").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/home","/login").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/additionalRandomOutcomes","/additionalRandomOutcomes/**",
                "/appUsers","/appUsers/**","/characterClasses","/characterClasses/**","/components","/components/**",
                "/customTypeOfSpells","/customTypeOfSpells/**","/roles","/roles/**","/spellCastingOutcomes", "/spells").authenticated();
        httpSecurity.addFilterBefore(new CustomAuthenticationFilter(appUserService, tokenUtil), UsernamePasswordAuthenticationFilter.class);
    }
}
