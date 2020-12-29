package com.danbro.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.edu.dto.EduCourseDto;
import com.danbro.edu.entity.EduCourse;
import com.danbro.edu.entity.EduCourseDescription;
import com.danbro.edu.mapper.EduCourseMapper;
import com.danbro.edu.service.EduCourseDescriptionService;
import com.danbro.edu.service.EduCourseService;
import com.danbro.enums.ResultCode;
import com.danbro.exception.MyCustomException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}