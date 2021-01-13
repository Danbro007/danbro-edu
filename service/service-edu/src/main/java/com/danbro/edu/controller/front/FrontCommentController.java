package com.danbro.edu.controller.front;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import com.danbro.dto.UserInfoDto;
import com.danbro.edu.dto.FrontCourseCommentDto;
import com.danbro.edu.dto.FrontInPutCommentInsertDto;
import com.danbro.edu.dto.FrontPagingDto;
import com.danbro.edu.service.EduCommentService;
import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import com.danbro.exception.MyCustomException;
import com.danbro.utils.JwtUtils;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

/**
 * @Classname FrontCommentController
 * @Description TODO
 * @Date 2021/1/7 20:57
 * @Created by Administrator
 */
@Data
@RestController

@RequestMapping("edu/front/comment")
public class FrontCommentController {
    @Resource
    EduCommentService eduCommentService;

    @ApiOperation("分页查询课程的评论")
    @GetMapping("{courseId}/{current}/{limit}")
    public Result<FrontPagingDto<FrontCourseCommentDto>> pagingGetCourseComment(@PathVariable String courseId,
                                         @PathVariable Long current,
                                         @PathVariable Long limit) {
        FrontPagingDto<FrontCourseCommentDto> commentList = eduCommentService.pagingGetCourseComment(courseId, current, limit);
        return Result.ofSuccess(commentList);
    }

    @ApiOperation("添加课程评论")
    @PostMapping("")
    public Result insertComment(@RequestBody FrontInPutCommentInsertDto courseCommentDto, HttpServletRequest request) {
        UserInfoDto userInfo = JwtUtils.getMemberIdByJwtToken(request);
        if (userInfo == null) {
            throw new MyCustomException(ResultCode.USER_NO_LOGIN);
        }
        courseCommentDto.
                setNickname(userInfo.getNickname()).
                setAvatar(userInfo.getAvatar()).
                setMemberId(userInfo.getId());
        Boolean b = eduCommentService.insertCourseComment(courseCommentDto);
        if (b) {
            return Result.ofSuccess();
        }
        return Result.ofFail(ResultCode.INSERT_COMMENT_FAILURE);
    }

}
