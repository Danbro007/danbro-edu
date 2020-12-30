package com.danbro.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.edu.dto.EduCourseDto;
import com.danbro.edu.dto.EduCoursePublishDto;
import com.danbro.edu.dto.SearchCourseConditionDto;
import com.danbro.edu.entity.EduCourse;

/**
 * 课程(EduCourse)表服务接口
 *
 * @author makejava
 * @since 2020-12-20 19:55:16
 */
public interface EduCourseService extends IService<EduCourse>{

    EduCourse insert(EduCourseDto eduCourseDto);

    EduCourseDto getCourseInfo(String courseId);

    Boolean updateCourseInfo(EduCourseDto eduCourseDto);

    EduCoursePublishDto getCourseInfoForPublish(String courseId);

   Page<EduCourse> pagingFindByCondition(Integer current, Integer limit, SearchCourseConditionDto conditionDto);

    Boolean removeCourse(String id);
}