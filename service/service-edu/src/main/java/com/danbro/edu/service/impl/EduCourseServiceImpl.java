package com.danbro.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.edu.dto.EduCourseInputDto;
import com.danbro.edu.entity.EduCourse;
import com.danbro.edu.entity.EduCourseDescription;
import com.danbro.edu.mapper.EduCourseMapper;
import com.danbro.edu.service.EduCourseDescriptionService;
import com.danbro.edu.service.EduCourseService;
import com.danbro.enums.ResultCode;
import com.danbro.exception.MyCustomException;
import org.springframework.beans.BeanUtils;
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
    public void insert(EduCourseInputDto eduCourseInputDto) {
        EduCourse eduCourse = new EduCourse();
        EduCourseDescription eduCourseDescription = new EduCourseDescription();

        BeanUtils.copyProperties(eduCourseInputDto, eduCourse);
        boolean f = this.save(eduCourse);
        if (!f) {
            throw new MyCustomException(ResultCode.INSERT_COURSE_FAILURE);
        }

        eduCourseDescription.
                setDescription(eduCourseInputDto.getDescription()).
                setId(eduCourse.getId());
        boolean b = eduCourseDescriptionService.save(eduCourseDescription);
        if (!b) {
            throw new MyCustomException(ResultCode.INSERT_COURSE_DESCRIPTION_FAILURE);
        }
    }
}