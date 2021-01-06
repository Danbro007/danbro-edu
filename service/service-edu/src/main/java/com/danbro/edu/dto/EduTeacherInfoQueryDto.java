package com.danbro.edu.dto;

import java.util.List;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.danbro.edu.entity.EduCourse;
import lombok.Data;

/**
 * @Classname EduTeacherInfoQueryDto
 * @Description TODO 用户查询讲师的详细信息
 * @Date 2021/1/6 19:57
 * @Created by Administrator
 */
@Data
public class EduTeacherInfoQueryDto {
    /**
     * 讲师ID
     */
    @TableId(type = IdType.ASSIGN_ID)
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

    /**
     * 该讲师教授的课程
     */
    private List<EduCourse> courseList;
}
