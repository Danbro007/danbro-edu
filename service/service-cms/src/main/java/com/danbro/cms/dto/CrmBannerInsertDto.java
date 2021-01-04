package com.danbro.cms.dto;

import lombok.Data;

/**
 * @Classname CrmBannerInsertDto
 * @Description TODO
 * @Date 2021/1/4 15:28
 * @Author Danrbo
 */
@Data
public class CrmBannerInsertDto {
    /**
     * 标题
     */
    private String title;
    /**
     * 图片地址
     */
    private String imageUrl;
    /**
     * 链接地址
     */
    private String linkUrl;
    /**
     * 排序
     */
    private Integer sort;
}
