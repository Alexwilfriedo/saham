package com.digital.model;

import javax.persistence.*;

import com.digital.model.common.BaseEntity;
import com.digital.util.Constant;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

/**
 * @author babacoul
 */
@Entity
@Table(indexes = {@Index(name = "i_role_role", columnList = "role", unique = true)})
public class Role{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(length = Constant.JPA_CONSTRAINTS_MEDIUM)
    private String role;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<User> users;

    public Role() {
    }



    public Role(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
