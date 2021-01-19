package com.danbro.edu.controller.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Classname FirstSubject
 * @Description TODO 返回二级分类的数据
 * @Date 2020/12/18 21:46
 * @Author Danrbo
 */
@Data
@Accessors(chain = true)
public class OutPutSecondSubjectDto {
    private String id;
    private String title;
}
