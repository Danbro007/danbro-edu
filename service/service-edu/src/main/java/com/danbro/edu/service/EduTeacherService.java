package com.danbro.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.edu.dto.FrontTeacherInfoQueryDto;
import com.danbro.edu.dto.FrontPagingFindTeacherResultDto;
import com.danbro.edu.entity.EduTeacher;
import com.danbro.edu.dto.FrontTeacherQueryDto;
import com.danbro.enums.Result;

import java.util.List;

/**
 * @Classname EduTeacherService
 * @Description TODO
 * @Date 2020/12/14 15:13
 * @Author Danrbo
 */
public interface EduTeacherService extends IService<EduTeacher> {
    Result pagingFindTeacherByCondition(Integer current, Integer limit, FrontTeacherQueryDto frontTeacherQueryDto);

    List<EduTeacher> getTopTeacherList(String limit);

    FrontPagingFindTeacherResultDto pagingFindTeacher(Integer current, Integer limit);

    FrontTeacherInfoQueryDto getTeacherInfoById(String id);
}
