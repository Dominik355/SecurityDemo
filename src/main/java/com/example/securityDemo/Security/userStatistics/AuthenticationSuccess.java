package com.example.securityDemo.Security.userStatistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Component("myAuthenticationSuccessHandler")
public class AuthenticationSuccess implements AuthenticationSuccessHandler {

    @Autowired
    private ActiveUserStore activeUserStore;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request
            , HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        HttpSession session = request.getSession(false);
        if(session != null) {
            session.setAttribute("user", new LoggedUser(authentication.getName(), activeUserStore));
        }
        handleLogin(request, response, authentication);

    }

    protected void handleLogin(HttpServletRequest request
            , HttpServletResponse response, Authentication authentication) throws IOException {

        String targetURL = determineUserUrl(authentication);
        if(response.isCommitted()) {
            Logger.getGlobal().info("Response has already been comitted. " +
                    "Unable to redirect user \"" + authentication.getName() + "\" to: " + targetURL);
            return;
        }
        redirectStrategy.sendRedirect(request, response, targetURL);

    }

    protected String determineUserUrl(final Authentication authentication) {

        Map<String, String> roleUrl = new HashMap<>();
        roleUrl.put("ROLE_STUDENT", "/courses_student");
        roleUrl.put("ROLE_TEACHER", "/courses_teacher");
        roleUrl.put("ROLE_ADMIN", "/courses_teacher");
        final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        return roleUrl.get(authorities
                .stream()
                .filter(a -> roleUrl.containsKey(a.getAuthority()))
                .findFirst()
                .get()
                .toString());

    }

}
