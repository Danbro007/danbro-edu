package com.danbro.edu.controller.param;

import java.math.BigDecimal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import com.danbro.anotation.IsId;
import com.danbro.anotation.IsTitle;
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
 * @Description TODO 后台用户添加或修改课程的参数
 * @Date 2020/12/20 19:48
 * @Author Danrbo
 */
@Data
@ApiModel("添加或修改课程的参数")
public class InsertCourseParam implements ParamConvert<EduCourse> {


    @IsId(message = "修改课程时ID必须存在并且符合格式！", groups = {Update.class})
    @Null(message = "创建课程时ID不能存在！", groups = {Insert.class})
    @ApiModelProperty("课程ID")
    private String id;

    @IsId(message = "讲师ID不合法！", groups = {Insert.class, Update.class})
    @ApiModelProperty("课程讲师ID")
    private String teacherId;

    @IsId(message = "课程分类ID不合法！", groups = {Insert.class, Update.class})
    @ApiModelProperty("课程分类ID")
    private String subjectId;

    @IsTitle
    @ApiModelProperty("课程标题")
    private String title;

    @NotNull(message = "课程价格不能为空！")
    @Min(value = 0, message = "课程价格不能小于 0 元！")
    @ApiModelProperty("课程价格，设置0表示免费。")
    private BigDecimal price;

    @NotNull(message = "课程总课时不能为空！")
    @Min(value = 1, message = "课程总课时要大于 0 ！")
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
