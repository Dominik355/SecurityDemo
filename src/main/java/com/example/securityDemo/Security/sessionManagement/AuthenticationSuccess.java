package com.example.securityDemo.Security.sessionManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;

@Component("myAuthenticationSuccessHandler")
public class AuthenticationSuccess implements AuthenticationSuccessHandler {

    @Autowired
    private ActiveUserStore activeUserStore;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if(session != null) {
            int i = 0;
            session.setAttribute("user", new LoggedUser(authentication.getName(), activeUserStore));
            System.out.println("AUTHENTICATION SUCCESFUL: " + authentication.getName());
        }
    }

}
