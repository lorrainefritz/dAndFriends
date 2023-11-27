package com.oc.dandfriends.security;

import com.oc.dandfriends.services.AppUserService;
import com.oc.dandfriends.token.TokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Log4j2
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${cors.allowed.origin}")
    private String allowedOrigin;


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

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Arrays.asList(allowedOrigin));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));

        // setAllowCredentials(true) is important, otherwise:
        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
        configuration.setAllowCredentials(true);

        // setAllowedHeaders is important! Without it, OPTIONS pre-flight request will fail with 403 Invalid CORS request
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        log.info("in SpringSecurityConfig in configure (HttpSecurity)");
      httpSecurity.cors().configurationSource(corsConfigurationSource()).and()   // <-- Enable CORS support here*/
                .csrf().disable();
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.authorizeRequests().antMatchers("/resources/**", "/static/**", "/css/**", "/images/**", "/logos/**").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/home","/login").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/spellOutcomes", "/spellCastingOutcomes","/additionalRandomOutcomes","/additionalRandomOutcomes/**",
                "/appUsers","/appUsers/**","/characterClasses","/characterClasses/**","/components","/components/**","/roles","/roles/**","/spells","/customTypeOfSpells","/customTypeOfSpells/**").authenticated();
        httpSecurity.addFilterBefore(new CustomAuthenticationFilter(appUserService, tokenUtil), UsernamePasswordAuthenticationFilter.class);
    }
}
