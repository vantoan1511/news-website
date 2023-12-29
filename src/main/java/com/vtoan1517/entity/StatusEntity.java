package com.vtoan1517.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "status")
@Getter
@Setter
public class StatusEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String code;

    @Column
    private String name;
}
