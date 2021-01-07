package com.danbro.edu.controller.front;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import com.danbro.edu.dto.FrontCourseCommentPagingDto;
import com.danbro.edu.dto.FrontInsertCourseCommentDto;
import com.danbro.edu.dto.FrontUcenterMemberDto;
import com.danbro.edu.rpcClient.UCenterUserClient;
import com.danbro.edu.service.EduCommentService;
import com.danbro.enums.Result;
import com.danbro.enums.ResultCode;
import com.danbro.utils.JwtUtils;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Classname FrontCommentController
 * @Description TODO
 * @Date 2021/1/7 20:57
 * @Created by Administrator
 */
@Data
@RestController
@CrossOrigin
@RequestMapping("edu/front/comment")
public class FrontCommentController {
    @Resource
    EduCommentService eduCommentService;

    @Autowired
    UCenterUserClient userClient;

    @ApiOperation("分页查询课程的评论")
    @GetMapping("{courseId}/{current}/{limit}")
    public Result pagingGetCourseComment(@PathVariable String courseId,
                                         @PathVariable Long current,
                                         @PathVariable Long limit) {
        FrontCourseCommentPagingDto commentPagingDto = eduCommentService.pagingGetCourseComment(courseId, current, limit);
        return Result.successOf("commentInfo", commentPagingDto);
    }

    @ApiOperation("添加课程评论")
    @PostMapping("")
    public Result insertComment(@RequestBody FrontInsertCourseCommentDto courseCommentDto, HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        Result result = userClient.getUserInfo(memberId);
        FrontUcenterMemberDto userInfo = (FrontUcenterMemberDto) result.getData().get("userInfo");
        courseCommentDto.
                setMemberId(userInfo.getId()).
                setAvatar(userInfo.getAvatar()).
                setNickName(userInfo.getNickname());
        Boolean b = eduCommentService.insertCourseComment(courseCommentDto);
        if (b) {
            return Result.successOf();
        }
        return Result.failureOf(ResultCode.INSERT_COMMENT_FAILURE);
    }

}
