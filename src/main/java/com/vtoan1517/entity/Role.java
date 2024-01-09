package com.vtoan1517.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role")
@Getter
@Setter
public class Role extends Base {

    public static final String ROLE_ADMIN = "admin";
    public static final String ROLE_AUTHOR = "author";
    public static final String ROLE_USER = "user";

    @Column(unique = true, nullable = false)
    private String code;

    @Column
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();
}
