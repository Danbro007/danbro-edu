package com.danbro.edu.controller.vo;

import java.io.Serializable;
import java.util.Date;
import com.danbro.edu.entity.EduSubject;
import com.danbro.impl.VoConvert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

/**
 * @Classname FirstSubject
 * @Description TODO 返回二级分类的数据
 * @Date 2020/12/18 21:46
 * @Author Danrbo
 */

@Data
@ApiModel("返回给前端的二级课程分类")
@Accessors(chain = true)
public class SecondSubjectVo implements Serializable, VoConvert<SecondSubjectVo, EduSubject> {
    @ApiModelProperty("二级课程分类ID")
    private String id;

    @ApiModelProperty("课程名")
    private String title;

    @ApiModelProperty("一级课程名")
    private String parentId;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("创建时间")
    private Date gmtCreate;

    @ApiModelProperty("修改时间")
    private Date gmtModified;

    @Override
    public SecondSubjectVo convertFrom(EduSubject eduSubject) {
        SecondSubjectVo secondSubjectVo = new SecondSubjectVo();
        BeanUtils.copyProperties(secondSubjectVo, eduSubject);
        return secondSubjectVo;
    }
}