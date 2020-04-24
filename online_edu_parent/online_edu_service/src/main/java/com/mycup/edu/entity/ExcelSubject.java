package com.mycup.edu.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author Garre
 * @create 2020-04-14 11:31
 */
@Data
public class ExcelSubject {
    @ExcelProperty(index = 0)
    private String parentSubjectName;
    @ExcelProperty(index = 1)
    private String childSubjectName;
}
