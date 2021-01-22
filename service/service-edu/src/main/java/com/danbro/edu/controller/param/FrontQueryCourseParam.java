package com.danbro.edu.controller.param;

import java.io.Serializable;
import com.danbro.anotation.IsAssignID;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname FrontCourseConditionPagingDto
 * @Description TODO 前台用户带有条件的分页查询课程
 * @Date 2021/1/7 12:14
 * @Author Danrbo
 */
@Data
public class FrontQueryCourseParam implements Serializable {

    @ApiModelProperty(value = "课程名称")
    private String title;

    @IsAssignID(message = "讲师ID非法！", required = false)
    @ApiModelProperty(value = "讲师id")
    private String teacherId;

    @IsAssignID(message = "一级类别ID非法！", required = false)
    @ApiModelProperty(value = "一级类别id")
    private String subjectParentId;

    @IsAssignID(message = "二级类别ID非法！", required = false)
    @ApiModelProperty(value = "二级类别id")
    private String subjectId;

    @ApiModelProperty(value = "根据什么字段排序排序")
    private String sortType;
}
