package com.danbro.exception;

import com.danbro.enums.ResultCode;
import lombok.Data;

/**
 * @Classname MyCustomException
 * @Description TODO
 * @Date 2020/12/15 15:22
 * @Author Danrbo
 */
@Data
public class MyCustomException extends RuntimeException {

    private String message;
    private Integer code;

    public MyCustomException(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }
}
