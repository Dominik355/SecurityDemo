package com.example.securityDemo.Security.userStatistics;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.util.stream.IntStream;
//***********NEFUNGUJE - neviem ako ovrridnut logout() funkciu v: PersistentTokenBasedRememberMeServices
// alebo aspektom do ten dat : Authentication = null,
// aby sa nevymazali otkeny a mohol som to spravit podla seba po vykonany joinpointu
@Component
@Aspect
public class AspectForDisablingRememberMeLogout {

    @Around("rememberMeLogout()")
    public void RememberMeLogoutAdvice(ProceedingJoinPoint joinPoint) {
        Object[] newArgs = joinPoint.getArgs();
        try {
            IntStream.range(0, newArgs.length)
                    .filter(i -> newArgs[i] instanceof Authentication)
                    .forEach(i -> newArgs[i] = null);
            joinPoint.proceed(newArgs);
        } catch(Throwable t) {
            t.printStackTrace();
        }
    }

    @Pointcut("execution(* org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices.logout(..))")
    public void rememberMeLogout() {}

}
