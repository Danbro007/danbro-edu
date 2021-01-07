package com.danbro.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.edu.dto.*;
import com.danbro.edu.entity.EduCourse;

import java.util.List;

/**
 * 课程(EduCourse)表服务接口
 *
 * @author makejava
 * @since 2020-12-20 19:55:16
 */
public interface EduCourseService extends IService<EduCourse> {
    /**
     * 添加课程
     *
     * @param eduCourseDto 课程参数
     * @return
     */
    EduCourse insert(EduCourseDto eduCourseDto);

    /**
     * 根据 courseId 获取课程信息
     *
     * @param courseId 课程Id
     * @return 课程信息
     */
    EduCourseDto getCourseInfo(String courseId);

    /**
     * 更新课程
     *
     * @param eduCourseDto 课程信息
     * @return 更新结果
     */
    Boolean updateCourseInfo(EduCourseDto eduCourseDto);

    /**
     * 根据课程Id获取要发布的课程信息
     * @param courseId 课程Id
     * @return 要发布的课程信息
     */
    EduCoursePublishDto getCourseInfoForPublish(String courseId);

    Page<EduCourse> pagingFindByCondition(Integer current, Integer limit, SearchCourseConditionDto conditionDto);

    Boolean removeCourse(String id);

    List<EduCourse> getTopCourseList(String limit);

    FrontCourseConditionPagingResultDto pagingFindCourseByCondition(Long current, Long limit, FrontCourseConditionPagingDto dto);
}