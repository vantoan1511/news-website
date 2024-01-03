package com.vtoan1517.controller.web;

import com.vtoan1517.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/search/")
public class ArticleSearchController {

    private final IArticleService articleService;

    @Autowired
    public ArticleSearchController(IArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public ModelAndView showPage(@RequestParam("q") String q) {

        String viewName = "web/search";
        ModelAndView mav = new ModelAndView(viewName);
        mav.addObject("articles", articleService.findByKeyword(q));
        return mav;
    }
}
