package com.vtoan1517.controller.web;

import com.vtoan1517.dto.UserDTO;
import com.vtoan1517.exception.EmailNotFoundException;
import com.vtoan1517.exception.InvalidUserTokenException;
import com.vtoan1517.service.IUserService;
import com.vtoan1517.utils.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Locale;

@Controller(value = "LoginController")
public class LoginController {

    private final IUserService userService;

    private final MessageSource messageSource;

    @Autowired
    public LoginController(MessageSource messageSource, IUserService userService) {
        this.messageSource = messageSource;
        this.userService = userService;
    }

    @GetMapping("/login")
    public ModelAndView showLogin(@RequestParam(value = "invalidCredentials", required = false) boolean invalidCredentials,
                                  @RequestParam(value = "invalidSession", required = false) boolean invalidSession,
                                  Locale locale,
                                  RedirectAttributes attributes) {
        String viewName = "redirect:/login";

        if (invalidCredentials) {
            attributes.addFlashAttribute("message",
                    new FlashMessage(FlashMessage.DANGER, messageSource.getMessage("credentials.invalid", null, locale)));
        } else if (invalidSession) {
            attributes.addFlashAttribute("message",
                    new FlashMessage(FlashMessage.WARNING, messageSource.getMessage("session.invalid", null, locale)));
        } else {
            viewName = "web/login";
        }

        return new ModelAndView(viewName);
    }

    @GetMapping("/forgot")
    public ModelAndView showForGotPage() {

        ModelAndView mav = new ModelAndView("web/forgot");
        return mav;
    }

    @PostMapping("/forgot")
    public ModelAndView reset(@RequestParam("email") String email, RedirectAttributes attributes) {

        String viewName = "web/forgot";

        try {
            userService.resetPassword(email);
            attributes.addFlashAttribute("message",
                    new FlashMessage(FlashMessage.INFO, "Kiểm tra email để reset mật khẩu"));
            viewName = "redirect:/login";
        } catch (EmailNotFoundException e) {
            attributes.addFlashAttribute("message",
                    new FlashMessage(FlashMessage.DANGER, e.getMessage()));
            viewName = "redirect:/forgot";
        }

        return new ModelAndView(viewName);
    }

    @GetMapping("/reset")
    public ModelAndView showReset(@RequestParam(value = "token", required = false) String token,
                                  RedirectAttributes attributes) {

        String viewName = "redirect:/login";

        if (token == null || token.isBlank() || !userService.foundByToken(token)) {
            attributes.addFlashAttribute("message",
                    new FlashMessage(FlashMessage.DANGER, "Đường dẫn đã hết hạn hoặc không tồn tại"));
        } else {
            viewName = "web/recover";
        }

        return new ModelAndView(viewName);
    }

    @PostMapping("/reset")
    public ModelAndView changePW(@RequestParam("token") String token,
                                 @RequestParam("password") String password,
                                 RedirectAttributes attributes) {

        String viewName = "web/recover";

        try {
            userService.changePassword(token, password);
            attributes.addFlashAttribute("message",
                    new FlashMessage(FlashMessage.SUCCESS, "Đổi mật khẩu thành công. Đăng nhập để tiếp tục"));
            viewName = "redirect:/login";
        } catch (InvalidUserTokenException e) {
            attributes.addFlashAttribute("message",
                    new FlashMessage(FlashMessage.DANGER, e.getMessage()));
        }

        return new ModelAndView(viewName);
    }
}
