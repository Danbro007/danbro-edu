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
    PARAMS_ERROR(20002, "参数不正确！"),
    NO_PERMISSION(20003,"你没有权限！"),

    /**
     * 讲师管理代码
     */
    TEACHER_NOT_FOUND(20100, "讲师不存在！"),
    DELETE_TEACHER_NOT_FOUND(20101, "要删除的讲师不存在！"),
    MATCH_CONDITION_TEACHER_NOT_FOUND(20102, "符合筛选条件的讲师不存在！"),
    UPDATE_TEACHER_FAILURE(20103, "修改讲师失败，可能讲师不存在！"),
    INSERT_TEACHER_FAILURE(20104, "添加讲师失败，讲师已存在！"),
    GET_TOP_TEACHER_LIST(20105, "获取热门讲师失败！"),
    TEACHER_SERVICE_TIME_OUT(20106, "讲师服务调用超时！"),

    /**
     * 课程管理代码
     */
    INSERT_COURSE_FAILURE(20200, "添加课程失败！"),
    INSERT_COURSE_DESCRIPTION_FAILURE(20201, "添加课程描述失败！"),
    UPDATE_COURSE_INFO_FAILURE(20202, "修改课程基本信息失败"),
    UPDATE_COURSE_PUBLISH_STATUS_FAILURE(20203, "修改课程发布状态失败"),
    DELETE_COURSE_FAILURE(20204, "删除课程失败"),
    GET_TOP_COURSE_LIST(20105, "获取热门课程失败！"),
    COURSE_IS_NOT_EXIST(20106, "查找的课程不存在！"),
    COURSE_SERVICE_TIME_OUT(20107, "课程服务调用超时！"),
    /**
     * 章节代码
     */
    DELETE_CHAPTER_FAILURE(20210, "删除章节失败"),
    UPDATE_CHAPTER_FAILURE(20211, "修改章节失败"),
    /**
     * 视频代码
     */
    INSERT_VIDEO_FAILURE(20220, "添加视频信息失败"),
    UPDATE_VIDEO_FAILURE(20221, "修改视频信息失败"),
    UPDATE_VIDEO_VIDEO_SOURCE_ID_IS_EMPTY(20222, "删除视频失败，小节里的视频已经为空！"),
    /**
     * banner代码
     */
    INSERT_BANNER_FAILURE(20300, "添加 Banner 失败"),
    DELETE_BANNER_FAILURE(20301, "删除 Banner 失败"),
    UPDATE_BANNER_FAILURE(20302, "修改 Banner 失败"),

    /**
     * 上传文件代码
     */
    AVATAR_UPLOAD_FAILURE(30000, "头像上传失败！"),
    OSS_UPLOAD_EXCEPTION(30001, "上传到阿里云OSS出现异常！"),
    UPLOAD_FILE_OVER_SIZE(30002, "上传的文件超过 5 MB！"),
    SUBJECT_UPLOAD_FAILURE(30003, "上传课程失败！"),
    VIDEO_UPLOAD_FAILURE(30020, "视频上传失败！"),
    DELETE_VIDEO_FAILURE(30021, "客户端出现异常，视频删除失败！"),
    DELETE_VIDEO_TIME_OUT(30022, "删除视频超时！"),
    UPLOAD_VIDEO_IS_EMPTY(30023, "上传的视频为空！"),
    CLIENT_ALIYUN_CONNECTION_ERROR(30030, "与阿里云视频点播平台连接失败！"),
    VOD_SERVICE_TIME_OUT(30031, "视频点播服务调用超时！"),
    /**
     * 短信代码
     */
    SEND_MESSAGE_FAILURE(30030, "请求发送验证短信失败！"),

    /**
     * 用户登录代码
     */
    USER_NOT_EXIST(40000, "用户不存在！"),
    PASSWORD_NOT_CORRECT(40001, "密码错误，请重新输入！"),
    USER_IS_DISABLED(40002, "用户目前处于禁止登录状态！"),
    USER_NO_LOGIN(40003, "请先登录!"),
    USER_SERVICE_TIME_OUT(40004, "用户服务调用超时！"),
    /**
     * 普通用户注册代码
     */
    CAPTCHA_NOT_CORRECT(40020, "手机验证码错误，请重新输入！"),
    MOBILE_IS_EXIST(40021, "此手机号已注册，请重新输入！"),
    RESISTER_FAILURE(40022, "注册失败！"),
    /**
     * 微信用户代码
     */
    WECHAT_REGISTER_FAILURE(40040, "微信用户注册失败！"),

    /**
     * 前台用户评论代码
     */
    INSERT_COMMENT_FAILURE(50000, "评论失败！"),

    /**
     * 订单服务相关代码
     */
    INSERT_ORDER_FAILURE(50500, "创建订单失败！");

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