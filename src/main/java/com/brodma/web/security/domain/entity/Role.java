package com.brodma.web.security.domain.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "roles_role_id_sequence", allocationSize = 10)
    @Column(name="role_id")
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Set<LoginUser> users = new HashSet<>();

    @ManyToMany(fetch=FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "roles_privileges", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "privilege_id"))
    private Set<Privilege> privileges = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {this.id = id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<LoginUser> getUsers() {
        return users;
    }

    public void setUsers(Set<LoginUser> users) {
        this.users = users;
    }

    public Set<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Set<Privilege> privileges) {
        this.privileges = privileges;
    }

    public void addPrivilege(Privilege privilege) {
        privileges.add(privilege);
        privilege.getRoles().add(this);
    }

    public void removeRole(Privilege privilege) {
        privileges.remove(privilege);
        privilege.getRoles().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        return Optional.ofNullable(o)
                .filter(that -> that instanceof Role)
                .map(that -> (Role)that)
                .filter(that -> Objects.equals(this.name, that.name))
                .filter(that -> Objects.equals(this.users, that.users))
                .filter(that -> Objects.equals(this.privileges, that.privileges))
                .isPresent();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, users, privileges);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Role{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name);
        sb.append(", users=").append(users);
        sb.append(", privileges=").append(privileges);
        sb.append('}');
        return sb.toString();
    }
}
