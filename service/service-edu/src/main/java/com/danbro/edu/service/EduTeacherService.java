package com.danbro.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.edu.entity.EduTeacher;
import com.danbro.edu.dto.EduTeacherQueryDto;
import com.danbro.enums.Result;

/**
 * @Classname EduTeacherService
 * @Description TODO
 * @Date 2020/12/14 15:13
 * @Author Danrbo
 */
public interface EduTeacherService extends IService<EduTeacher> {
    Result pagingFindTeacherByCondition(Integer current, Integer limit, EduTeacherQueryDto eduTeacherQueryDto);

}
