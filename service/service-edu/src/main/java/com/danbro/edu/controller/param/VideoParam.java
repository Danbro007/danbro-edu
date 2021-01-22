package com.danbro.edu.controller.param;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import com.danbro.anotation.IsAssignID;
import com.danbro.anotation.IsBool;
import com.danbro.anotation.IsPositiveNum;
import com.danbro.edu.entity.EduVideo;
import com.danbro.impl.Insert;
import com.danbro.impl.ParamConvert;
import com.danbro.impl.Update;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * @Classname EduVideoInsertInPutDto
 * @Description TODO 添加或者添加视频的参数
 * @Date 2020/12/29 12:17
 * @Author Danrbo
 */
@Data
@ApiModel("添加或者修改小节")
public class VideoParam implements ParamConvert<EduVideo> {
    @IsAssignID(message = "修改小节时ID必须存在并且合法！", groups = {Update.class})
    @Null(message = "创建小节时ID不能存在！", groups = {Insert.class})
    @ApiModelProperty("小节ID")
    private String id;

    @IsAssignID(message = "课程ID必须存在并且合法！", groups = {Update.class, Insert.class})
    @ApiModelProperty("课程ID")
    private String courseId;

    @IsAssignID(message = "章节ID必须存在并且合法！", groups = {Update.class, Insert.class})
    @ApiModelProperty("章节ID")
    private String chapterId;

    @ApiModelProperty("小节标题")
    private String title;

    @IsPositiveNum(message = "小节排序必须存在并且合法！", groups = {Update.class, Insert.class})
    @ApiModelProperty("小节排序")
    private String sort;

    @IsBool(groups = {Insert.class, Update.class})
    @ApiModelProperty("小节是否免费")
    private String isFree;

    @NotBlank(message = "视频名称不能为空！", groups = {Update.class, Insert.class})
    @ApiModelProperty("小节里的视频名")
    private String videoOriginalName;

    @NotBlank(message = "视频源ID不能为空！", groups = {Update.class, Insert.class})
    @ApiModelProperty("小节里的视频在阿里云点播平台的ID")
    private String videoSourceId;

    @Override
    public EduVideo convertTo() {
        EduVideo eduVideo = new EduVideo();
        BeanUtils.copyProperties(this, eduVideo);
        eduVideo.setSort(Integer.parseInt(this.getSort()));
        eduVideo.setIsFree(Boolean.getBoolean(this.getIsFree()));
        return eduVideo;
    }

}
