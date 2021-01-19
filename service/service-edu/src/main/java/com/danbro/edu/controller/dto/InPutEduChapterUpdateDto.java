package com.danbro.edu.controller.dto;

import lombok.Data;

/**
 * @Classname EduChapterUpdateInPutDto
 * @Description TODO 后台用户更新章节的输入参数
 * @Date 2020/12/29 11:59
 * @Author Danrbo
 */
@Data
public class InPutEduChapterUpdateDto {
    private String id;
    private String courseId;
    private String title;
    private Integer sort;
}


