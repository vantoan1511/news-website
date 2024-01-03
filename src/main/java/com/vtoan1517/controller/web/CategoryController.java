package com.vtoan1517.controller.web;

import com.vtoan1517.exception.CategoryNotFoundException;
import com.vtoan1517.service.IArticleService;
import com.vtoan1517.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/categories/")
public class CategoryController {

    private final IArticleService articleService;

    private final ICategoryService categoryService;

    @Autowired
    public CategoryController(ICategoryService categoryService, IArticleService articleService) {
        this.categoryService = categoryService;
        this.articleService = articleService;
    }

    @GetMapping("/{categoryCode}")
    public ModelAndView showPage(@PathVariable("categoryCode") String categoryCode,
                                 @RequestParam(name = "page", defaultValue = "1") int page,
                                 @RequestParam(name = "limit", defaultValue = "10") int limit,
                                 @RequestParam(name = "sortBy", defaultValue = "modifiedDate") String sortBy,
                                 @RequestParam(name = "sortOrder", defaultValue = "desc") String sortOrder) throws CategoryNotFoundException {

        Sort.Direction direction = sortOrder.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        PageRequest articleReq = new PageRequest(page - 1, limit, new Sort(direction, sortBy));

        ModelAndView mav = new ModelAndView("web/category");
        mav.addObject("category", categoryService.findByCode(categoryCode));
        mav.addObject("articles", articleService.findAllByCategoryCode(categoryCode, articleReq));
        return mav;
    }
}
