package com.brodma.web.security.domain;

import com.brodma.web.security.domain.entity.LoginUser;
import com.brodma.web.security.domain.entity.Role;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountDetailsTest {

    private AccountDetails sut;

    @Mock
    private LoginUser loginUser;

    @Mock
    private Role admin;

    @Mock
    private Role superUser;

    @Before
    public void setUp() {
        when(admin.getName()).thenReturn(RoleDesc.ADMIN.getDesc());
        when(superUser.getName()).thenReturn(RoleDesc.SUPER_USER.getDesc());
        Set<Role> roles = new HashSet<>();
        roles.add(admin);
        roles.add(superUser);
        when(loginUser.getRoles()).thenReturn(roles);
        sut = new AccountDetails(loginUser);
    }

    @Test
    public void shouldVerifySuperUserRoleAndAdminRole() {
        assertTrue(sut.hasAdminRole());
        assertTrue(sut.hasSuperUserRole());
    }

    @Test
    public void shouldVerifyOnlySuperUserRole() {
        Set<Role> roles = new HashSet<>();
        roles.add(superUser);
        when(loginUser.getRoles()).thenReturn(roles);
        assertTrue(sut.hasSuperUserRole());
        assertFalse(sut.hasAdminRole());
    }

    @Test
    public void shouldVerifyOnlyAdminRole() {
        Set<Role> roles = new HashSet<>();
        roles.add(admin);
        when(loginUser.getRoles()).thenReturn(roles);
        assertTrue(sut.hasAdminRole());
        assertFalse(sut.hasSuperUserRole());
    }

    @Test
    public void shouldNotVerifySuperUserRole() {
        when(loginUser.getRoles()).thenReturn(Collections.emptySet());
        assertFalse(sut.hasSuperUserRole());
    }

    @Test
    public void shouldNotVerifyAdminRole() {
        when(loginUser.getRoles()).thenReturn(Collections.emptySet());
        assertFalse(sut.hasAdminRole());
    }
}