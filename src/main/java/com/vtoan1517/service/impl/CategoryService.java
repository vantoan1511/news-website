package com.vtoan1517.service.impl;

import com.vtoan1517.dto.CategoryDTO;
import com.vtoan1517.entity.Category;
import com.vtoan1517.exception.CategoryNotFoundException;
import com.vtoan1517.repository.CategoryRepository;
import com.vtoan1517.service.ICategoryService;
import com.vtoan1517.utils.CollectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;

    private final CollectionMapper mapper = new CollectionMapper();

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        Category parent = categoryRepository.findByCode(categoryDTO.getParentCode());
        Category categoryEntity = mapper.map(categoryDTO, Category.class);
        categoryEntity.setParent(parent);
        return mapper.map(categoryRepository.save(categoryEntity), CategoryDTO.class);
    }

    @Override
    public Map<String, String> findAll() {
        return mapper.map(categoryRepository.findAll(), Category::getCode, Category::getName);
    }

    @Override
    public CategoryDTO findByCode(String code) throws CategoryNotFoundException {
        Category category = categoryRepository.findByCode(code);
        if (category == null) throw new CategoryNotFoundException("Không tìm thấy chuyên mục " + code);
        return mapper.map(category, CategoryDTO.class);
    }
}
