package com.danbro.edu.controller.dto;

import lombok.Data;

/**
 * @Classname EduCourseTitleDto
 * @Description TODO 给前台用户展示课程信息
 * @Date 2021/1/7 16:23
 * @Author Danrbo
 */
@Data
public class FrontCourseTitleDto {
    private String courseTitle;
    private String courseId;
    private String courseCover;
}
