package com.danbro.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.edu.entity.EduComment;
import com.danbro.edu.mapper.EduCommentMapper;
import com.danbro.edu.service.EduCommentService;
import org.springframework.stereotype.Service;

/**
 * 评论(EduComment)表服务实现类
 *
 * @author makejava
 * @since 2020-12-20 19:55:16
 */
@Service("eduCommentService")
public class EduCommentServiceImpl extends ServiceImpl<EduCommentMapper, EduComment> implements EduCommentService {
}