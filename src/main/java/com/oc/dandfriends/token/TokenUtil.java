package com.oc.dandfriends.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Log4j2
@Component
@NoArgsConstructor
@Setter
public class TokenUtil {

    @Value("${p7v2api.api.jwtSecret}")
    private String jwtSecret;

    private Algorithm getAlgorithm() {
        log.info("in CustomCheckToken in get Algo");
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes(StandardCharsets.UTF_8));
        return algorithm;
    }

    public String createToken(User user) {
        log.info("in CustomAuthenticationFilter in createToken");
        Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes(StandardCharsets.UTF_8));
        String accessToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 13 * 24 * 60 * 60 * 1000)) // 13 jours
                .sign(algorithm);
        log.info("in CustomAuthentication in createToken with token {}", accessToken);
        return accessToken;
    }

    public String checkTokenAndRetrieveUsernameFromIt(String token) {
        log.info("in CustomCheckToken in checkTokenAndRetrieveUsernameFromIt with token {}", token);
        Algorithm algorithm = getAlgorithm();
        //verification de la validité du token
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        String username = decodedJWT.getSubject();
        log.info("in CustomCheckToken in checkTokenAndRetrieveUsernameFromIt with username {}", username);
        Date expirationDate = decodedJWT.getExpiresAt();
        log.info("in CustomAuthentication in checkTokenAndRetrieveUsernameFromIt with date {}", expirationDate);
        return username;

    }

}
