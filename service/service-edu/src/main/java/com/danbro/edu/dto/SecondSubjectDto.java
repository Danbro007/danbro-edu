package com.danbro.edu.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Classname FirstSubject
 * @Description TODO
 * @Date 2020/12/18 21:46
 * @Author Danrbo
 */
@Data
@Accessors(chain = true)
public class SecondSubjectDto {
    private String id;
    private String title;
}
