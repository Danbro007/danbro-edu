package com.danbro.dto;

import lombok.Data;

/**
 * @Classname TeacherTopDto
 * @Description TODO
 * @Date 2021/1/9 13:30
 * @Created by Administrator
 */
@Data
public class TeacherTopDto {
    /**
     * 讲师ID
     */
    private String id;
    /**
     * 讲师姓名
     */
    private String name;
    /**
     * 讲师简介
     */
    private String intro;
    /**
     * 讲师资历,一句话说明讲师
     */
    private String career;
    /**
     * 头衔 1高级讲师 2首席讲师
     */
    private Integer level;
    /**
     * 讲师头像
     */
    private String avatar;
    /**
     * 排序
     */
    private Integer sort;

}
