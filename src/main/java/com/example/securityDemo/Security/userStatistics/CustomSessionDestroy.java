package com.example.securityDemo.Security.userStatistics;

import org.springframework.context.ApplicationListener;
import org.springframework.session.events.SessionDestroyedEvent;
import org.springframework.stereotype.Component;
import java.util.logging.Logger;

/*
* pri pouziti ukladania sessions do databazy, sa metoda value unbound nezavola pri expiraci sessionu
* avsak sessiondestroyedevent je vytvoreny. Event je zavolany az po valueUnbound, takze mozem na nom skontrolovat ci
* user je este prihlaseny(lebo pri normalnom odhlaseni ho vymaze value unbound), ak nie - vymazem ho, a pridam info ze mu
* expiroval cas prihlasenia
* */
@Component
public class CustomSessionDestroy implements ApplicationListener<SessionDestroyedEvent> {

    @Override
    public void onApplicationEvent(SessionDestroyedEvent event) {
        Logger.getGlobal().info("Session destroyed event created: ");
        event.getSession().removeAttribute("user");
    }
}
