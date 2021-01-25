package com.danbro.edu.controller;

import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import com.danbro.edu.controller.vo.FirstSubjectVo;
import com.danbro.edu.service.EduSubjectService;
import com.danbro.enums.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 课程科目(EduSubject)表控制层
 *
 * @author makejava
 * @since 2020-12-17 16:59:16
 */

@RestController
@RequestMapping("edu")
public class EduSubjectController {
    /**
     * 服务对象
     */
    @Resource
    private EduSubjectService eduSubjectService;

    @ApiOperation("上传Excel格式的课程")
    @PostMapping("subject")
    public Result importSubject(@RequestParam("file") MultipartFile file) throws IOException {
        eduSubjectService.importSubject(file);
        return Result.ofSuccess();
    }

    @ApiOperation("获取课程列表并且按照级别分类")
    @GetMapping("subject/list")
    public Result<List<FirstSubjectVo>> getAllSubject() {
        return Result.ofSuccess(eduSubjectService.getAllSubject());
    }
}