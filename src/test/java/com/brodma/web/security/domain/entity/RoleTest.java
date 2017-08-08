package com.brodma.web.security.domain.entity;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class RoleTest {

    @Test
    public void shouldVerifyCorrectEqualsImpl() {
        LoginUser superUser = new LoginUser();
        superUser.setUserName("alice");
        LoginUser admin = new LoginUser();
        admin.setUserName("bob");
        Privilege add = new Privilege();
        add.setName("add");
        Privilege delete = new Privilege();
        delete.setName("delete");
        EqualsVerifier.forClass(Role.class)
                .withIgnoredFields("id")
                .withPrefabValues(LoginUser.class,superUser, admin)
                .withPrefabValues(Privilege.class,add, delete)
                .verify();
    }

}