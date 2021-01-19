package com.danbro.edu.controller.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Classname FrontPagingDto
 * @Description TODO 用户在前台分页查询 R 列表的结果
 * @Date 2020/12/15 11:35
 * @Author Danrbo
 */
@Accessors(chain = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FrontPagingDto<R> {
    private List<R> items;
    private Long current;
    private Long pages;
    private Long size;
    private Long total;
    private Boolean hasNext;
    private Boolean hasPrevious;



}
