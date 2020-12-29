package com.danbro.edu.dto;

import com.danbro.edu.entity.EduChapter;
import com.danbro.edu.entity.EduVideo;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname EduChapterListDto
 * @Description TODO
 * @Date 2020/12/23 15:13
 * @Author Danrbo
 */
@Data
@Accessors(chain = true)
public class EduChapterOutPutDto {
    private String label;
    private String id;
    private Integer sort;
    private List<EduVideoOutPutDto> children = new ArrayList<>();
}
