package com.vtoan1517.controller.web;

import com.vtoan1517.utils.FlashMessage;
import org.springframework.stereotype.Controller;
import org.springframework.util.ErrorHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ErrorController {

    @GetMapping("/access-denied")
    public String error(RedirectAttributes attributes) {
        attributes.addFlashAttribute("message",
                new FlashMessage(FlashMessage.DANGER, "Bạn không có quyền truy cập"));
        return "redirect:/login";
    }
}
