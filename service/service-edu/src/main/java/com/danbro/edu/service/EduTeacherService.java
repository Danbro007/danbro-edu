package com.danbro.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.edu.dto.EduTeacherInfoQueryDto;
import com.danbro.edu.dto.PagingFindTeacherDto;
import com.danbro.edu.entity.EduTeacher;
import com.danbro.edu.dto.EduTeacherQueryDto;
import com.danbro.enums.Result;

import java.util.List;

/**
 * @Classname EduTeacherService
 * @Description TODO
 * @Date 2020/12/14 15:13
 * @Author Danrbo
 */
public interface EduTeacherService extends IService<EduTeacher> {
    Result pagingFindTeacherByCondition(Integer current, Integer limit, EduTeacherQueryDto eduTeacherQueryDto);

    List<EduTeacher> getTopTeacherList(String limit);

    PagingFindTeacherDto pagingFindTeacher(Integer current, Integer limit);

    EduTeacherInfoQueryDto getTeacherInfoById(String id);
}
