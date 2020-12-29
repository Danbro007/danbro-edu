package com.danbro.edu.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 课程(EduChapter)实体类
 *
 * @author makejava
 * @since 2020-12-20 19:55:16
 */
@Data
public class EduChapter implements Serializable {
    private static final long serialVersionUID = -60130242263683274L;
    /**
     * 章节ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 课程ID
     */
    private String courseId;
    /**
     * 章节名称
     */
    private String title;
    /**
     * 显示排序
     */
    private Integer sort;
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