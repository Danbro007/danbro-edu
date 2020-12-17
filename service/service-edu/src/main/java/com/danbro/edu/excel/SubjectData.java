package com.danbro.edu.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Classname SubjectData
 * @Description TODO 上传的课程 Excel 格式
 * @Date 2020/12/17 21:27
 * @Author Danrbo
 */
@Data
public class SubjectData {

    @ExcelProperty(index = 0)
    private String firstSubject;
    @ExcelProperty(index = 1)
    private String secondSubject;
}
