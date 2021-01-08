package com.danbro.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Classname EduCourseBasicInfoDto
 * @Description TODO
 * @Date 2021/1/8 14:38
 * @Author Danrbo
 */
@Data
public class EduCourseBasicInfoDto {
    private String id;

    private String teacherId;

    private String teacherName;

    private String subjectId;

    private String title;

    private BigDecimal price;

    private Integer lessonNum;

    private String cover;

    private String description;

    private String subjectParentId;
}
