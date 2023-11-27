package com.oc.dandfriends.security;

import com.oc.dandfriends.services.AppUserService;
import com.oc.dandfriends.token.TokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Log4j2
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    private final AppUserService appUserService;
    private final TokenUtil tokenUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        {
            log.info("in CustomAuthenticationFilter in doFilterInternal");

            try {
                String token = getCookieValue(request, "tokenDandFriends");
                if (token!=null) {
                    log.info("in CustomAuthenticationFilter in doFilterInternal in jwt token is not null");

                    log.info("in CustomAuthenticationFilter in doFilterInternal with token {}", token);
                    String username = tokenUtil.checkTokenAndRetrieveUsernameFromIt(token);
                    log.info("in CustomAuthenticationFilter in doFilterInternal with {}", username);
                    UserDetails userDetails = appUserService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }

            } catch (Exception e) {
                log.error("Cannot set user authentication", e);
            }
            filterChain.doFilter(request, response);
        }
    }

    private String getCookieValue(HttpServletRequest request, String cookieName) {
        log.info("in CustomAuthenticationFilter in getCookieValue");

        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            log.info("in CustomAuthenticationFilter in getCookieValue: cookies are null or empty");
            return null;
        }

        // Log all cookies
        for(Cookie cookie : cookies) {
            log.info("in CustomAuthenticationFilter in getCookieValue: Cookie name: {} and value: {}", cookie.getName(), cookie.getValue());
        }

        // Retrieve the desired cookie's value
        String value = Arrays.stream(cookies)
                .filter(c -> c.getName().equals(cookieName))
                .findFirst()
                .map(Cookie::getValue)
                .orElse(null);

        log.info("in CustomAuthenticationFilter in getCookieValue: Retrieved cookie {} with value: {}", cookieName, value);
        return value;
    }

}
