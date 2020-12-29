package com.danbro.edu.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Classname EduVideoDto
 * @Description TODO 查询课程大纲返回的video
 * @Date 2020/12/28 12:03
 * @Author Danrbo
 */
@Data
@Accessors(chain = true)
public class EduVideoOutPutDto {
    private String id;
    private String label;
    private Boolean isFree;
    private Integer sort;
}
