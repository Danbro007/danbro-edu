package com.danbro.edu.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Classname EduCoursePublishStatusDto
 * @Description TODO 修改课程发布状态的参数实体类
 * @Date 2020/12/30 11:26
 * @Author Danrbo
 */
@Data
public class EduCoursePublishStatusDto {

    @NotBlank(message = "课程ID不能为空！")
    private String id;
    @NotBlank(message = "课程状态不能为空！")
    private String status;
}
