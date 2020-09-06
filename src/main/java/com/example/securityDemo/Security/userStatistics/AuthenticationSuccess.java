package com.example.securityDemo.Security.userStatistics;

import com.example.securityDemo.Repositories.redis.ActiveUserRepository;
import com.example.securityDemo.Security.securityEnums.LoginEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
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
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Component("myAuthenticationSuccessHandler")
public class AuthenticationSuccess implements AuthenticationSuccessHandler {

    @Autowired
    private ActiveUsersService activeUsersService;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request
            , HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        if(authentication != null) {
            HttpSession session = request.getSession(true);
            if(session != null) {
                LoggedUser user = new LoggedUser(
                        authentication.getName()
                        , new Timestamp(session.getCreationTime())
                        , authentication instanceof RememberMeAuthenticationToken ?
                        LoginEnum.REMEMBER_ME.name() : LoginEnum.LOGIN.name());
                session.setAttribute(LoginEnum.USER.name(), user);
                activeUsersService.addActiveUser(user, session.getId());
            }  else
                Logger.getGlobal().info("Session is null");
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
