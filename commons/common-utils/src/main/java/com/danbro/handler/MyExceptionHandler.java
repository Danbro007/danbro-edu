package com.danbro.handler;

import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import com.danbro.exception.MyCustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Set;

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

    /**
     * 负责处理参数校验失败的异常。
     *
     * @param e 参数校验异常
     * @return
     */
    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    public Result paramValidException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ArrayList<String> errors = new ArrayList<>();
        violations.forEach(v -> {
            errors.add(v.getMessage());
        });
        return Result.ofFail(ResultCode.PARAMS_ERROR, errors);
    }


}
