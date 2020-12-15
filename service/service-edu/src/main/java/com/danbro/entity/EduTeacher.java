package com.danbro.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * 讲师(EduTeacher)实体类
 *
 * @author makejava
 * @since 2020-12-14 15:11:47
 */
@Data
public class EduTeacher implements Serializable {
    private static final long serialVersionUID = -47902043022957890L;
    /**
     * 讲师ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 讲师姓名
     */
    @Max(value = 5, message = "教师姓名不能超过 5 个字!")
    @NotEmpty(message = "姓名不能为空！")
    private String name;
    /**
     * 讲师简介
     */
    @NotEmpty(message = "讲师简介不能为空！")
    private String intro;
    /**
     * 讲师资历,一句话说明讲师
     */
    @Min(value = 5, message = "教师简介不能少于 5 个字！")
    @Max(value = 50, message = "教师简介不能超过 50 个字！")
    @NotEmpty(message = "讲师资历不能为空")
    private String career;
    /**
     * 头衔 1高级讲师 2首席讲师
     */
    @NotNull(message = "讲师头衔不能为空")
    private Integer level;
    /**
     * 讲师头像
     */
    @NotEmpty(message = "讲师头像必须上传")
    private String avatar;
    /**
     * 排序
     */
    @NotNull(message = "讲师排序不能为空")
    private Integer sort;
    /**
     * 逻辑删除 1（true）已删除， 0（false）未删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer isDeleted;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;

}