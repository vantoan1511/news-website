package com.vtoan1517.controller.admin;

import com.vtoan1517.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller(value = "AdminHomeController")
public class HomeController {

    private final IArticleService articleService;

    @Autowired
    public HomeController(IArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/admin/home")
    public ModelAndView home() {

        String viewName = "admin/home";
        ModelAndView mav = new ModelAndView(viewName);
        return mav;
    }
}
