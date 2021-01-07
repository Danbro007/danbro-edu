package com.danbro.edu.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Classname FrontCourseCommentDto
 * @Description TODO 返回给前台用户的课程评论分页类
 * @Date 2021/1/7 21:01
 * @Created by Administrator
 */
@Data
@Accessors(chain = true)
@Builder
public class FrontCourseCommentPagingDto {
    private List<FrontCourseCommentDto> items;
    private Long current;
    private Long pages;
    private Long size;
    private Long total;
    private Boolean hasNext;
    private Boolean hasPrevious;
}
