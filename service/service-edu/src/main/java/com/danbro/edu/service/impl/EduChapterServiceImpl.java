package com.danbro.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.edu.entity.EduChapter;
import com.danbro.edu.mapper.EduChapterMapper;
import com.danbro.edu.service.EduChapterService;
import org.springframework.stereotype.Service;

/**
 * 课程(EduChapter)表服务实现类
 *
 * @author makejava
 * @since 2020-12-20 19:55:16
 */
@Service("eduChapterService")
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
}