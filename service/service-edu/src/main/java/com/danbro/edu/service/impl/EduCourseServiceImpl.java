package com.danbro.edu.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.danbro.dto.CourseTopDto;
import com.danbro.dto.FrontCourseDetailInfoDto;
import com.danbro.edu.controller.dto.FrontCourseConditionPagingDto;
import com.danbro.edu.controller.dto.FrontPagingDto;
import com.danbro.edu.controller.param.QueryCourseParam;
import com.danbro.edu.controller.param.CourseParam;
import com.danbro.edu.controller.vo.CoursePublishVo;
import com.danbro.edu.controller.vo.CourseVo;
import com.danbro.edu.entity.EduCourse;
import com.danbro.edu.entity.EduCourseDescription;
import com.danbro.edu.mapper.EduCourseMapper;
import com.danbro.edu.service.EduChapterService;
import com.danbro.edu.service.EduCourseDescriptionService;
import com.danbro.edu.service.EduCourseService;
import com.danbro.edu.service.EduTeacherService;
import com.danbro.edu.service.EduVideoService;
import com.danbro.edu.utils.SortType;
import com.danbro.enity.OutPutPagingDto;
import com.danbro.enums.ResultCode;
import com.danbro.exception.MyCustomException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * 课程(EduCourse)表服务实现类
 *
 * @author makejava
 * @since 2020-12-20 19:55:16
 */
@Service("eduCourseService")
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    EduCourseDescriptionService eduCourseDescriptionService;
    @Autowired
    EduChapterService eduChapterService;
    @Autowired
    EduVideoService eduVideoService;
    @Autowired
    EduTeacherService eduTeacherService;

    @Override
    public EduCourse insert(CourseParam courseParam) {
        EduCourse eduCourse = new EduCourse();
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        BeanUtils.copyProperties(courseParam, eduCourse);
        boolean f = this.save(eduCourse);
        if (!f) {
            throw new MyCustomException(ResultCode.INSERT_COURSE_FAILURE);
        }
        eduCourseDescription.
                setDescription(courseParam.getDescription()).
                setId(eduCourse.getId());
        boolean b = eduCourseDescriptionService.save(eduCourseDescription);
        if (!b) {
            throw new MyCustomException(ResultCode.INSERT_COURSE_DESCRIPTION_FAILURE);
        }
        return eduCourse;
    }

    @Override
    public CourseVo getCourseBasicInfo(String courseId) {
        return this.baseMapper.getCourseBasicInfoByCourseId(courseId);
    }


    @Override
    public CoursePublishVo getCourseInfoForPublish(String courseId) {
        return baseMapper.getCourseInfoForPublish(courseId);
    }

    @Override
    public OutPutPagingDto<EduCourse> pagingFindByCondition(Integer current, Integer limit, QueryCourseParam conditionDto) {
        Page<EduCourse> page = new Page<>(current, limit);
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        if (conditionDto != null) {
            if (!StringUtils.isEmpty(conditionDto.getStatus())) {
                queryWrapper.eq("status", conditionDto.getStatus());
            }
            if (!StringUtils.isEmpty(conditionDto.getTitle())) {
                queryWrapper.like("title", conditionDto.getTitle());
            }
        }
        queryWrapper.orderByDesc("gmt_create");
        return new OutPutPagingDto<EduCourse>()
                .setTotal(page.getTotal())
                .setRows(page.getRecords());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean removeCourse(String id) {
        return false;
//        if (eduChapterService.removeChapterAndVideoByCourseId(id) &&
//                eduVideoService.removeByCourseId(id) &&
//                eduCourseDescriptionService.removeById(id) &&
//                this.removeById(id)) {
//            return true;
//        } else {
//            throw new MyCustomException(ResultCode.DELETE_COURSE_FAILURE);
//        }
    }

    @Cacheable(value = "course", key = "'top-course-list'")
    @Override
    public List<CourseTopDto> getTopCourseList(String limit) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("view_count");
        queryWrapper.last(String.format("limit %s", limit));
        List<CourseTopDto> topDtos = new ArrayList<>();
        this.list(queryWrapper).forEach(e -> {
            CourseTopDto topDto = new CourseTopDto();
            BeanUtils.copyProperties(e, topDto);
            topDtos.add(topDto);
        });
        return topDtos;
    }

    @Override
    public FrontPagingDto<EduCourse> pagingFindCourseByCondition(Long current, Long limit, FrontCourseConditionPagingDto dto) {
        Page<EduCourse> page = new Page<>(current, limit);
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(dto.getSubjectParentId())) {
            queryWrapper.eq("subject_parent_id", dto.getSubjectParentId());
        }
        if (!StringUtils.isEmpty(dto.getSubjectId())) {
            queryWrapper.eq("subject_id", dto.getSubjectId());
        }
        if (!StringUtils.isEmpty(dto.getSortType())) {
            if ((dto.getSortType().equals(SortType.SALE_SORT.getType()) ||
                    dto.getSortType().equals(SortType.PRICE_SORT.getType()) ||
                    dto.getSortType().equals(SortType.CREATE_TIME_SORT.getType()))) {
                queryWrapper.orderByDesc(dto.getSortType());
            }
        }
        this.page(page, queryWrapper);
        return new FrontPagingDto<EduCourse>().
                setPages(page.getPages()).
                setCurrent(page.getCurrent()).
                setSize(page.getSize()).
                setHasPrevious(page.hasPrevious()).
                setHasNext(page.hasNext()).
                setItems(page.getRecords()).
                setTotal(page.getTotal());
    }

    @Override
    public FrontCourseDetailInfoDto getCourseDetailInfo(String courseId) {
        return this.baseMapper.getCourseDetailInfo(courseId);
    }

    @Override
    public EduCourse insertOrUpdateCourse(EduCourse eduCourse) {
        this.saveOrUpdate(eduCourse);
        return eduCourse;
    }
}