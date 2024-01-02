package com.vtoan1517.controller.admin;

import com.vtoan1517.service.IMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin/storage")
public class StorageController {

    private final IMediaService mediaService;

    @Autowired
    public StorageController(IMediaService mediaService) {
        this.mediaService = mediaService;
    }

    @GetMapping
    public ModelAndView show() {

        ModelAndView mav = new ModelAndView("admin/storage/storage");
        mav.addObject("images", mediaService.findAll());
        return mav;
    }
}
