package com.vtoan1517.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "request")
@Getter
@Setter
public class RequestEntity extends BaseEntity{

    private String reason;
}
