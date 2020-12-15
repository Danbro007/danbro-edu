package enums;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;

/**
 * @Classname Result
 * @Description TODO 返回结果体
 * @Date 2020/12/15 10:21
 * @Author Danrbo
 */
@Data
public class Result {
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

    private Result(ResultCode resultCode, HashMap<String, Object> data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }

    private Result(ResultCode resultCode, String key, Object value) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        HashMap<String, Object> data = new HashMap<>();
        data.put(key, value);
        this.data = data;
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

    /**
     * 返回 false 和数据。
     *
     * @return 失败的结果
     */
    public static Result failureOf(ResultCode resultCode, HashMap<String, Object> data) {
        Result result = new Result(resultCode, data);
        result.setSuccess(false);
        return result;
    }

    public static Result successOf(ResultCode resultCode, String key,Object data) {
        Result result = new Result(resultCode, key,data);
        result.setSuccess(true);
        return result;
    }

    public static Result failureOf(ResultCode resultCode, String key,Object data) {
        Result result = new Result(resultCode, key,data);
        result.setSuccess(false);
        return result;
    }
}
