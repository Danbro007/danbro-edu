package com.danbro.edu.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * @Classname EduChapterDeleteInPutDto
 * @Description TODO 删除章节的输入参数
 * @Date 2020/12/29 15:28
 * @Author Danrbo
 */
@Data
public class InPutEduChapterDeleteDto {
    private String id;
    private List<String> children = new ArrayList<>();
}
