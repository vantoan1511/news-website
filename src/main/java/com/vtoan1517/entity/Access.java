package com.vtoan1517.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "access")
@Getter
@Setter
public class Access extends Base {

    public static final String ACCESS_PUBLIC = "public";
    public static final String ACCESS_PRIVATE = "private";
    public static final String ACCESS_READ = "read";

    @Column(unique = true, nullable = false)
    private String code;

    @Column
    private String name;

    @OneToMany(mappedBy = "access")
    private List<Article> articles = new ArrayList<>();
}
