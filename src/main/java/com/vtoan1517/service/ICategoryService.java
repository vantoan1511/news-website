package com.vtoan1517.service;

import com.vtoan1517.dto.CategoryDTO;
import com.vtoan1517.exception.CategoryNotFoundException;

import java.util.Map;

public interface ICategoryService {
    CategoryDTO save(CategoryDTO categoryDTO);

    Map<String, String> findAll();

    CategoryDTO findByCode(String code) throws CategoryNotFoundException;
}
