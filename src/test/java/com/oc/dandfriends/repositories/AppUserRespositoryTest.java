package com.oc.dandfriends.repositories;

import com.oc.dandfriends.entities.AppUser;
import com.oc.dandfriends.entities.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class AppUserRespositoryTest {
    @Autowired
    private AppUserRepository appUserRepositoryUnderTest;

    @AfterEach
    void tearDown() {
        appUserRepositoryUnderTest.deleteAll();
    }

    @Test
    void checkFindByUserName_whenGivenAValidUsername_shouldReturnAUser() {
        Role role = new Role(1, "ROLE_MJ", null);
        AppUser user1 = new AppUser(1, "paul@gmail.com","paulo","123", role);
        AppUser user2 = new AppUser(2, "john@gmail.com","jonhy","123", role);
        AppUser user3 = new AppUser(3, "lily@gmail.com","lily","123", role);
        appUserRepositoryUnderTest.save(user1);
        appUserRepositoryUnderTest.save(user2);
        appUserRepositoryUnderTest.save(user3);

        AppUser appUserUnderTest = appUserRepositoryUnderTest.findByUsername("lily@gmail.com");
        assertThat(appUserUnderTest.getPseudo()).isEqualTo("lily");
    }

}
