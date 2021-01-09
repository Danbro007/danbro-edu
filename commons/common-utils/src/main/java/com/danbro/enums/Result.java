package com.danbro.enums;

import java.io.Serializable;
import java.util.List;
import com.danbro.exception.MyCustomException;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.validation.ObjectError;

/**
 * @Classname Result
 * @Description TODO 返回结果体
 * @Date 2020/12/15 10:21
 * @Author Danrbo
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Result<R> implements Serializable {
    @ApiModelProperty("状态码")
    private Integer code;
    @ApiModelProperty("响应消息")
    private String message;
    @ApiModelProperty("响应的数据")
    private R data;
    @ApiModelProperty("校验数据错误列表")
    private List<ObjectError> errors;
    @ApiModelProperty("请求是否成功")
    private Boolean isSuccess = false;

    private Result(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }


    private Result(ResultCode resultCode, R data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }


    private Result(MyCustomException customException) {
        this.code = customException.getCode();
        this.message = customException.getMessage();
        this.errors = customException.getErrors();
    }

    /**
     * 只返回 true ，不附带数据。
     *
     * @return 成功的结果
     */
    public static <R> Result<R> ofSuccess() {
        Result<R> result = new Result<>(ResultCode.SUCCESS);
        result.setIsSuccess(true);
        return result;
    }

    /**
     * 只返回 false ，不附带数据。
     *
     * @return 失败的结果
     */
    public static <R> Result<R> ofFail(ResultCode resultCode) {
        return new Result<>(resultCode);
    }

    /**
     * 返回 true 和数据。
     *
     * @return 成功的结果
     */
    public static <R> Result<R> ofSuccess(R data) {
        Result<R> result = new Result<>(ResultCode.SUCCESS, data);
        result.setIsSuccess(true);
        return result;
    }

    public static <R> Result<R> ofFail(ResultCode resultCode, R data) {
        return new Result<>(resultCode, data);
    }

    public static <R> Result<R> ofFail(MyCustomException exception) {
        return new Result<>(exception);
    }

    public static <R> Result<R> ofFail(MyCustomException exception, List<ObjectError> errors) {
        Result<R> result = new Result<>(exception);
        result.setErrors(errors);
        return result;
    }
}
