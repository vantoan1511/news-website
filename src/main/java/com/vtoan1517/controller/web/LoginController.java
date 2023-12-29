package com.vtoan1517.controller.web;

import com.vtoan1517.dto.UserDTO;
import com.vtoan1517.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Locale;

@Controller(value = "LoginController")
public class LoginController {

    private final MessageSource messageSource;

    @Autowired
    public LoginController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping("/login")
    public String getPage(Model model, Locale locale,
                          @RequestParam(value = "invalidCredentials", required = false) boolean invalidCredentials,
                          @RequestParam(value = "invalidSession", required = false) boolean invalidSession) {
        if (invalidCredentials) {
            model.addAttribute("type", "danger");
            model.addAttribute("message",
                    messageSource.getMessage("credentials.invalid", null, locale));
        } else if (invalidSession) {
            model.addAttribute("type", "warning");
            model.addAttribute("message", messageSource.getMessage("session.invalid", null, locale));
        }
        model.addAttribute("user", new UserDTO());
        return "web/login";
    }
}
