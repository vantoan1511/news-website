package com.vtoan1517.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class CategoryDTO extends BaseDTO {
    private String code;
    private String name;
    private String parentCode;
}
