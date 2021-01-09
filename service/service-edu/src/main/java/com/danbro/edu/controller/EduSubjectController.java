package com.danbro.edu.controller;

import java.util.List;
import javax.annotation.Resource;
import com.danbro.edu.dto.OutPutFirstSubjectDto;
import com.danbro.edu.service.EduSubjectService;
import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import com.danbro.exception.MyCustomException;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin
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
    public Result insertSubject(@RequestParam("file") MultipartFile file) {
        try {
            eduSubjectService.insert(file);
        } catch (Exception e) {
            throw new MyCustomException(ResultCode.SUBJECT_UPLOAD_FAILURE);
        }
        return Result.ofSuccess();
    }

    @ApiOperation("获取课程列表并且按照级别分类")
    @GetMapping("subject")
    public Result<List<OutPutFirstSubjectDto>> getAllSubject() {
        List<OutPutFirstSubjectDto> subjectList = eduSubjectService.getAllSubject();
        return Result.ofSuccess(subjectList);
    }
}