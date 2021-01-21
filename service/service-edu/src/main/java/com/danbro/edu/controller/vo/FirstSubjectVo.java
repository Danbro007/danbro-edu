package com.danbro.edu.controller.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.danbro.edu.entity.EduSubject;
import com.danbro.impl.VoConvert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

/**
 * @Classname FirstSubject
 * @Description TODO 返回一级既下属的二级分类
 * @Date 2020/12/18 21:46
 * @Author Danrbo
 */
@Data
@ApiModel("返回给前端的一级课程分类")
@Accessors(chain = true)
public class FirstSubjectVo implements Serializable, VoConvert<FirstSubjectVo, EduSubject> {
    @ApiModelProperty("一级课程分类ID")
    private String id;

    @ApiModelProperty("课程名")
    private String title;

    @ApiModelProperty("一级课程名")
    private String parentId;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("二级课程分类列表")
    private List<SecondSubjectVo> children;

    @ApiModelProperty("创建时间")
    private Date gmtCreate;

    @ApiModelProperty("修改时间")
    private Date gmtModified;

    public FirstSubjectVo() {
        children = new ArrayList<>();
    }

    @Override
    public FirstSubjectVo convertFrom(EduSubject eduSubject) {
        FirstSubjectVo firstSubjectVo = new FirstSubjectVo();
        BeanUtils.copyProperties(firstSubjectVo, eduSubject);
        return firstSubjectVo;
    }
}
