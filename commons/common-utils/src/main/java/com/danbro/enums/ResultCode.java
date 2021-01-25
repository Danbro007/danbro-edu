package com.danbro.enums;

/**
 * @Classname com.danbro.enums.ResultCode
 * @Description TODO 响应状态码
 * @Date 2020/12/15 10:14
 * @Author Danrbo
 */

public enum ResultCode {
    /**
     * 状态码
     */
    SUCCESS(20000, "成功"),
    FAILURE(20001, "失败"),
    PARAMS_ERROR(20002, "参数校验失败！"),
    NO_PERMISSION(20003, "你没有权限！"),
    UNKNOWN_EXCEPTION(20004, "未知错误！"),
    RPC_SERVICE_TIME_OUT(20005, "远程服务调用失败！"),
    DB_SERVER_ERROR(20006, "数据库出现错误！"),
    WECHAT_PAY_SERVICE_FAILURE(20007, "调用微信支付服务失败！"),
    FILE_IO_EXCEPTION(20008, "文件IO异常！"),
    /**
     * chapter增删改的错误代码 21001~21010
     */
    CHAPTER_INSERT_OR_UPDATE_FAILURE(21001, "添加或修改章节失败！"),
    CHAPTER_DELETE_FAILURE(21002, "删除章节失败！"),
    /**
     * comment增删改的错误代码 21011~21020
     */
    COMMENT_INSERT_OR_UPDATE_FAILURE(21011, "添加或者修改评论失败！"),
    /**
     * course 增删改的错误代码 21021~21030
     */
    COURSE_INSERT_OR_UPDATE_FAILURE(21021, "添加或者修改课程失败！"),
    COURSE_DELETE_FAILURE(21022, "删除课程失败！"),
    COURSE_SERVICE_TIME_OUT(21023, "课程服务超时！"),
    /**
     * teacher 增删改的错误代码 21031~21040
     */
    TEACHER_INSERT_OR_UPDATE_FAILURE(21031, "添加或者修改讲师失败！"),
    TEACHER_DELETE_FAILURE(21032, "删除讲师失败！"),
    TEACHER_SERVICE_TIME_OUT(21033, "讲师服务超时！"),
    /**
     * video 增删改的错误代码 21041~21050
     */
    VIDEO_INSERT_OR_UPDATE_FAILURE(21041, "添加或者修改小节失败！"),
    VIDEO_DELETE_FAILURE(21042, "删除小节失败！"),
    /**
     * permission 增删改的错误代码 22001~22010
     */
    PERMISSION_INSERT_OR_UPDATE_FAILURE(22001, "添加或修改权限失败！"),
    PERMISSION_DELETE_FAILURE(22002, "删除权限失败！"),
    /**
     * role 增删改的错误代码 22011~22020
     */
    ROLE_INSERT_OR_UPDATE_FAILURE(22011, "添加或修改角色失败！"),
    ROLE_DELETE_FAILURE(22012, "删除角色失败！"),
    /**
     * 后台 user 增删改的错误代码 22021~22030
     */
    USER_INSERT_OR_UPDATE_FAILURE(22021, "添加或修改用户失败！"),
    USER_DELETE_FAILURE(22022, "删除用户失败！"),
    USER_ROLE_INSERT_FAILURE(22023, "添加用户角色失败！"),
    USER_NOT_EXIST(22034, "用户不存在！"),
    USER_PASSWORD_NOT_CORRECT(22035, "密码错误，请重新输入！"),
    USER_IS_DISABLED(22036, "用户目前处于禁止登录状态！"),
    USER_NO_LOGIN(22037, "请先登录!"),
    USER_SERVICE_TIME_OUT(22038, "用户服务超时！"),
    /**
     * banner 增删改的错误代码 22031~22040
     */
    BANNER_INSERT_OR_UPDATE_FAILURE(22031, "添加或者修改 Banner 失败"),
    BANNER_DELETE_FAILURE(21032, "删除 Banner 失败"),
    /**
     * order 增删爱的错误代码 22041~22050
     */
    ORDER_INSERT_FAILURE(22041, "添加订单失败！"),
    ORDER_DELETE_FAILURE(22042, "删除订单失败！"),
    ORDER_UPDATE_FAILURE(22043, "更新订单失败！"),
    ORDER_NOT_FOUND(22044, "订单不存在！"),
    ORDER_UPDATE_PAY_STATUS_FAILURE(22045, "更新订单支付状态失败！"),
    ORDER_SERVICE_TIME_OUT(22038, "订单服务超时！"),

    /**
     * oss 错误代码 22061~22062
     */
    OSS_CLIENT_CONNECTION_ERROR(22051, "与阿里云OSS服务出现连接错误！"),
    OSS_UPLOAD_FILE_OVER_SIZE(22052, "超过规定的上传文件大小！"),
    OSS_UPLOAD_FILE_FAILURE(22053, "上传文件失败！"),
    /**
     * StatisticsDaily 增删改的错误代码  22061~22070
     */
    STATISTICS_INSERT_FAILURE(22061, "创建统计信息失败！"),
    STATISTICS_SERVICE_TIME_OUT(22062, "统计服务超时！"),
    /**
     * vod 错误代码 22071~22080
     */
    VOD_FILENAME_IS_EMPTY(22071, "上传的视频名为空！"),
    VOD_UPLOAD_VIDEO_FAILURE(22072, "上传视频失败！"),
    VOD_CLIENT_CONNECTION_ERROR(22073, "与阿里云VOD服务出现连接错误！"),
    VOD_SERVICE_TIME_OUT(22074, "VOD服务超时！"),
    /**
     * subject 错误代码 22081~22090
     */
    SUBJECT_UPLOAD_FAILURE(22081, "上传课程分类表格失败！"),
    /**
     * 会员相关的错误代码 23001~23010
     */
    MEMBER_REGISTER_CAPTCHA_ERROR(23001, "验证码错误，请重新输入！"),
    MEMBER_MOBILE_IS_EXIST(23002, "此手机号已注册，请重新输入！"),
    MEMBER_REGISTER_FAILURE(23003, "会员注册失败！"),
    WECHAT_REGISTER_FAILURE(23004, "微信用户注册失败！"),
    /**
     * 短信代码
     */
    SEND_MESSAGE_FAILURE(30030, "请求发送验证短信失败！");
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