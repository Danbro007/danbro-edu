package com.danbro.aop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.danbro.enums.ResultCode;
import com.danbro.exception.MyCustomException;
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
     * 环绕AOP
     * @param joinPoint 切点
     * @return
     * @throws Throwable
     */
    @Around("validAop()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        List<String> errorList = new ArrayList<>();
        if (args != null) {
            Arrays.stream(args).forEach(arg -> {
                if (arg instanceof BindingResult) {
                    BindingResult bindingResult = (BindingResult) arg;
                    if (bindingResult.hasErrors()) {
                        bindingResult.getAllErrors().forEach(error -> errorList.add(error.getDefaultMessage()));
                        throw new MyCustomException(ResultCode.PARAMS_ERROR, errorList);
                    }
                }
            });
        }
        return joinPoint.proceed();
    }

}
