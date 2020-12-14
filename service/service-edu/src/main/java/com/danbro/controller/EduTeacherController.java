package com.danbro.controller;

import com.danbro.entity.EduTeacher;
import com.danbro.service.EduTeacherService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 讲师(EduTeacher)表控制层
 *
 * @author makejava
 * @since 2020-12-14 15:11:47
 */
@RestController
@RequestMapping("eduTeacher")
public class EduTeacherController {
    /**
     * 服务对象
     */
    @Resource
    private EduTeacherService eduTeacherService;

    /**
     * 查询多条教师数据
     *
     * @return 多条数据
     */
    @GetMapping("/findAll")
    public List<EduTeacher> findAll(){
        return eduTeacherService.list();
    }

    @DeleteMapping("{id}")
    public boolean deleteTeacherById(@PathVariable String id){
        if (id!=null && !StringUtils.isEmpty(id)){
            return eduTeacherService.removeById(id);
        }
        return false;
    }


    @GetMapping("/findOne/{id}")
    public EduTeacher findOne(@PathVariable String id){
        if (id!=null && !StringUtils.isEmpty(id)){
            return eduTeacherService.getById(id);
        }
        return null;
    }

}