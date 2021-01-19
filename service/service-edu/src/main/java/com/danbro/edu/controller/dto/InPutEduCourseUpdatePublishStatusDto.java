package com.danbro.edu.controller.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @Classname EduCoursePublishStatusDto
 * @Description TODO 后台用户修改课程发布状态的参数
 * @Date 2020/12/30 11:26
 * @Author Danrbo
 */
@Data
public class InPutEduCourseUpdatePublishStatusDto {

    @NotBlank(message = "课程ID不能为空！")
    private String id;
    @NotBlank(message = "课程状态不能为空！")
    private String status;
}
