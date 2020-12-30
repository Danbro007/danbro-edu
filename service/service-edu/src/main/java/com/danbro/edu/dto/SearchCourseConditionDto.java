package com.danbro.edu.dto;

import lombok.Data;

/**
 * @Classname SearchCourseCondition
 * @Description TODO 查询课程的参数
 * @Date 2020/12/30 12:08
 * @Author Danrbo
 */
@Data
public class SearchCourseConditionDto {
    private String title;
    private String status;
}
