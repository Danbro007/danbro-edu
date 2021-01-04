package com.danbro.enums;

import com.danbro.exception.MyCustomException;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @Classname Result
 * @Description TODO 返回结果体
 * @Date 2020/12/15 10:21
 * @Author Danrbo
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class Result implements Serializable {
    @ApiModelProperty("状态码")
    private Integer code;
    @ApiModelProperty("响应消息")
    private String message;
    @ApiModelProperty("响应的数据")
    private HashMap<String, Object> data;
    @ApiModelProperty("请求是否成功")
    private Boolean success;

    private Result(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public Result setDataChain(String key, Object value) {
        if (this.data == null) {
            this.data = new HashMap<>(16);
        }
        this.data.put(key, value);
        return this;
    }

    private Result(ResultCode resultCode, HashMap<String, Object> data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }


    private Result(MyCustomException customException) {
        this.code = customException.getCode();
        this.message = customException.getMessage();
    }

    /**
     * 只返回 true ，不附带数据。
     *
     * @return 成功的结果
     */
    public static Result successOf(ResultCode resultCode) {
        Result result = new Result(resultCode);
        result.setSuccess(true);
        return result;
    }

    /**
     * 只返回 false ，不附带数据。
     *
     * @return 失败的结果
     */
    public static Result failureOf(ResultCode resultCode) {
        Result result = new Result(resultCode);
        result.setSuccess(false);
        return result;
    }

    /**
     * 返回 true 和数据。
     *
     * @return 成功的结果
     */
    public static Result successOf(ResultCode resultCode, HashMap<String, Object> data) {
        Result result = new Result(resultCode, data);
        result.setSuccess(true);
        return result;
    }

    public static Result successOf(ResultCode resultCode, String key, Object value) {
        Result result = new Result(resultCode).setDataChain(key, value);
        result.setSuccess(true);
        return result;
    }

    public static Result failureOf(ResultCode resultCode, String key, Object value) {
        Result result = new Result(resultCode).setDataChain(key, value);
        result.setSuccess(false);
        return result;
    }

    public static Result failureOf(MyCustomException exception) {
        Result result = new Result(exception);
        result.setSuccess(false);
        return result;
    }
}
