package com.danbro.edu.controller.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname EduCoursePublishDto
 * @Description TODO 展示给后台用户课程发布的数据
 * @Date 2020/12/29 21:05
 * @Author Danrbo
 */
@Data
public class CoursePublishVo {
    @ApiModelProperty("课程ID")
    private String id;

    @ApiModelProperty("课程标题")
    private String title;

    @ApiModelProperty("课程价格")
    private String price;

    @ApiModelProperty("课程数")
    private String lessonNum;

    @ApiModelProperty("课程描述")
    private String description;

    @ApiModelProperty("课程封面")
    private String cover;

    @ApiModelProperty("讲师名")
    private String teacherName;

    @ApiModelProperty("一级分类名")
    private String oneSubject;

    @ApiModelProperty("二级分类名")
    private String twoSubject;
}
