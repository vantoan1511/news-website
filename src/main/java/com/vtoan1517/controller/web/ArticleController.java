package com.vtoan1517.controller.web;

import com.vtoan1517.dto.ArticleDTO;
import com.vtoan1517.entity.Role;
import com.vtoan1517.exception.ArticleNotFoundException;
import com.vtoan1517.service.IArticleRetrievalService;
import com.vtoan1517.service.IReviewRetrievalService;
import com.vtoan1517.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller(value = "WebArticleController")
public class ArticleController {

    private final IArticleRetrievalService articleService;

    private final IReviewRetrievalService reviewRetrievalService;


    @Autowired
    public ArticleController(IArticleRetrievalService articleService, IReviewRetrievalService reviewRetrievalService) {
        this.articleService = articleService;
        this.reviewRetrievalService = reviewRetrievalService;
    }

    @GetMapping("/{slug}")
    public ModelAndView single(@PathVariable(name = "slug") String slug,
                               @RequestParam(value = "previewMode", required = false) boolean previewMode) throws ArticleNotFoundException {
        List<String> roles = SecurityUtils.getAuthorities();
        ArticleDTO articleDTO = new ArticleDTO();

        if (previewMode && (roles.contains(Role.ROLE_ADMIN) || roles.contains(Role.ROLE_AUTHOR))) {
            articleDTO = articleService.findBySlug(slug);
        } else {
            articleDTO = articleService.findBySlugAndStatus(slug, "published");
        }

        ModelAndView mav = new ModelAndView("web/details");
        mav.addObject("article", articleDTO);
        Pageable pageable = new PageRequest(0, 2, new Sort(Sort.Direction.DESC, "createdDate"));
        mav.addObject("comments", reviewRetrievalService.findAllByArticleSlug(slug, pageable));
        return mav;
    }
}
