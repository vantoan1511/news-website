package com.vtoan1517.security;

import com.vtoan1517.entity.Role;
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

    private String nextUrl;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        String targetUrl;
        SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, response);
        if (nextUrl != null && !nextUrl.isBlank()) {
            targetUrl = nextUrl;
        } else if (savedRequest != null && savedRequest.getRedirectUrl() != null) {
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
        List<String> roles = SecurityUtils.getAuthorities();
        return (isAdmin(roles) || isAuthor(roles)) ? "/admin/home" : "/home";
    }

    private boolean isAdmin(List<String> roles) {
        return roles.contains(Role.ROLE_ADMIN);
    }

    private boolean isAuthor(List<String> roles) {
        return roles.contains(Role.ROLE_AUTHOR);
    }

    private boolean isUser(List<String> roles) {
        return roles.contains(Role.ROLE_USER);
    }
}
