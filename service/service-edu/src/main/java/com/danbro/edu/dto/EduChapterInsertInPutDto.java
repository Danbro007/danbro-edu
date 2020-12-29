package com.danbro.edu.dto;

import lombok.Data;

/**
 * @Classname EduChapterInPutDto
 * @Description TODO
 * @Date 2020/12/28 16:27
 * @Author Danrbo
 */
@Data
public class EduChapterInsertInPutDto {
    private String courseId;
    private String title;
    private Integer sort;
}
