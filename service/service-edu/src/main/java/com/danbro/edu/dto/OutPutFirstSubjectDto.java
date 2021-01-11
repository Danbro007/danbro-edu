package com.danbro.edu.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Classname FirstSubject
 * @Description TODO 返回一级既下属的二级分类
 * @Date 2020/12/18 21:46
 * @Author Danrbo
 */
@Data
@Accessors(chain = true)
public class OutPutFirstSubjectDto {
    private String id;
    private String title;
    private List<OutPutSecondSubjectDto> children;

    public OutPutFirstSubjectDto() {
        children = new ArrayList<>();
    }

}
