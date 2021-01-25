package com.danbro.exceptions;

import java.util.List;

import com.danbro.enums.ResultCode;
import lombok.Data;

/**
 * @Classname MyCustomException
 * @Description TODO 普通异常
 * @Date 2020/12/15 15:22
 * @Author Danrbo
 */
@Data
public class EduException extends RuntimeException {

    private static final long serialVersionUID = 8901488341753242348L;
    private String message;
    private Integer code;
    private List<String> errors;

    public EduException(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public EduException(ResultCode resultCode, List<String> errors) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.errors = errors;
    }
}
