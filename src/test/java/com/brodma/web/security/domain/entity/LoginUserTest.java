package com.brodma.web.security.domain.entity;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class LoginUserTest {

    @Test
    public void shouldVerifyCorrectEqualsImpl() {
        Role superUser = new Role();
        superUser.setName("superUser");
        Role admin = new Role();
        admin.setName("admin");
        EqualsVerifier.forClass(LoginUser.class)
                .withIgnoredFields("id")
                .withPrefabValues(Role.class, superUser, admin)
                .verify();
    }
}