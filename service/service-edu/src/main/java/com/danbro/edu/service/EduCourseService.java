package com.danbro.edu.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.dto.CourseTopDto;
import com.danbro.dto.FrontCourseDetailInfoDto;
import com.danbro.edu.controller.dto.FrontCourseConditionPagingDto;
import com.danbro.edu.controller.dto.FrontPagingDto;
import com.danbro.edu.controller.vo.CoursePublishVo;
import com.danbro.edu.controller.dto.SearchCourseConditionDto;
import com.danbro.edu.controller.param.InsertCourseParam;
import com.danbro.edu.controller.vo.CourseVo;
import com.danbro.edu.entity.EduCourse;

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
     * @param insertCourseParam 课程参数
     * @return
     */
    EduCourse insert(InsertCourseParam insertCourseParam);

    /**
     * 根据 courseId 获取课程基本信息（不包括课程里的章节和小节）
     *
     * @param courseId 课程Id
     * @return 课程信息
     */
    CourseVo getCourseBasicInfo(String courseId);

    /**
     * 根据课程Id获取要发布的课程信息
     *
     * @param courseId 课程Id
     * @return 要发布的课程信息
     */
    CoursePublishVo getCourseInfoForPublish(String courseId);

    /**
     * 分页查询课程带有查询条件
     *
     * @param current      当前页
     * @param limit        每页显示的个数
     * @param conditionDto 查询条件
     * @return 符合条件的课程
     */
    Page<EduCourse> pagingFindByCondition(Integer current, Integer limit, SearchCourseConditionDto conditionDto);

    /**
     * 通过课程ID删除课程
     *
     * @param id 课程ID
     * @return 删除结果
     */
    Boolean removeCourse(String id);

    /**
     * 获取前 limit 名的课程
     *
     * @param limit 个数
     * @return 课程列表
     */
    List<CourseTopDto> getTopCourseList(String limit);

    /**
     * @param current
     * @param limit
     * @param dto
     * @return
     */
    FrontPagingDto<EduCourse> pagingFindCourseByCondition(Long current, Long limit, FrontCourseConditionPagingDto dto);

    /**
     * 通过课程ID获取所有相关的章节和小节
     *
     * @param courseId 课程ID
     * @return 课程详细信息
     */
    FrontCourseDetailInfoDto getCourseDetailInfo(String courseId);

    /**
     * 更新或者更新课程
     *
     * @param eduCourse 要添加或者更新的课程
     * @return 添加或者更新完毕的课程对象
     */
    EduCourse insertOrUpdateCourse(EduCourse eduCourse);
}