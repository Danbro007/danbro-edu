package com.danbro.edu.dto;

import lombok.Data;

/**
 * @Classname EduChapterUpdateInPutDto
 * @Description TODO
 * @Date 2020/12/29 11:59
 * @Author Danrbo
 */
@Data
public class EduChapterUpdateInPutDto {
    private String id;
    private String courseId;
    private String title;
    private Integer sort;
}


