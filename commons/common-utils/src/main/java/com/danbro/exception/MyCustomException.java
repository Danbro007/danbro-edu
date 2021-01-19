package com.danbro.exception;

import java.util.List;

import com.danbro.enums.ResultCode;
import lombok.Data;
import org.springframework.validation.ObjectError;

/**
 * @Classname MyCustomException
 * @Description TODO 自定义异常
 * @Date 2020/12/15 15:22
 * @Author Danrbo
 */
@Data
public class MyCustomException extends RuntimeException {

    private String message;
    private Integer code;
    private List<String> errors;

    public MyCustomException(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public MyCustomException(ResultCode resultCode, List<String> errors) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.errors = errors;
    }
}
