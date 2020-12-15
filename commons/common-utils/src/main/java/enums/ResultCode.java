package enums;

import lombok.Data;

/**
 * @Classname enums.ResultCode
 * @Description TODO 响应状态码枚举类
 * @Date 2020/12/15 10:14
 * @Author Danrbo
 */

public enum ResultCode {
    /**
     * 状态码
     */
    Success(2000, "成功"),
    Failure(20001, "失败");

    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
