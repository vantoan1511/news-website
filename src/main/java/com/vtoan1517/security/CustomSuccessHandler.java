package com.vtoan1517.security;

import com.vtoan1517.utils.SecurityUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
@Getter
@Setter
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        String targetUrl;
        SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, response);
        if (savedRequest != null && savedRequest.getRedirectUrl() != null) {
            targetUrl = savedRequest.getRedirectUrl();
        } else {
            targetUrl = determineTargetUrl(authentication);
        }
        if (response.isCommitted()) {
            return;
        }
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    private String determineTargetUrl(Authentication authentication) {
        String url = "";
        List<String> roles = SecurityUtils.getAuthorities();
        if (isAdmin(roles) || isAuthor(roles)) {
            url = "/admin/home";
        } else {
            url = "/home";
        }
        return url;
    }

    private boolean isAdmin(List<String> roles) {
        return roles.contains("ADMIN") || roles.contains("admin");
    }

    private boolean isAuthor(List<String> roles) {
        return roles.contains("author") || roles.contains("AUTHOR");
    }

    private boolean isUser(List<String> roles) {
        return roles.contains("USER") || roles.contains("user");
    }
}
