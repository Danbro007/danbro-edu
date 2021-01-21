package com.danbro.edu.controller.param;

import javax.validation.constraints.NotBlank;
import com.danbro.anotation.IsAssignID;
import com.danbro.enity.EduCourse;
import com.danbro.impl.ParamConvert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @Classname CourseStatusParam
 * @Description TODO 更新课程发布状态的参数
 * @Date 2021/1/19 19:40
 * @Created by Administrator
 */
@Data
@ApiModel("修改课程发布发布的参数")
public class CourseStatusParam implements ParamConvert<EduCourse> {

    @IsAssignID(message = "课程ID不能为空并且必须大于0！")
    @ApiModelProperty("课程ID")
    private String id;

    @NotBlank(message = "课程发布状态不能为空！")
    @ApiModelProperty("课程发布状态")
    private String status;

    @Override
    public EduCourse convertTo() {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(this, eduCourse);
        return eduCourse;
    }
}
