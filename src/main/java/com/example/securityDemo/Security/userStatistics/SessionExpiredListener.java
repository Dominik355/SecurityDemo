package com.example.securityDemo.Security.userStatistics;

import com.example.securityDemo.Security.securityEnums.LoginEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.session.events.SessionExpiredEvent;
import org.springframework.stereotype.Component;
import java.util.logging.Logger;

@Component
public class SessionExpiredListener implements ApplicationListener<SessionExpiredEvent> {

    @Autowired
    private ActiveUsersService activeUsersService;

    /**
     * When session expires, it is added to statistics with reason - expired
     */
    @Override
    public void onApplicationEvent(SessionExpiredEvent event) {
        try {
            LoggedUser user = event.getSession().getAttribute(LoginEnum.USER.name());
            makeRecord(event.getSessionId());
        } catch (NullPointerException e) {

        }
    }

    private void makeRecord(String sessionId) {
        activeUsersService.removeActiveUser(sessionId, LoginEnum.SESSION_EXPIRATION);
    }

}
