package com.danbro.edu.controller.dto;

import lombok.Data;

/**
 * @Classname SearchCourseCondition
 * @Description TODO 查询课程的参数
 * @Date 2020/12/30 12:08
 * @Author Danrbo
 */
@Data
public class SearchCourseConditionDto {
    /**
     * 课程名
     */
    private String title;
    /**
     * 课程状态
     */
    private String status;
}
