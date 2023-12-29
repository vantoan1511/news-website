package com.vtoan1517.controller.web;

import com.vtoan1517.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "WebHomeController")
public class HomeController {

    private final IArticleService articleService;

    @Autowired
    public HomeController(IArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/home")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView("web/home");
        mav.addObject("latestArticles", articleService.findAll());
        return mav;
    }
}
