package com.oc.dandfriends.token;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TokenUtilTest {
    private TokenUtil tokenUtilUnderTest;

    @BeforeEach
    void setup(){
        tokenUtilUnderTest = new TokenUtil();
        tokenUtilUnderTest.setJwtSecret("secret");
    }

    @Test
    public void checkCheckTokenAndRetrieveUsernameFromIt_shouldReturnAUsername_whenGivenAValidToken(){
        //GIVEN
        String token ="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwZXRlckBnbWFpbC5jb20iLCJleHAiOjE2NjA0NjUzNDZ9.QQG6qlJvhKJV7Oz6HAjZDaXK1QeOJax4TigFlWj9FGQ";
        //WHEN
        String usernameUnderTest = tokenUtilUnderTest.checkTokenAndRetrieveUsernameFromIt(token);
        //THEN
        assertThat(usernameUnderTest).isEqualTo("peter@gmail.com");
    }

}