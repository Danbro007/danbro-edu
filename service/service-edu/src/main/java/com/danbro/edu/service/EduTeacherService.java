package com.danbro.edu.service;

import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.danbro.dto.TeacherTopDto;
import com.danbro.edu.dto.FrontPagingDto;
import com.danbro.edu.dto.FrontTeacherInfoQueryDto;
import com.danbro.edu.dto.FrontTeacherQueryDto;
import com.danbro.edu.entity.EduTeacher;

/**
 * @Classname EduTeacherService
 * @Description TODO
 * @Date 2020/12/14 15:13
 * @Author Danrbo
 */
public interface EduTeacherService extends IService<EduTeacher> {
    Page<EduTeacher> pagingFindTeacherByCondition(Integer current, Integer limit, FrontTeacherQueryDto frontTeacherQueryDto);

    List<TeacherTopDto> getTopTeacherList(String limit);

    FrontPagingDto<EduTeacher> pagingFindTeacher(Integer current, Integer limit);

    FrontTeacherInfoQueryDto getTeacherInfoById(String id);
}
