package com.danbro.edu.controller.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.danbro.edu.controller.dto.OutPutEduVideoDto;
import com.danbro.edu.entity.EduChapter;
import com.danbro.impl.VoConvert;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Classname ChapterVo
 * @Description TODO 返回给前端的章节信息
 * @Date 2021/1/19 14:46
 * @Author Danrbo
 */
@Data
public class ChapterVo implements Serializable, VoConvert<ChapterVo, EduChapter> {
    private static final long serialVersionUID = -6114772300795049483L;

    @ApiModelProperty("章节ID")
    private String id;

    @ApiModelProperty("课程ID")
    private String courseId;

    @ApiModelProperty("章节名")
    private String title;

    @ApiModelProperty("章节排序")
    private Integer sort;

    @ApiModelProperty("创建章节的时间")
    private Date gmtCreate;

    @ApiModelProperty("修改章节的时间")
    private Date gmtModified;

    @ApiModelProperty("当前章节所属的所有列表")
    private List<VideoVo> children = new ArrayList<>();

    @Override
    public ChapterVo convertFrom(EduChapter eduChapter) {
        BeanUtils.copyProperties(eduChapter, this);
        return this;
    }
}
