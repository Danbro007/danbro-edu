package com.danbro.edu.controller.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname SearchCourseCondition
 * @Description TODO 查询课程的参数
 * @Date 2020/12/30 12:08
 * @Author Danrbo
 */
@Data
public class QueryCourseParam {
    @ApiModelProperty("课程标题")
    private String title;

    @ApiModelProperty("课程发布状态")
    private String status;
}
