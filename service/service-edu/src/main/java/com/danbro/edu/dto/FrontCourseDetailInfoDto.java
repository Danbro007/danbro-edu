package com.danbro.edu.dto;

import com.danbro.edu.entity.EduCourse;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Classname FrontCourseInfo
 * @Description TODO 前台用户获取课程的详细信息
 * @Date 2021/1/7 14:15
 * @Author Danrbo
 */
@Data
public class FrontCourseDetailInfoDto {
    private String courseId;
    private String courseCover;
    private Integer buyCount;
    private Integer courseLessonNum;
    private BigDecimal coursePrice;
    private String courseTitle;
    private Long viewCount;
    private String courseDescription;
    /**
     * 章节列表
     */
    private List<FrontCourseDetailInfoChapterDto> chapterList;
}
