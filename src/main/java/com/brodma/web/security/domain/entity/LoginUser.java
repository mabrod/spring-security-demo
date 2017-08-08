package com.brodma.web.security.domain.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "login_users")
public class LoginUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "login_users_user_id_sequence", allocationSize = 10)
    @Column(name="user_id")
    private Long id;
    private String userName;
    private String password;
    private Boolean active;
    private Boolean expired;
    private Boolean locked;

    @ManyToMany(fetch=FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "login_users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public void addRole(Role role) {
        roles.add(role);
        role.getUsers().add(this);
    }

    public void removeRole(Role role) {
        roles.remove(role);
        role.getUsers().remove(this);
    }

    @Override
    public boolean equals(Object o) {
       return Optional.ofNullable(o)
               .filter(that -> that instanceof LoginUser)
               .map(that -> (LoginUser)that)
               .filter(that -> Objects.equals(this.userName, that.userName))
               .filter(that -> Objects.equals(this.password, that.password))
               .filter(that -> Objects.equals(this.active, that.active))
               .filter(that -> Objects.equals(this.expired, that.expired))
               .filter(that -> Objects.equals(this.locked, that.locked))
               .filter(that -> Objects.equals(this.roles, that.roles))
               .isPresent();
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password, active, expired, locked, roles);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LoginUser{");
        sb.append("id=").append(id);
        sb.append(", userName='").append(userName);
        sb.append(", password='").append(password);
        sb.append(", active=").append(active);
        sb.append(", expired=").append(expired);
        sb.append(", locked=").append(locked);
        sb.append(", roles=").append(roles);
        sb.append('}');
        return sb.toString();
    }
}


