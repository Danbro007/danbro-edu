package com.danbro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.mapper.EduTeacherMapper;
import com.danbro.entity.EduTeacher;
import com.danbro.service.EduTeacherService;
import com.danbro.vo.TeacherQueryVo;
import enums.Result;
import enums.ResultCode;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;

/**
 * @Classname EduTeacherServiceImpl
 * @Description TODO
 * @Date 2020/12/14 15:12
 * @Author Danrbo
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public Result pagingFindTeacherByCondition(Integer current, Integer limit, TeacherQueryVo teacherQueryVo) {
        Page<EduTeacher> eduTeacherPage = new Page<>(current, limit);
        Date end = teacherQueryVo.getEnd();
        Date start = teacherQueryVo.getStart();
        Integer level = teacherQueryVo.getLevel();
        String name = teacherQueryVo.getName();
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
        this.page(eduTeacherPage, queryWrapper);
        HashMap<String, Object> map = new HashMap<>(16);
        map.put("total", eduTeacherPage.getTotal());
        map.put("rows", eduTeacherPage.getRecords());
        return Result.successOf(ResultCode.Success, map);
    }
}