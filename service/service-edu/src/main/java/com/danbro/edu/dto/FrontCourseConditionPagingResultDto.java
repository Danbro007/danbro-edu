package com.danbro.edu.dto;

import com.danbro.edu.entity.EduCourse;
import com.danbro.edu.entity.EduTeacher;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @Classname FrontCourseConditionPagingResultDto
 * @Description TODO 前台用户分页带有条件查询课程的结果
 * @Date 2021/1/7 12:19
 * @Author Danrbo
 */
@Data
@Accessors(chain = true)
@Builder
public class FrontCourseConditionPagingResultDto implements Serializable {
    private List<EduCourse> items;
    private Long current;
    private Long pages;
    private Long size;
    private Long total;
    private Boolean hasNext;
    private Boolean hasPrevious;
}
