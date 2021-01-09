package com.danbro.edu.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Classname TeacherPagingDto
 * @Description TODO 后台返回的分页数据
 * @Date 2021/1/9 13:13
 * @Created by Administrator
 */
@Data
@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OutPutPagingDto<T> {
    private Long total;
    private List<T> rows;
}
