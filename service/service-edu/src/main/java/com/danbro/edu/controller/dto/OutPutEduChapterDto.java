package com.danbro.edu.controller.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Classname EduChapterListDto
 * @Description TODO 展示给后台用户章节及所有小节的返回数据
 * @Date 2020/12/23 15:13
 * @Author Danrbo
 */
@Data
@Accessors(chain = true)
public class OutPutEduChapterDto {
    private String label;
    private String id;
    private Integer sort;
    private List<OutPutEduVideoDto> children = new ArrayList<>();
}
