package com.danbro.edu.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.io.Serializable;

/**
 * 课程简介(EduCourseDescription)实体类
 *
 * @author makejava
 * @since 2020-12-20 19:55:16
 */
@Accessors(chain = true)
@Data
public class EduCourseDescription implements Serializable {
    private static final long serialVersionUID = 141020434366485852L;
    /**
     * 课程ID
     */
    @TableId(type = IdType.INPUT)
    private String id;
    /**
     * 课程简介
     */
    private String description;
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