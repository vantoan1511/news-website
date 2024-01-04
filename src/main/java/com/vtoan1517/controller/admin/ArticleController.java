package com.vtoan1517.controller.admin;

import com.vtoan1517.dto.ArticleDTO;
import com.vtoan1517.dto.Model;
import com.vtoan1517.exception.ArticleNotFoundException;
import com.vtoan1517.service.IAccessService;
import com.vtoan1517.service.IArticleRetrievalService;
import com.vtoan1517.service.ICategoryService;
import com.vtoan1517.service.IStatusService;
import com.vtoan1517.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller(value = "AdminArticleController")
@RequestMapping("/admin/articles")
public class ArticleController {

    private final ICategoryService categoryService;

    private final IAccessService accessService;

    private final IStatusService statusService;

    private final IArticleRetrievalService articleService;

    @Autowired
    public ArticleController(ICategoryService categoryService, IAccessService accessService,
                             IArticleRetrievalService articleService, IStatusService statusService) {
        this.categoryService = categoryService;
        this.accessService = accessService;
        this.articleService = articleService;
        this.statusService = statusService;
    }

    @GetMapping
    public ModelAndView listArticle(@RequestParam(name = "tab", required = false, defaultValue = "all") String tab,
                                    @RequestParam(name = "page", defaultValue = "1") int page,
                                    @RequestParam(name = "limit", defaultValue = "5") int limit,
                                    @RequestParam(name = "sortBy", defaultValue = "modifiedDate") String sortBy,
                                    @RequestParam(name = "sortOrder", defaultValue = "desc") String sortOrder) {

        String author = SecurityUtils.getPrincipal().getUsername();
        List<String> roles = SecurityUtils.getAuthorities();

        Sort.Direction direction = sortOrder.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = new PageRequest(page - 1, limit, new Sort(direction, sortBy));

        Page<ArticleDTO> items;
        items = articleService.findAll(pageable);

        if (roles.contains("admin")) {
            if (tab.equalsIgnoreCase("all")) {
                items = articleService.findAll(pageable);
            } else if (tab.equalsIgnoreCase("featured")) {
                //items = articleService.findAllByFeatured(true, pageable);
            } else {
                //items = articleService.findAllByStatusCode(tab, pageable);
            }
        } else {
            if (tab.equalsIgnoreCase("all")) {
                //items = articleService.findAllByAuthor(author, pageable);
            } else if (tab.equalsIgnoreCase("featured")) {
                //items = articleService.findAllByFeaturedAndAuthor(true, author, pageable);
            } else {
                //items = articleService.findAllByStatusCodeAndAuthor(tab, author, pageable);
            }
        }
/*
        long totalItems = articleService.getTotalItems(author);
        int totalPages = (int) Math.ceil((double) totalItems / limit);
*/

        String viewName = "admin/article/list";
        ModelAndView mav = new ModelAndView(viewName);
        Model<Object> model = Model.builder()
                .page(page)
                .limit(limit)
                .sortBy(sortBy)
                .sortOrder(sortOrder)
                .totalPages(items.getTotalPages())
                .totalItems(items.getTotalElements())
                .data(items.getContent())
                .build();
        mav.addObject("model", model);
        return mav;
    }

    @GetMapping({"/new", "/{id}"})
    public ModelAndView createArticle(@PathVariable(name = "id", required = false) Long id) throws ArticleNotFoundException {
        List<String> roles = SecurityUtils.getAuthorities();
        ArticleDTO articleDTO = new ArticleDTO();

        if (id != null) {
            articleDTO = articleService.findById(id);
        }

        String viewName = "admin/article/details";

        ModelAndView mav = new ModelAndView(viewName);
        mav.addObject("model", articleDTO);
        mav.addObject("categories", categoryService.findAll());
        mav.addObject("access", accessService.findAll());
        mav.addObject("status", statusService.findAll());
        return mav;
    }
}
