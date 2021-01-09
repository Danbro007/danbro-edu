package com.danbro.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.danbro.edu.dto.OutPutEduCoursePublishDto;
import com.danbro.dto.FrontCourseDetailInfoDto;
import com.danbro.edu.entity.EduCourse;

/**
 * 课程(EduCourse)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-20 19:55:16
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    OutPutEduCoursePublishDto getCourseInfoForPublish(String courseId);
    FrontCourseDetailInfoDto getCourseDetailInfo(String courseId);

}