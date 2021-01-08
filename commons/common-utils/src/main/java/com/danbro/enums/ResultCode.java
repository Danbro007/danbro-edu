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

    /**
     * 讲师管理代码
     */
    TEACHER_NOT_FOUND(200100, "讲师不存在！"),
    DELETE_TEACHER_NOT_FOUND(200101, "要删除的讲师不存在！"),
    MATCH_CONDITION_TEACHER_NOT_FOUND(200102, "符合筛选条件的讲师不存在！"),
    UPDATE_TEACHER_FAILURE(200103, "修改讲师失败，可能讲师不存在！"),
    INSERT_TEACHER_FAILURE(200104, "添加讲师失败，讲师已存在！"),
    GET_TOP_TEACHER_LIST(200105, "获取热门讲师失败！"),
    /**
     * 课程管理代码
     */
    INSERT_COURSE_FAILURE(200200, "添加课程失败！"),
    INSERT_COURSE_DESCRIPTION_FAILURE(200201, "添加课程描述失败！"),
    UPDATE_COURSE_INFO_FAILURE(200202, "修改课程基本信息失败"),
    UPDATE_COURSE_PUBLISH_STATUS_FAILURE(200203, "修改课程发布状态失败"),
    DELETE_COURSE_FAILURE(200204, "删除课程失败"),
    GET_TOP_COURSE_LIST(200105, "获取热门课程失败！"),
    /**
     * 章节代码
     */
    DELETE_CHAPTER_FAILURE(200210, "删除章节失败"),
    UPDATE_CHAPTER_FAILURE(200211, "修改章节失败"),
    /**
     * 视频代码
     */
    INSERT_VIDEO_FAILURE(200220, "添加视频信息失败"),
    UPDATE_VIDEO_FAILURE(200221, "修改视频信息失败"),
    UPDATE_VIDEO_VIDEO_SOURCE_ID_IS_EMPTY(200222, "删除视频失败，小节里的视频已经为空！"),
    /**
     * banner代码
     */
    INSERT_BANNER_FAILURE(200300, "添加 Banner 失败"),
    DELETE_BANNER_FAILURE(200301, "删除 Banner 失败"),
    UPDATE_BANNER_FAILURE(200302, "修改 Banner 失败"),

    /**
     * 上传文件代码
     */
    AVATAR_UPLOAD_FAILURE(300000, "头像上传失败！"),
    OSS_UPLOAD_EXCEPTION(300001, "上传到阿里云OSS出现异常！"),
    UPLOAD_FILE_OVER_SIZE(300002, "上传的文件超过 5 MB！"),
    SUBJECT_UPLOAD_FAILURE(300003, "上传课程失败！"),
    VIDEO_UPLOAD_FAILURE(300020, "视频上传失败！"),
    DELETE_VIDEO_FAILURE(300021, "客户端出现异常，视频删除失败！"),
    DELETE_VIDEO_TIME_OUT(300022, "删除视频超时！"),
    UPLOAD_VIDEO_IS_EMPTY(300023, "上传的视频为空！"),
    CLIENT_ALIYUN_CONNECTION_ERROR(300030,"与阿里云视频点播平台连接失败！"),
    /**
     * 短信代码
     */
    SEND_MESSAGE_FAILURE(300030, "请求发送验证短信失败！"),

    /**
     * 用户登录代码
     */
    USER_NOT_EXIST(400000, "用户不存在！"),
    PASSWORD_NOT_CORRECT(400001, "密码错误，请重新输入！"),
    USER_IS_DISABLED(400002, "用户目前处于禁止登录状态！"),
    USER_NO_LOGIN(400003,"请先登录!"),
    /**
     * 普通用户注册代码
     */
    CAPTCHA_NOT_CORRECT(400020, "手机验证码错误，请重新输入！"),
    MOBILE_IS_EXIST(400021, "此手机号已注册，请重新输入！"),
    RESISTER_FAILURE(400022, "注册失败！"),
    /**
     * 微信用户代码
     */
    WECHAT_REGISTER_FAILURE(400040,"微信用户注册失败！"),

    /**
     * 前台用户评论代码S
     */
    INSERT_COMMENT_FAILURE(500000,"评论失败！")
    ;

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