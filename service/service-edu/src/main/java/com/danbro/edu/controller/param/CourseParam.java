package com.danbro.edu.controller.param;

import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import com.danbro.anotation.IsAssignID;
import com.danbro.anotation.IsPositiveNum;
import com.danbro.anotation.IsPrice;
import com.danbro.enity.EduCourse;
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
public class CourseParam implements ParamConvert<EduCourse> {

    @IsAssignID(message = "修改课程时ID必须存在并且符合格式！", groups = {Update.class})
    @Null(message = "创建课程时ID不能存在！", groups = {Insert.class})
    @ApiModelProperty("课程ID")
    private String id;

    @IsAssignID(message = "讲师ID必须存在并且符合格式！", groups = {Insert.class, Update.class})
    @ApiModelProperty("课程讲师ID")
    private String teacherId;

    @IsAssignID(message = "课程分类ID必须存在并且符合格式！", groups = {Insert.class, Update.class})
    @ApiModelProperty("课程分类ID")
    private String subjectId;

    @ApiModelProperty("课程标题")
    private String title;

    @IsPrice(groups = {Insert.class, Update.class})
    @ApiModelProperty("课程价格，设置0表示免费。")
    private String price;


    @IsPositiveNum(message = "课程总时必须存在并且大于0！", groups = {Insert.class, Update.class})
    @ApiModelProperty("课程总课时")
    private String lessonNum;


    @NotBlank(message = "请上传课程封面！", groups = {Insert.class, Update.class})
    @ApiModelProperty("课程封面图片路径")
    private String cover;

    @NotBlank(message = "课程简介不能为空！", groups = {Insert.class, Update.class})
    @ApiModelProperty("课程简介")
    private String description;

    @IsAssignID(groups = {Insert.class, Update.class})
    @ApiModelProperty("父类分类ID")
    private String subjectParentId;

    @Override
    public EduCourse convertTo() {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(this, eduCourse);
        eduCourse.setPrice(new BigDecimal(this.getPrice()));
        eduCourse.setLessonNum(Integer.getInteger(this.getLessonNum()));
        return eduCourse;
    }
}
