package com.danbro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.entity.EduTeacher;
import com.danbro.dto.EduTeacherQueryDto;
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
