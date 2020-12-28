package com.danbro.edu.dto;

import com.danbro.edu.entity.EduChapter;
import com.danbro.edu.entity.EduVideo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname EduChapterListDto
 * @Description TODO
 * @Date 2020/12/23 15:13
 * @Author Danrbo
 */
@Data
public class EduChapterOutPutDto {
    private String title;
    private String id;
    private List<EduVideoOutPutDto> children = new ArrayList<>();
}
