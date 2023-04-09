package com.demo.bank.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="ROLES")
public class Role
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ROLE_ID")
    private Long id;

    @Column(nullable=false, unique=true,name="NAME")
    private String name;

    @ManyToMany(mappedBy="roles")
    private List<User> users;

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

/*    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }*/
}
