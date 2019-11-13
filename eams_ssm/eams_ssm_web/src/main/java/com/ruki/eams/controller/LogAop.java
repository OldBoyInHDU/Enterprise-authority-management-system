package com.ruki.eams.controller;

import com.ruki.eams.domain.SysLog;
import com.ruki.eams.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService sysLogService;

    private Date visitTime;//访问时间
    private Class clazz;//访问的类
    private Method method;//访问的方法

    //前置通知，目的：获取开始时间，执行的类是哪一个类，执行的是哪一个方法
    @Before("execution(* com.ruki.eams.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();//当前时间就是开始访问的时间
        clazz = jp.getTarget().getClass();//具体要访问的类
        String methodName = jp.getSignature().getName();//获取访问的方法的名称
        Object[] args = jp.getArgs();//获取访问的方法的参数

        //获取具体执行的方法的Method对象
        if(args == null || args.length == 0){
            method = clazz.getMethod(methodName);//只能获取无参的方法
        } else {
            Class[] classArgs = new Class[args.length];//新建了一个class数组，用来存放参数的类
            for(int i = 0; i < classArgs.length; i++){
                classArgs[i] = args[i].getClass();
            }

            clazz.getMethod(methodName, classArgs);
        }
    }

    //后置通知
    @After("execution(* com.ruki.eams.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        //获取访问的时长
        long time = new Date().getTime() - visitTime.getTime();

        //获取URL
        String url = "";
        if(clazz != null && method != null && clazz != LogAop.class){
            //1. 获取类上的@RequestMapping("/orders")
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if(classAnnotation != null){
                String[] classValues = classAnnotation.value();

                //2. 获取方法上的 @RequestMapping("/findAll.do")
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if(methodAnnotation != null){
                    String[] methodValues = methodAnnotation.value();
                    url = classValues[0] + methodValues[0];

                    //获取IP
                    //需要在web.xml中配置监听器 org.springframework.web.context.request.RequestContextListener
                    String ip = request.getRemoteAddr();

                    //获取当前操作用户
                    SecurityContext context = SecurityContextHolder.getContext();//从上下文中获得当前登录的用户
                    User user = (User) context.getAuthentication().getPrincipal();
                    String username = user.getUsername();

                    //将日志相关信息封装到SysLog对象中
                    SysLog sysLog = new SysLog();
                    sysLog.setExecutionTime(time);
                    sysLog.setIp(ip);
                    sysLog.setMethod("[类名]" + clazz.getName() + "[方法名]" + method.getName());
                    sysLog.setUrl(url);
                    sysLog.setUsername(username);
                    sysLog.setVisitTime(visitTime);

                    sysLogService.save(sysLog);
                }
            }
        }


    }
}
