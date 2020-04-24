package com.mycup;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author Garre
 * @create 2020-04-14 11:00
 */
@Data
public class Student {
    @ExcelProperty(value = "学生编号", index = 0)
    private Integer stuId;
    @ExcelProperty(value = "学生姓名", index = 1)
    private String stuName;
}
