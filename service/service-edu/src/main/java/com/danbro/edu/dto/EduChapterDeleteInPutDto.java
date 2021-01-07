package com.danbro.edu.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname EduChapterDeleteInPutDto
 * @Description TODO 删除章节的参数
 * @Date 2020/12/29 15:28
 * @Author Danrbo
 */
@Data
public class EduChapterDeleteInPutDto {
    private String id;
    private List<String> children = new ArrayList<>();
}
