package com.danbro.enums;

/**
 * @Classname com.danbro.enums.ResultCode
 * @Description TODO 响应状态码枚举类
 * @Date 2020/12/15 10:14
 * @Author Danrbo
 */

public enum ResultCode {
    /**
     * 状态码
     */
    SUCCESS(20000, "成功"),
    FAILURE(20001, "失败"),

    /**
     * 讲师管理代码
     */
    TEACHER_NOT_FOUND(20002, "讲师不存在！"),
    DELETE_TEACHER_NOT_FOUND(20003, "要删除的讲师不存在！"),
    MATCH_CONDITION_TEACHER_NOT_FOUND(20004, "符合筛选条件的讲师不存在！"),
    UPDATE_TEACHER_FAILURE(20005, "修改讲师失败，可能讲师不存在！"),
    INSERT_TEACHER_FAILURE(20006, "添加讲师失败，可能因为讲师已存在！"),

    /**
     * 上传文件代码
     */
    AVATAR_UPLOAD_FAILURE(30001,"头像上传失败！"),
    OSS_UPLOAD_EXCEPTION(30001,"上传到阿里云OSS出现异常！"),
    UPLOAD_FILE_OVER_SIZE(30002,"上传的文件超过 5 MB！");

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
