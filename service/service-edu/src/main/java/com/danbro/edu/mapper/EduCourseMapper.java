package com.danbro.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.danbro.dto.FrontCourseDetailInfoDto;
import com.danbro.edu.controller.vo.CoursePublishVo;
import com.danbro.edu.controller.vo.CourseVo;
import com.danbro.edu.entity.EduCourse;

/**
 * 课程(EduCourse)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-20 19:55:16
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {


    CoursePublishVo getCourseInfoForPublish(String courseId);

    FrontCourseDetailInfoDto getCourseDetailInfo(String courseId);

    /**
     * 通过课程ID获取课程基本信息（课程讲师）
     *
     * @param courseId 课程ID
     * @return 课程基本信息
     */
    CourseVo getCourseBasicInfoByCourseId(String courseId);
}