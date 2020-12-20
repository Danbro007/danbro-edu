package com.danbro.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.edu.entity.EduCourseCollect;
import com.danbro.edu.mapper.EduCourseCollectMapper;
import com.danbro.edu.service.EduCourseCollectService;
import org.springframework.stereotype.Service;

/**
 * 课程收藏(EduCourseCollect)表服务实现类
 *
 * @author makejava
 * @since 2020-12-20 19:55:16
 */
@Service("eduCourseCollectService")
public class EduCourseCollectServiceImpl extends ServiceImpl<EduCourseCollectMapper, EduCourseCollect> implements EduCourseCollectService {
}