package com.danbro.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.edu.dto.EduCourseInputDto;
import com.danbro.edu.entity.EduCourse;
import java.util.List;

/**
 * 课程(EduCourse)表服务接口
 *
 * @author makejava
 * @since 2020-12-20 19:55:16
 */
public interface EduCourseService extends IService<EduCourse>{

    EduCourse insert(EduCourseInputDto eduCourseInputDto);
}