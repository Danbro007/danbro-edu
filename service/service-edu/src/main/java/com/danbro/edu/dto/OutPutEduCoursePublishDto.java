package com.danbro.edu.dto;

import lombok.Data;

/**
 * @Classname EduCoursePublishDto
 * @Description TODO 展示给后台用户课程发布的数据
 * @Date 2020/12/29 21:05
 * @Author Danrbo
 */
@Data
public class OutPutEduCoursePublishDto {
    private String id;
    private String title;
    private String price;
    private String lessonNum;
    private String description;
    private String cover;
    private String teacherName;
    private String oneSubject;
    private String twoSubject;
}
