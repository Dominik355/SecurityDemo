package com.example.securityDemo.Security.userStatistics;

import org.springframework.stereotype.Component;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.logging.Logger;

@Component
public class CustomSessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        Logger.getGlobal().info("Session created: " + se.getSession().getId()
                + ", source: " + se.getSource());
        if(se.getSession().getAttribute("user") == null) {
            se.getSession().setAttribute("visitor", new Object(){
                @Override
                public String toString() {
                    return super.toString();
                }
            });
        }
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        Logger.getGlobal().info("Session destroyed: " + se.getSession().getId()
                + ", source: " + se.getSource());
        se.getSession().removeAttribute("user");
    }

}
