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
    TEACHER_NOT_FOUND(20100, "讲师不存在！"),
    DELETE_TEACHER_NOT_FOUND(20101, "要删除的讲师不存在！"),
    MATCH_CONDITION_TEACHER_NOT_FOUND(200102, "符合筛选条件的讲师不存在！"),
    UPDATE_TEACHER_FAILURE(20103, "修改讲师失败，可能讲师不存在！"),
    INSERT_TEACHER_FAILURE(20104, "添加讲师失败，讲师已存在！"),
    /**
     * 课程管理代码
     */
    INSERT_COURSE_FAILURE(200200, "添加课程失败！"),
    INSERT_COURSE_DESCRIPTION_FAILURE(200201, "添加课程描述失败！"),
    UPDATE_COURSE_INFO_FAILURE(200202,"修改课程基本信息失败"),
    DELETE_CHAPTER_FAILURE(200210,"删除章节失败"),
    UPDATE_CHAPTER_FAILURE(200211,"修改章节失败"),
    INSERT_VIDEO_FAILURE(200220,"添加视频信息失败"),
    UPDATE_VIDEO_FAILURE(200221,"修改视频信息失败"),
    DELETE_VIDEO_FAILURE(200221,"删除视频信息失败"),
    /**
     * 上传文件代码
     */
    AVATAR_UPLOAD_FAILURE(30001, "头像上传失败！"),
    OSS_UPLOAD_EXCEPTION(30001, "上传到阿里云OSS出现异常！"),
    UPLOAD_FILE_OVER_SIZE(30002, "上传的文件超过 5 MB！"),
    SUBJECT_UPLOAD_FAILURE(30003, "上传课程失败！");

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
