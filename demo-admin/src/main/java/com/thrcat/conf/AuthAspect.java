package com.thrcat.conf;

import com.thrcat.annotion.AdminCheck;
import com.thrcat.controller.BaseController;
import com.thrcat.exceptions.NoPermissionException;
import com.thrcat.logs.ErrorLog;
import com.thrcat.logs.InfoLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * desc:
 *
 * @author scx
 * @create 2020/05/11
 */
@Aspect
@Component
public class AuthAspect extends BaseController {

    @Pointcut(value = "execution(* com.thrcat.controller..*(..))")
    private void auth() {

    }

    @Around("auth()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        AdminCheck adminCheck = getAdminCheck(joinPoint);
        if (adminCheck != null) {
            //TODO 这里可以通过adminCheck.roleType() 进行权限校验
            InfoLog.info(adminCheck.roleType().name() + " check on :" + joinPoint.toString());
            throw new NoPermissionException("无权限访问" + adminCheck.roleType().name() + "接口");
        }
        Long start = System.currentTimeMillis();
        Object res;
        res = joinPoint.proceed();
        Long end = System.currentTimeMillis();
        if (start - end >= 5 * 1000L) {
            ErrorLog.warn("方法名:" + joinPoint.getSignature().getName() + "耗时:" + (end - start) + "ms");
        }
        return res;
    }

    /**
     * 根据切面获得方法是否有管理员权限限制
     *
     * @param jp 切面
     * @return 方法
     */
    private AdminCheck getAdminCheck(JoinPoint jp) {
        Annotation annotation = jp.getSignature().getDeclaringType().getAnnotation(AdminCheck.class);
        if (annotation != null) {
            return (AdminCheck) annotation;
        }
        Method proxyMethod = ((MethodSignature) jp.getSignature()).getMethod();
        if (!proxyMethod.isAnnotationPresent(AdminCheck.class)) {
            return null;
        }
        return proxyMethod.getAnnotation(AdminCheck.class);

    }
}
