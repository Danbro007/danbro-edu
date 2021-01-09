package com.danbro.edu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.dto.TeacherTopDto;
import com.danbro.edu.dto.FrontPagingDto;
import com.danbro.edu.dto.FrontTeacherInfoQueryDto;
import com.danbro.edu.dto.FrontTeacherQueryDto;
import com.danbro.edu.entity.EduTeacher;
import com.danbro.edu.mapper.EduTeacherMapper;
import com.danbro.edu.service.EduCourseService;
import com.danbro.edu.service.EduTeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
    public Page<EduTeacher> pagingFindTeacherByCondition(Integer current, Integer limit, FrontTeacherQueryDto frontTeacherQueryDto) {
        Page<EduTeacher> page = new Page<>(current, limit);
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
        this.page(page, queryWrapper);
        return page;
    }

    @Cacheable(value = "teacher", key = "'top-teacher-list'")
    @Override
    public List<TeacherTopDto> getTopTeacherList(String limit) {
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("level");
        queryWrapper.last(String.format("limit %s", limit));
        List<EduTeacher> eduTeachers = this.list(queryWrapper);
        ArrayList<TeacherTopDto> topDtos = new ArrayList<>();
        eduTeachers.forEach(e -> {
            TeacherTopDto topDto = new TeacherTopDto();
            BeanUtils.copyProperties(e, topDto);
            topDtos.add(topDto);
        });
        return topDtos;
    }

    @Override
    public FrontPagingDto<EduTeacher> pagingFindTeacher(Integer current, Integer limit) {
        Page<EduTeacher> page = new Page<>(current, limit);
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        this.page(page, queryWrapper);
        return new FrontPagingDto<EduTeacher>().
                setCurrent(page.getCurrent()).
                setTotal(page.getTotal()).
                setSize(page.getSize()).
                setHasPrevious(page.hasPrevious()).
                setHasNext(page.hasNext()).
                setItems(page.getRecords()).
                setPages(page.getPages());
    }

    @Override
    public FrontTeacherInfoQueryDto getTeacherInfoById(String id) {
        return this.baseMapper.getTeacherInfoById(id);
    }
}