package com.danbro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.entity.EduTeacher;
import com.danbro.exception.MyCustomException;
import com.danbro.mapper.EduTeacherMapper;
import com.danbro.service.EduTeacherService;
import com.danbro.dto.EduTeacherQueryDto;
import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
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
    public Result pagingFindTeacherByCondition(Integer current, Integer limit, EduTeacherQueryDto eduTeacherQueryDto) {
        Page<EduTeacher> eduTeacherPage = new Page<>(current, limit);
        LocalDateTime end = eduTeacherQueryDto.getEnd();
        LocalDateTime start = eduTeacherQueryDto.getStart();
        Integer level = eduTeacherQueryDto.getLevel();
        String name = eduTeacherQueryDto.getName();
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
        if (eduTeacherPage.getTotal() > 0){
            HashMap<String, Object> map = new HashMap<>(16);
            map.put("total", eduTeacherPage.getTotal());
            map.put("rows", eduTeacherPage.getRecords());
            return Result.successOf(ResultCode.Success, map);
        }
        throw new MyCustomException(ResultCode.MATCH_CONDITION_TEACHER_NOT_FOUND);
    }
}