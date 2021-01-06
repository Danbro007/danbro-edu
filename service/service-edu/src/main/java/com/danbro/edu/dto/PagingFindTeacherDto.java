package com.danbro.edu.dto;

import java.util.List;
import com.danbro.edu.entity.EduTeacher;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Builder
public class PagingFindTeacherDto {
    private List<EduTeacher> items;
    private Long current;
    private Long pages;
    private Long size;
    private Long total;
    private Boolean hasNext;
    private Boolean hasPrevious;
}
