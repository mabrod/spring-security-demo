package com.brodma.web.security.domain.dto;

import com.brodma.web.security.domain.entity.Role;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserDetails implements Serializable {

    private Set<Role> roles;
    private String username;

    public UserDetails(String username, Set<Role> roles) {
        this.username = username;
        this.roles = roles;
    }

    public Set<Role> getRoles() {
        return new HashSet(roles);
    }

    public String getUsername() {
        return username;
    }
}
