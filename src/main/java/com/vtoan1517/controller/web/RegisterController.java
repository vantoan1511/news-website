package com.vtoan1517.controller.web;

import com.vtoan1517.dto.UserDTO;
import com.vtoan1517.service.IUserService;
import com.vtoan1517.validator.CustomUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Locale;

@Controller
public class RegisterController {

    private final IUserService userService;

    private final CustomUserValidator userValidator;

    private final MessageSource messageSource;

    @Autowired
    public RegisterController(IUserService userService, CustomUserValidator userValidator, MessageSource messageSource) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.messageSource = messageSource;
    }

    @InitBinder("user")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(userValidator);
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new UserDTO());
        return "web/register";
    }

    @PostMapping("/register")
    public ModelAndView register(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult result,
                                 RedirectAttributes attributes, Locale locale) {
        String path = "";
        ModelAndView mav = new ModelAndView();
        if (result.hasErrors()) {
            path = "web/register";
        } else {
            attributes.addFlashAttribute("type", "success");
            attributes.addFlashAttribute("message", messageSource.getMessage("register.success", null, locale));
            path = "redirect:login";
        }
        mav.setViewName(path);
        return mav;
    }
}
