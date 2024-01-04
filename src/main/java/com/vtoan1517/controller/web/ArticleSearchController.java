package com.vtoan1517.controller.web;

import com.vtoan1517.dto.ArticleDTO;
import com.vtoan1517.dto.Model;
import com.vtoan1517.service.IArticleRetrievalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/search/")
public class ArticleSearchController {

    private final IArticleRetrievalService articleService;

    @Autowired
    public ArticleSearchController(IArticleRetrievalService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public ModelAndView showPage(@RequestParam(name = "page", defaultValue = "1") int page,
                                 @RequestParam(name = "limit", defaultValue = "10") int limit,
                                 @RequestParam(name = "sortBy", defaultValue = "modifiedDate") String sortBy,
                                 @RequestParam(name = "sortOrder", defaultValue = "desc") String sortOrder,
                                 @RequestParam("q") String q) {

        Sort.Direction direction = sortOrder.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = new PageRequest(page - 1, limit, new Sort(direction, sortBy));

        Page<ArticleDTO> searchResults = articleService.findAllByKeyword(q, pageable);
        Model model = Model.builder()
                .page(page)
                .limit(limit)
                .sortBy(sortBy)
                .sortOrder(sortOrder)
                .totalPages(searchResults.getTotalPages())
                .totalItems(searchResults.getTotalElements())
                .data(searchResults.getContent())
                .build();

        String viewName = "web/search";
        ModelAndView mav = new ModelAndView(viewName);
        mav.addObject("model", model);
        return mav;
    }
}
