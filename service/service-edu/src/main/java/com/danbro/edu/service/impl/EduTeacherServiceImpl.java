package com.danbro.edu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.edu.controller.dto.FrontPagingDto;
import com.danbro.edu.controller.param.QueryTeacherParam;
import com.danbro.enums.ResultCode;
import com.danbro.exceptions.EduException;
import com.danbro.vo.TeacherVo;
import com.danbro.enity.EduTeacher;
import com.danbro.edu.mapper.EduTeacherMapper;
import com.danbro.edu.service.EduCourseService;
import com.danbro.edu.service.EduTeacherService;
import com.danbro.enity.OutPutPagingDto;
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
    public OutPutPagingDto<TeacherVo> pagingFindTeacherByCondition(Integer current, Integer limit, QueryTeacherParam queryTeacherParam) {
        Page<EduTeacher> page = new Page<>(current, limit);
        Date end = queryTeacherParam.getEnd();
        Date start = queryTeacherParam.getStart();
        Integer level = queryTeacherParam.getLevel();
        String name = queryTeacherParam.getName();
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
        ArrayList<TeacherVo> teacherVoArrayList = new ArrayList<>();
        page.getRecords().forEach(e -> teacherVoArrayList.add(new TeacherVo().convertFrom(e)));
        return new OutPutPagingDto<TeacherVo>().setRows(teacherVoArrayList).setTotal(page.getTotal());
    }

    @Cacheable(value = "teacher", key = "'top-teacher-list'")
    @Override
    public List<TeacherVo> getTopTeacherList(String limit) {
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("level");
        queryWrapper.last(String.format("limit %s", limit));
        List<EduTeacher> eduTeachers = this.list(queryWrapper);
        ArrayList<TeacherVo> topList = new ArrayList<>();
        eduTeachers.forEach(e -> topList.add(new TeacherVo().convertFrom(e)));
        return topList;
    }

    @Override
    public FrontPagingDto<TeacherVo> pagingFindTeacher(Integer current, Integer limit) {
        Page<EduTeacher> page = new Page<>(current, limit);
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        this.page(page, queryWrapper);
        ArrayList<TeacherVo> list = new ArrayList<>();
        page.getRecords().forEach(e -> list.add(new TeacherVo().convertFrom(e)));
        return new FrontPagingDto<TeacherVo>().
                setCurrent(page.getCurrent()).
                setTotal(page.getTotal()).
                setSize(page.getSize()).
                setHasPrevious(page.hasPrevious()).
                setHasNext(page.hasNext()).
                setItems(list).
                setPages(page.getPages());
    }

    @Override
    public TeacherVo getTeacherInfoById(String id) {
        return this.baseMapper.getTeacherInfoById(id);
    }

    @Override
    public EduTeacher insertOrUpdateTeacher(EduTeacher teacher) {
        boolean success = this.saveOrUpdate(teacher);
        if (!success) {
            throw new EduException(ResultCode.TEACHER_INSERT_OR_UPDATE_FAILURE);
        }
        return teacher;
    }

    @Override
    public void removeTeacherByTeacherId(String id) {
        boolean success = this.removeById(id);
        if (!success) {
            throw new EduException(ResultCode.TEACHER_DELETE_FAILURE);
        }
    }
}