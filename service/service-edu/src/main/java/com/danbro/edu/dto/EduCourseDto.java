package com.danbro.edu.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Classname CourseInputDto
 * @Description TODO
 * @Date 2020/12/20 19:48
 * @Author Danrbo
 */
@Data
@ApiModel("课程提交参数")
public class EduCourseDto {

    @ApiModelProperty("课程ID")
    private String id;

    @ApiModelProperty("课程讲师ID")
    private String teacherId;

    @ApiModelProperty("课程专业ID")
    private String subjectId;

    @ApiModelProperty("课程标题")
    private String title;

    @ApiModelProperty("课程价格，设置0表示免费。")
    private BigDecimal price;

    @ApiModelProperty("课程总课时")
    private Integer lessonNum;

    @ApiModelProperty("课程封面图片路径")
    private String cover;

    @ApiModelProperty("课程简介")
    private String description;

    @ApiModelProperty("父类分类ID")
    private String subjectParentId;
}
