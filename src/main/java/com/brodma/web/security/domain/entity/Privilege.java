package com.brodma.web.security.domain.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "privilege")
public class Privilege implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "privilege_privilege_id_sequence", allocationSize = 10)
    @Column(name="privilege_id")
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Set<Role> roles = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        return Optional.ofNullable(o)
                .filter(that -> that instanceof Privilege)
                .map(that -> (Privilege)that)
                .filter(that -> Objects.equals(this.name, that.name))
                .filter(that -> Objects.equals(this.roles, that.roles))
                .isPresent();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, roles);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Privilege{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name);
        sb.append(", roles=").append(roles);
        sb.append('}');
        return sb.toString();
    }
}
