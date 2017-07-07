package com.brodma.web.security.domain;

import com.brodma.web.security.domain.entity.LoginUser;
import com.brodma.web.security.domain.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.*;
import static java.util.stream.Collectors.toList;

public final class AccountDetails implements UserDetails {

    private final LoginUser user;

    public AccountDetails(final LoginUser user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles().stream().map(p -> new SimpleGrantedAuthority(p.getName())).collect(toList());
    }

    public Set<Role> getRoles() {
        return new HashSet(user.getRoles());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !user.getExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !user.getLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.getActive();
    }

    @Override
    public boolean isEnabled() {
        return user.getActive();
    }

    public boolean hasSuperUserRole() {
        return user.getRoles()
                .stream()
                .anyMatch(r -> RoleDesc.toRole(r.getName()) == RoleDesc.SUPER_USER);
    }

    public boolean hasAdminRole() {
        return user.getRoles()
                .stream()
                .anyMatch(r -> RoleDesc.toRole(r.getName()) == RoleDesc.ADMIN);
    }
}
