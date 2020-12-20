package com.danbro.edu.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 课程收藏(EduCourseCollect)实体类
 *
 * @author makejava
 * @since 2020-12-20 19:55:16
 */
@Data
public class EduCourseCollect implements Serializable {
    private static final long serialVersionUID = -75200560579636768L;
    /**
     * 收藏ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 课程讲师ID
     */
    private String courseId;
    /**
     * 课程专业ID
     */
    private String memberId;
    /**
     * 逻辑删除 1（true）已删除， 0（false）未删除
     */
    @TableLogic()
    @TableField(fill = FieldFill.INSERT)
    private Object isDeleted;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}