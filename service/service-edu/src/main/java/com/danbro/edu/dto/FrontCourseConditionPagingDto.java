package com.danbro.edu.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Classname FrontCourseConditionPagingDto
 * @Description TODO 前台用户带有条件的分页查询课程
 * @Date 2021/1/7 12:14
 * @Author Danrbo
 */
@Data
public class FrontCourseConditionPagingDto implements Serializable {
    @ApiModelProperty(value = "课程名称")
    private String title;

    @ApiModelProperty(value = "讲师id")
    private String teacherId;

    @ApiModelProperty(value = "一级类别id")
    private String subjectParentId;

    @ApiModelProperty(value = "二级类别id")
    private String subjectId;

    @ApiModelProperty(value = "根据什么字段排序排序")
    private String sortType;
}
