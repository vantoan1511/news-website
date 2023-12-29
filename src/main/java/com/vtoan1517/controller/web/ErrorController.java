package com.vtoan1517.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/access-denied")
    public String error() {
        return "redirect:/login?accessDenied";
    }
}
