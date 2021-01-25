package com.danbro.aop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.danbro.enums.ResultCode;
import com.danbro.exceptions.EduException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

/**
 * @Classname ValidAop
 * @Description TODO 对 controller 的参数校验 AOP
 * @Date 2021/1/20 18:30
 * @Created by Administrator
 */
@Component
@Aspect
@Order(1)
public class ValidAop {

    /**
     * 对有 @ValidParam 注解的进行 AOP
     */
    @Pointcut("@annotation(com.danbro.anotation.ValidParam)")
    public void validAop() {

    }

    /**
     * 环绕AOP 找到 controller 的参数带有 BindingResult 的
     *
     * @param joinPoint 切点
     * @return
     * @throws Throwable 异常
     */
    @Around("validAop()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        if (args != null) {
            Arrays.stream(args).forEach(arg -> {
                if (arg instanceof BindingResult) {
                    BindingResult bindingResult = (BindingResult) arg;
                    if (bindingResult.hasErrors()) {
                        List<String> errorList = new ArrayList<>();
                        bindingResult.getAllErrors().forEach(error -> errorList.add(error.getDefaultMessage()));
                        throw new EduException(ResultCode.PARAMS_ERROR, errorList);
                    }
                }
            });
        }
        return joinPoint.proceed();
    }

}
