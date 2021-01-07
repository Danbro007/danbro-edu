package com.danbro.edu.dto;

import java.util.List;
import com.danbro.edu.entity.EduTeacher;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Classname FrontPagingFindTeacherResultDto
 * @Description TODO 用户在前台分页查询讲师列表的结果
 * @Date 2020/12/15 11:35
 * @Author Danrbo
 */
@Accessors(chain = true)
@Data
@Builder
public class FrontPagingFindTeacherResultDto {
    private List<EduTeacher> items;
    private Long current;
    private Long pages;
    private Long size;
    private Long total;
    private Boolean hasNext;
    private Boolean hasPrevious;
}
