package com.example.securityDemo.Security.userStatistics;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.stream.IntStream;
//***********NEFUNGUJE
@Component
@Aspect
public class AspectForDisablingRememberMeLogout {
//na hovado poslem do logoutu null aby sa nic nevykonalo a urobim co potrebujem - nici ne ma nenapadlo,resp nefungovalo
    @Around("rememberMeLogout()")
    public void RememberMeLogoutAdvice(ProceedingJoinPoint joinPoint) {
        System.out.println("LOG out of remember me called ");
        Object[] newArgs = joinPoint.getArgs();
        try {
            IntStream.range(0, newArgs.length)
                    .filter(i -> newArgs[i] instanceof Authentication)
                    .forEach(i -> newArgs[i] = null); // na drzaka tam dam null, aby sa token nemazal tam, ale vymazem ho sam ako potrebujem
            Arrays.stream(newArgs).forEach(s -> System.out.println("class: " + s.getClass()));
            joinPoint.proceed(newArgs);
        } catch(Throwable t) {
            t.printStackTrace();
        }
        // tu urobim to co potrebujem miesto klasickeho logoutu - vymazem token na zaklade mena zo security contex holderu + zariadenia
    }

    @Around("execution(* com.example.securityDemo.Controller.RestControllers.trying.*(..))")
    public void mvcAdvice(ProceedingJoinPoint joinPoint) {
        System.out.println("before mvc ocntroller called");
        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("after execution of controller");
    }

    @Pointcut("execution(* org.springframework.security.web.authentication.rememberme.*.logout(..))")
    public void rememberMeLogout() {}

}
