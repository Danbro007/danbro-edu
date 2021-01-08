package com.danbro.edu.dto;

import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @Classname EduTeacherInfoQueryDto
 * @Description TODO 用户查询讲师的详细信息
 * @Date 2021/1/6 19:57
 * @Created by Administrator
 */
@Data
public class FrontTeacherInfoQueryDto {
    private String id;
    private String name;
    private String intro;
    private String career;
    private String avatar;
    private List<FrontCourseTitleDto> courseList;
}
