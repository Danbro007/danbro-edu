package com.danbro.handler;

import com.danbro.enums.Result;
import com.danbro.exception.MyCustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Classname MyExceptionHandler
 * @Description TODO 统一的异常处理
 * @Date 2020/12/15 15:17
 * @Author Danrbo
 */
@Slf4j
@ControllerAdvice
public class MyExceptionHandler {


    @ResponseBody
    @ExceptionHandler(MyCustomException.class)
    public Result myCustomException(MyCustomException e) {
        log.error(e.getMessage());
        return Result.ofFail(e);
    }

}
