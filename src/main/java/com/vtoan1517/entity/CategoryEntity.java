package com.vtoan1517.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "category")
@Getter
@Setter
public class CategoryEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String code;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private CategoryEntity parent;

    @OneToMany(mappedBy = "parent")
    private Set<CategoryEntity> subCategories = new HashSet<>();

    @OneToMany(mappedBy = "category")
    private List<ArticleEntity> articles = new ArrayList<>();
}
