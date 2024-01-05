package com.vtoan1517.entity;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "status")
@Getter
@Setter
public class Status extends Base {

    public static final String STATUS_DRAFT = "draft";
    public static final String STATUS_PENDING = "pending";
    public static final String STATUS_PUBLISHED = "published";
    public static final String STATUS_TRASH = "trash";
    public static final String STATUS_ARCHIVED = "archived";

    @Column(nullable = false, unique = true)
    private String code;

    @Column
    private String name;
}
