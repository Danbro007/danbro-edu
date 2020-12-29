package com.danbro.edu.dto;

import lombok.Data;

/**
 * @Classname EduVideoInsertInPutDto
 * @Description TODO 更新视频的参数实体类
 * @Date 2020/12/29 12:17
 * @Author Danrbo
 */
@Data
public class EduVideoUpdateInPutDto {
    private String id;
    /**
     * 节点名称
     */
    private String title;
    /**
     * 排序字段
     */
    private Integer sort;


    private Boolean isFree;
}
