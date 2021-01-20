package com.danbro.edu.controller.param;

/**
 * @Classname UpdateCourseParam
 * @Description TODO
 * @Date 2021/1/19 19:25
 * @Created by Administrator
 */

import java.math.BigDecimal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import com.danbro.anotation.IsPositiveNum;
import com.danbro.anotation.IsPrice;
import com.danbro.edu.entity.EduCourse;
import com.danbro.impl.Insert;
import com.danbro.impl.ParamConvert;
import com.danbro.impl.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @Classname CourseInputDto
 * @Description TODO 后台用户添加课程的参数
 * @Date 2020/12/20 19:48
 * @Author Danrbo
 */
@Data
@ApiModel("更新课程的参数")
public class UpdateCourseParam implements ParamConvert<EduCourse> {

    @NotBlank(message = "课程ID不能为空！")
    @Min(value = 1, message = "课程ID不能小于0！")
    @ApiModelProperty("课程ID")
    private String id;

    @NotBlank(message = "讲师ID不能为空！")
    @Min(value = 1, message = "讲师ID不能小于0！")
    @ApiModelProperty("课程讲师ID")
    private String teacherId;

    @NotBlank(message = "课程ID不能为空！")
    @Min(value = 1, message = "课程分类ID不能小于0！")
    @ApiModelProperty("课程分类ID")
    private String subjectId;

    @NotBlank(message = "课程标题不能为空！")
    @Size(max = 20, message = "课程标题不能超过 20 个字！")
    @ApiModelProperty("课程标题")
    private String title;

    @IsPrice
    @ApiModelProperty("课程价格，设置0表示免费。")
    private BigDecimal price;

    @IsPositiveNum(message = "课程总课时必须大于0！", groups = {Insert.class, Update.class})
    @ApiModelProperty("课程总课时")
    private Integer lessonNum;

    @NotBlank(message = "请上传课程封面！")
    @ApiModelProperty("课程封面图片路径")
    private String cover;

    @NotBlank(message = "课程简介不能为空！")
    @ApiModelProperty("课程简介")
    private String description;

    @Min(value = 1, message = "父类分类ID不能小于等于0！")
    @NotBlank(message = "请选择父类分类！")
    @ApiModelProperty("父类分类ID")
    private String subjectParentId;

    @Override
    public EduCourse convertTo() {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(this, eduCourse);
        return eduCourse;
    }
}
