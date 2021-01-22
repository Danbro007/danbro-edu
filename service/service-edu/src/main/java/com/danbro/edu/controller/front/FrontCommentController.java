package com.danbro.edu.controller.front;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.danbro.anotation.IsAssignID;
import com.danbro.anotation.IsPositiveNum;
import com.danbro.anotation.ValidParam;
import com.danbro.dto.UserInfoDto;
import com.danbro.edu.controller.vo.CourseCommentVo;
import com.danbro.edu.controller.param.CourseCommentParam;
import com.danbro.edu.controller.dto.FrontPagingDto;
import com.danbro.edu.service.EduCommentService;
import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import com.danbro.exception.MyCustomException;
import com.danbro.impl.Insert;
import com.danbro.utils.JwtUtils;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Classname FrontCommentController
 * @Description TODO
 * @Date 2021/1/7 20:57
 * @Created by Administrator
 */
@Data
@RestController
@Validated
@RequestMapping("edu/front/comment")
public class FrontCommentController {
    @Resource
    EduCommentService eduCommentService;

    @ApiOperation("分页查询课程的评论")
    @GetMapping("{courseId}/{current}/{limit}")
    public Result<FrontPagingDto<CourseCommentVo>> pagingGetCourseComment(@IsAssignID @PathVariable String courseId,
                                                                          @IsPositiveNum(message = "当前页数不合法！") @PathVariable Long current,
                                                                          @IsPositiveNum(message = "每页显示数不合法！") @PathVariable Long limit) {
        return Result.ofSuccess(eduCommentService.pagingGetCourseComment(courseId, current, limit));
    }

    @ValidParam
    @ApiOperation("添加课程评论")
    @PostMapping("")
    public Result<CourseCommentVo> insertComment(@Validated(Insert.class) @RequestBody CourseCommentParam commentParam,
                                                 HttpServletRequest request,
                                                 BindingResult result) {
        UserInfoDto userInfo = JwtUtils.getMemberIdByJwtToken(request);
        if (userInfo == null) {
            throw new MyCustomException(ResultCode.USER_NO_LOGIN);
        }
        commentParam.
                setNickname(userInfo.getNickname()).
                setAvatar(userInfo.getAvatar()).
                setMemberId(userInfo.getId());
        return Result.ofSuccess(new CourseCommentVo().convertFrom(eduCommentService.insertOrUpdateCourseComment(commentParam.convertTo())));
    }
}
