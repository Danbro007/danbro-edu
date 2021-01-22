package com.danbro.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.enity.EduCourseDescription;
import com.danbro.edu.mapper.EduCourseDescriptionMapper;
import com.danbro.edu.service.EduCourseDescriptionService;
import org.springframework.stereotype.Service;

/**
 * 课程简介(EduCourseDescription)表服务实现类
 *
 * @author makejava
 * @since 2020-12-20 19:55:16
 */
@Service("eduCourseDescriptionService")
public class EduCourseDescriptionServiceImpl extends ServiceImpl<EduCourseDescriptionMapper, EduCourseDescription> implements EduCourseDescriptionService {
}