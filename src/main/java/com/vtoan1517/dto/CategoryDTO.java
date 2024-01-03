package com.vtoan1517.dto;

import com.vtoan1517.entity.Article;
import com.vtoan1517.entity.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class CategoryDTO extends BaseDTO {
    private String code;
    private String name;
    private String parentCode;
    private String parentName;
    private Set<CategoryDTO> subCategories = new HashSet<>();
    private List<ArticleDTO> articles = new ArrayList<>();
}
