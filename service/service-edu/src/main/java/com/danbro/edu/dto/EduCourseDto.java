package com.danbro.edu.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;

/**
 * @Classname CourseInputDto
 * @Description TODO 课程添加的提交参数
 * @Date 2020/12/20 19:48
 * @Author Danrbo
 */
@Data
@ApiModel("课程提交参数")
public class EduCourseDto {
    @NotBlank(message = "讲师ID不能为空！")
    @ApiModelProperty("课程讲师ID")
    private String teacherId;

    @NotBlank(message = "课程ID不能为空！")
    @ApiModelProperty("课程专业ID")
    private String subjectId;

    @NotBlank(message = "课程标题不能为空！")
    @Size(max = 20,message = "课程标题不能超过 20 个字！")
    @ApiModelProperty("课程标题")
    private String title;

    @NotNull(message = "课程价格不能为空！")
    @Min(value = 0,message = "课程价格不能小于 0 元！")
    @ApiModelProperty("课程价格，设置0表示免费。")
    private BigDecimal price;

    @NotNull(message = "课程总课时不能为空！")
    @Min(value = 1,message = "课程总课时要大于 0 ！")
    @ApiModelProperty("课程总课时")
    private Integer lessonNum;

    @NotBlank(message = "请上传课程封面！")
    @ApiModelProperty("课程封面图片路径")
    private String cover;

    @NotBlank(message = "课程简介不能为空！")
    @ApiModelProperty("课程简介")
    private String description;

    @NotBlank(message = "请选择父类分类！")
    @ApiModelProperty("父类分类ID")
    private String subjectParentId;
}
