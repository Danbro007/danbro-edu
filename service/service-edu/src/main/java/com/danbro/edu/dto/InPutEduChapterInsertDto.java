package com.danbro.edu.dto;

import lombok.Data;

/**
 * @Classname EduChapterInPutDto
 * @Description TODO 添加章节的输入参数
 * @Date 2020/12/28 16:27
 * @Author Danrbo
 */
@Data
public class InPutEduChapterInsertDto {
    private String courseId;
    private String title;
    private Integer sort;
}
