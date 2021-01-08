package com.danbro.edu.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

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
    private Long viewCount;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private BigDecimal coursePrice;
    private String courseTitle;
    private String courseDescription;
    private String teacherName;
    private String teacherId;
    private String teacherIntro;
    private String teacherAvatar;
    /**
     * 章节列表
     */
    private List<FrontCourseDetailInfoChapterDto> chapterList;
}
