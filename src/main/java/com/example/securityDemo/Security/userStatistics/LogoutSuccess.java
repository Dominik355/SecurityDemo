package com.example.securityDemo.Security.userStatistics;

import com.example.securityDemo.Repositories.UserLoginRecordRepository;
import com.example.securityDemo.Security.securityEnums.LoginEnum;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private ActiveUsersService activeUsersService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request
            , HttpServletResponse response
            , Authentication authentication) throws IOException, ServletException {

        HttpSession session = request.getSession();
        if(session != null) {
            if(session.getAttribute(LoginEnum.USER.name()) == null) {
                Logger.getGlobal().info("User logged out after session expired");;
            } else {
                makeRecord(session.getId());
                session.removeAttribute(LoginEnum.USER.name());
            }
            session.invalidate();
        }
        response.sendRedirect("/login");

    }

    /**
     *  Deletes user from activeUserStore and create LoginRecord
     * @param sessionId
     */
    private void makeRecord(String sessionId) {
        activeUsersService.removeActiveUser(sessionId, LoginEnum.LOGOUT);
    }

}
