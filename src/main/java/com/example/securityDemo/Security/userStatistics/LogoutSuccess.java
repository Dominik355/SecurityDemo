package com.example.securityDemo.Security.userStatistics;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.logging.Logger;

@Component("myLogoutSuccessHandler")
public class LogoutSuccess implements LogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(HttpServletRequest request
            , HttpServletResponse response
            , Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession();
        if(session != null) {
            if(session.getAttribute("user") == null) {
                System.out.println("User logged out after session expired");
            } else {
                Logger.getGlobal().info("LogoutSuccess called: " + authentication.getName());
                session.removeAttribute("user");
            }
            session.invalidate();
        }
        response.sendRedirect("/login");
    }

}
