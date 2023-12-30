package com.vtoan1517.controller.web;

import com.vtoan1517.dto.UserDTO;
import com.vtoan1517.service.IUserService;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    private final MessageSource source;

    private final IUserService userService;

    public AccountController(MessageSource source,
                             IUserService userService) {
        this.source = source;
        this.userService = userService;
    }

    @GetMapping("/activate")
    public ModelAndView activate(@RequestParam("token") String token, RedirectAttributes attributes) {

        String viewName = "redirect:/login";

        if (token != null) {
            UserDTO userDTO = userService.findByToken(token);
            if (userDTO != null && userService.activate(token) != null) {
                attributes.addFlashAttribute("type", "success");
                attributes.addFlashAttribute("message", source.getMessage("account.activated", null, null));
                return new ModelAndView(viewName);
            }
        }
        attributes.addFlashAttribute("type", "danger");
        attributes.addFlashAttribute("message", "Truy cập không hợp lệ");
        return new ModelAndView(viewName);
    }
}
