package com.easysoft.member.backend.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 操作权限通知
 * User: andy
 * Date: 14-5-16
 * Time: 上午9:38
 *
 * @since:
 */
@Aspect
@Component
public class PermissionAdvice{
    @Before("execution(* com.easysoft.*.controller.*.list*(..))")
    public void getOperationAuths(JoinPoint joinPoint)  {
        System.out.println("operation auth"+joinPoint.getArgs());
    }
}
