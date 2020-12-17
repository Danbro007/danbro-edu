package com.danbro.edu.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 课程科目(EduSubject)实体类
 *
 * @author makejava
 * @since 2020-12-17 16:59:16
 */
@Data
@Accessors(chain = true)
public class EduSubject implements Serializable {
    private static final long serialVersionUID = 416858106567768115L;
    /**
     * 课程类别ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 类别名称
     */
    @ApiModelProperty("课程名")
    private String title;
    /**
     * 父ID
     */
    @ApiModelProperty("一级课程名")
    private String parentId;
    /**
     * 排序字段
     */
    @ApiModelProperty("排序")
    private Integer sort;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    /**
     * 更新时间
     */
    @ApiModelProperty("修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

}