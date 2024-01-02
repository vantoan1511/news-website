package com.vtoan1517.controller.web;

import com.vtoan1517.dto.ArticleDTO;
import com.vtoan1517.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller(value = "WebHomeController")
public class HomeController {

    private final IArticleService articleService;

    @Autowired
    public HomeController(IArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/home")
    public ModelAndView home() {

        PageRequest featuredReq = new PageRequest(0, 3, new Sort(Sort.Direction.DESC, "modifiedDate"));
        PageRequest latestReq = new PageRequest(0, 10, new Sort(Sort.Direction.DESC, "modifiedDate"));
        PageRequest popularReq = new PageRequest(0, 10, new Sort(Sort.Direction.DESC, "traffic"));

        List<ArticleDTO> featured = articleService.findAllByStatusCodeAndFeatured("published", true, featuredReq);
        List<ArticleDTO> latest = articleService.findAllByStatusCode("published", latestReq);
        List<ArticleDTO> popular = articleService.findAllByStatusCode("published", popularReq);

        ModelAndView mav = new ModelAndView("web/home");
        mav.addObject("latestArticles", articleService.findAll());
        mav.addObject("featured", featured);
        mav.addObject("latest", latest);
        mav.addObject("popular", popular);

        return mav;
    }
}
