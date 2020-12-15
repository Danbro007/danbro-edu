package com.danbro.handler;

import com.danbro.enums.Result;
import com.danbro.exception.MyCustomException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Classname MyExceptionHandler
 * @Description TODO 统一的异常处理
 * @Date 2020/12/15 15:17
 * @Author Danrbo
 */
@ControllerAdvice
public class MyExceptionHandler {
    @ResponseBody
    @ExceptionHandler(MyCustomException.class)
    public Result error(MyCustomException e) {
        return Result.failureOf(e);
    }
}
