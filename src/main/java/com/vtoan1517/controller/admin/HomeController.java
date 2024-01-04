package com.vtoan1517.controller.admin;

import com.vtoan1517.service.IArticleRetrievalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "AdminHomeController")
public class HomeController {

    private final IArticleRetrievalService articleService;

    @Autowired
    public HomeController(IArticleRetrievalService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/admin/home")
    public ModelAndView home() {

        String viewName = "admin/home";
        ModelAndView mav = new ModelAndView(viewName);
        return mav;
    }
}
