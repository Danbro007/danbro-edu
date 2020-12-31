package com.danbro.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.edu.dto.EduCourseDto;
import com.danbro.edu.dto.EduCoursePublishDto;
import com.danbro.edu.dto.SearchCourseConditionDto;
import com.danbro.edu.entity.EduCourse;
import com.danbro.edu.entity.EduCourseDescription;
import com.danbro.edu.mapper.EduCourseMapper;
import com.danbro.edu.service.EduChapterService;
import com.danbro.edu.service.EduCourseDescriptionService;
import com.danbro.edu.service.EduCourseService;
import com.danbro.edu.service.EduVideoService;
import com.danbro.enums.ResultCode;
import com.danbro.exception.MyCustomException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 课程(EduCourse)表服务实现类
 *
 * @author makejava
 * @since 2020-12-20 19:55:16
 */
@Service("eduCourseService")
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    EduCourseDescriptionService eduCourseDescriptionService;
    @Autowired
    EduChapterService eduChapterService;
    @Autowired
    EduVideoService eduVideoService;

    @Override
    public EduCourse insert(EduCourseDto eduCourseDto) {
        EduCourse eduCourse = new EduCourse();
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(eduCourseDto, eduCourse);
        boolean f = this.save(eduCourse);
        if (!f) {
            throw new MyCustomException(ResultCode.INSERT_COURSE_FAILURE);
        }
        eduCourseDescription.
                setDescription(eduCourseDto.getDescription()).
                setId(eduCourse.getId());
        boolean b = eduCourseDescriptionService.save(eduCourseDescription);
        if (!b) {
            throw new MyCustomException(ResultCode.INSERT_COURSE_DESCRIPTION_FAILURE);
        }
        return eduCourse;
    }

    @Override
    public EduCourseDto getCourseInfo(String courseId) {
        EduCourseDto eduCourseDto = new EduCourseDto();
        EduCourse course = this.getById(courseId);
        BeanUtils.copyProperties(course, eduCourseDto);
        EduCourseDescription courseDescription = eduCourseDescriptionService.getById(courseId);
        BeanUtils.copyProperties(courseDescription, eduCourseDto);
        return eduCourseDto;
    }

    @Override
    public Boolean updateCourseInfo(EduCourseDto eduCourseDto) {
        try {
            EduCourse eduCourse = new EduCourse();
            BeanUtils.copyProperties(eduCourseDto, eduCourse);
            this.updateById(eduCourse);
            EduCourseDescription courseDescription = new EduCourseDescription();
            BeanUtils.copyProperties(eduCourseDto, courseDescription);
            eduCourseDescriptionService.updateById(courseDescription);
        } catch (BeansException e) {
            return false;
        }
        return true;
    }


    @Override
    public EduCoursePublishDto getCourseInfoForPublish(String courseId) {
        return baseMapper.getCourseInfoForPublish(courseId);
    }

    @Override
    public Page<EduCourse> pagingFindByCondition(Integer current, Integer limit, SearchCourseConditionDto conditionDto) {
        Page<EduCourse> coursePage = new Page<>(current, limit);
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        if (conditionDto != null) {
            if (!StringUtils.isEmpty(conditionDto.getStatus())) {
                queryWrapper.eq("status", conditionDto.getStatus());
            }
            if (!StringUtils.isEmpty(conditionDto.getTitle())) {
                queryWrapper.like("title", conditionDto.getTitle());
            }
        }
        queryWrapper.orderByDesc("gmt_create");
        return this.page(coursePage, queryWrapper);
    }

    /**
     * 删除课程相关的章节、视频
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean removeCourse(String id) {
        eduChapterService.removeByCourseId(id);
        eduVideoService.removeByCourseId(id);
        return eduCourseDescriptionService.removeById(id) &&
                this.removeById(id);
    }
}