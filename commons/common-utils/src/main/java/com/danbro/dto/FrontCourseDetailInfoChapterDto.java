package com.danbro.dto;

import lombok.Data;

import java.util.List;

/**
 * @Classname FrontCourseDetailInfoChapterDto
 * @Description TODO
 * @Date 2021/1/7 14:22
 * @Author Danrbo
 */
@Data
public class FrontCourseDetailInfoChapterDto {
    private String chapterTitle;
    private String chapterId;
    /**
     * 小节列表
     */
    private List<FrontCourseDetailInfoVideoDto> videoList;
}
