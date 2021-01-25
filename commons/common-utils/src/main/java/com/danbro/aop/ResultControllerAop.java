package com.danbro.aop;

import com.danbro.enums.ErrorResult;
import com.danbro.enums.Result;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author Danrbo
 * @Classname ResultControllerAop
 * @Description TODO Result 的 AOP
 * @Date 2021/1/25 11:23
 */
@Aspect
@Component
public class ResultControllerAop extends ControllerAop {
    @Override
    @Pointcut("execution(public com.danbro.enums.Result *(..))")
    protected void targetMethod() {
    }

    @Override
    protected ErrorResult createResult() {
        return new Result<>();
    }
}
