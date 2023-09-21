package com.cytao.aop;

import com.cytao.entity.Log;
import com.cytao.service.LogInter;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Spliterators;

@Aspect
@Component
public class LogAspect {
    @Autowired
    private LogInter logSer;

    @Pointcut("execution(* com.cytao.service.*.up*(..))")
    public void updateCell() {}

    @Pointcut("execution(* com.cytao.service.*.del*(..))")
    public void deleteCell() {}

    @AfterReturning(value = "updateCell()", argNames = "joinPoint,object", returning = "object")
    public void updateLog(JoinPoint joinPoint, Object object)
            throws Throwable {
        // 判断参数
        if (joinPoint.getArgs() == null) {// 没有参数
            return;
        }
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();

        // 获取参数
        Object[] arrays=joinPoint.getArgs();
        StringBuffer rs=new StringBuffer();
        String paramClass;
        for (Object object2 : arrays) {
            //取到参数的类型
            paramClass=object2.getClass().getName();
            paramClass=paramClass.substring(paramClass.lastIndexOf(".")+1);
            rs.append("[参数，类型]"+paramClass+",值:("
                    +joinPoint.getArgs()[0]+")");
        }

        //从session里获取用户信息
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        String username =(String) session.getAttribute("loginUsername");

        Log log = new Log();
        log.setContent(rs.toString());
        log.setAdmin(username);
        log.setOperation(methodName);

        log.setCreatedate(new Date());
        logSer.serviceLog(log);
    }

    @AfterReturning(value = "deleteCell()", argNames = "joinPoint,object", returning = "object")
    public void deleteLog(JoinPoint joinPoint, Object object) throws Throwable {
        // 判断参数
        if (joinPoint.getArgs() == null) {// 没有参数
            return;
        }
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();
        // 获取参数
        Object[] arrays=joinPoint.getArgs();
        StringBuffer rs=new StringBuffer();
        String paramClass;
        for (Object object2 : arrays) {
            //取到参数的类型
            paramClass=object2.getClass().getName();
            paramClass=paramClass.substring(paramClass.lastIndexOf(".")+1);
            rs.append("[参数，类型]"+paramClass+",值:("
                    +joinPoint.getArgs()[0]+")");

        }

        //获取用户信息
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession(true);
        String username =(String) session.getAttribute("loginUsername");

        Log log = new Log();
        log.setContent(rs.toString());
        log.setAdmin(username);
        log.setOperation(methodName);
        log.setCreatedate(new Date());
        logSer.serviceLog(log);
    }

}
