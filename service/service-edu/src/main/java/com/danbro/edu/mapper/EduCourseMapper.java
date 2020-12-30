package com.danbro.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.danbro.edu.dto.EduCoursePublishDto;
import com.danbro.edu.entity.EduCourse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 课程(EduCourse)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-20 19:55:16
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    EduCoursePublishDto getCourseInfoForPublish(String courseId);

}