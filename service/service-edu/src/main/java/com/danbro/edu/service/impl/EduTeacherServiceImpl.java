package com.danbro.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.edu.dto.EduTeacherInfoQueryDto;
import com.danbro.edu.dto.FrontTeacherQueryDto;
import com.danbro.edu.dto.FrontPagingFindTeacherResultDto;
import com.danbro.edu.entity.EduCourse;
import com.danbro.edu.entity.EduTeacher;
import com.danbro.edu.mapper.EduTeacherMapper;
import com.danbro.edu.service.EduCourseService;
import com.danbro.edu.service.EduTeacherService;
import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import com.danbro.exception.MyCustomException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @Classname EduTeacherServiceImpl
 * @Description TODO EduTeacherService 实现类
 * @Date 2020/12/14 15:12
 * @Author Danrbo
 */
@Service("eduTeacherService")
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {
    @Autowired
    EduCourseService eduCourseService;

    @Override
    public Result pagingFindTeacherByCondition(Integer current, Integer limit, FrontTeacherQueryDto frontTeacherQueryDto) {
        Page<EduTeacher> eduTeacherPage = new Page<>(current, limit);
        Date end = frontTeacherQueryDto.getEnd();
        Date start = frontTeacherQueryDto.getStart();
        Integer level = frontTeacherQueryDto.getLevel();
        String name = frontTeacherQueryDto.getName();
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            queryWrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(start)) {
            queryWrapper.gt("gmt_create", start);
        }
        if (!StringUtils.isEmpty(end)) {
            queryWrapper.lt("gmt_create", end);
        }
        // 按照 gmt_create 排序
        queryWrapper.orderByDesc("gmt_create");
        this.page(eduTeacherPage, queryWrapper);
        if (eduTeacherPage.getTotal() > 0) {
            return Result.successOf().
                    setDataChain("total", eduTeacherPage.getTotal()).
                    setDataChain("rows", eduTeacherPage.getRecords());
        }
        throw new MyCustomException(ResultCode.MATCH_CONDITION_TEACHER_NOT_FOUND);
    }

    @Cacheable(value = "teacher", key = "'top-teacher-list'")
    @Override
    public List<EduTeacher> getTopTeacherList(String limit) {
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("level");
        queryWrapper.last(String.format("limit %s", limit));
        return this.list(queryWrapper);
    }

    @Override
    public FrontPagingFindTeacherResultDto pagingFindTeacher(Integer current, Integer limit) {
        Page<EduTeacher> page = new Page<>(current, limit);
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        this.page(page, queryWrapper);
        return FrontPagingFindTeacherResultDto.builder().
                current(page.getCurrent()).
                total(page.getTotal()).
                size(page.getSize()).
                hasPrevious(page.hasPrevious()).
                hasNext(page.hasNext()).
                items(page.getRecords()).
                pages(page.getPages()).build();

    }

    @Override
    public EduTeacherInfoQueryDto getTeacherInfoById(String id) {
        EduTeacher eduTeacher = this.getById(id);
        if (eduTeacher == null) throw new MyCustomException(ResultCode.TEACHER_NOT_FOUND);
        EduTeacherInfoQueryDto queryDto = new EduTeacherInfoQueryDto();
        BeanUtils.copyProperties(eduTeacher, queryDto);
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_id", eduTeacher.getId());
        List<EduCourse> courses = eduCourseService.list(queryWrapper);
        queryDto.setCourseList(courses);
        return queryDto;
    }
}