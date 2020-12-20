package com.danbro.edu.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname FirstSubject
 * @Description TODO
 * @Date 2020/12/18 21:46
 * @Author Danrbo
 */
@Data
@Accessors(chain = true)
public class FirstSubjectDto {
    private String id;
    private String title;
    private List<SecondSubjectDto> children;

    public FirstSubjectDto() {
        children = new ArrayList<>();
    }
}
