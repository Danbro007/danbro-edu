package com.danbro.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * @Classname CourseTopDto
 * @Description TODO 热门课程
 * @Date 2021/1/9 13:19
 * @Created by Administrator
 */
@Data
public class CourseTopDto implements Serializable {
    private String id;
    /**
     * 课程标题
     */
    private String title;
    /**
     * 课程封面图片路径
     */
    private String cover;
    /**
     * 销售数量
     */
    private Integer buyCount;
    /**
     * 浏览数量
     */
    private Long viewCount;
}
