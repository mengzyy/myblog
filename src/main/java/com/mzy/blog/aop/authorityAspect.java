package com.mzy.blog.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * @program: myblog
 * @author: mengzy 18306299232@163.com
 * @create: 2020-02-13 22:36
 **/


@Component
@Aspect
public class authorityAspect {


    //定义一个切入点
    @Pointcut("execution(public * com.mzy.blog.controller.siteInfoController.mylove())")
    public void myLovePermissionsAspect() {


    }

    //around切入方法
    @Around("myLovePermissionsAspect()")
    public String executeAround(ProceedingJoinPoint joinPoint) throws Throwable {
        //获得当前session
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        String username = (String) session.getAttribute("sessionUserName");
        if (username==null||!username.equals("孟中艺")) {
            return "403";
        } else {
            return (String) joinPoint.proceed();
        }


    }
}
