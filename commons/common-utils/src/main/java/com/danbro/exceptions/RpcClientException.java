package com.danbro.exceptions;

import com.danbro.enums.ResultCode;
import lombok.Data;

/**
 * @author Danrbo
 * @Classname RpcClientException
 * @Description TODO 远程调用 RPC 服务的异常
 * @Date 2021/1/25 11:43
 */
@Data
public class RpcClientException extends RuntimeException {
    private static final long serialVersionUID = -412826653904994751L;

    private String message;
    private Integer code;

    public RpcClientException(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }
}
