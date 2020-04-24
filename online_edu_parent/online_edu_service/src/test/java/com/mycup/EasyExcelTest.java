package com.mycup;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author Garre
 * @create 2020-04-14 11:01
 */

public class EasyExcelTest {
    // 1.写入Excel
    @Test
    public void testWriteExcel(){
        String fileName = "F:\\01.xlsx";
        ArrayList<Student> students = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student student = new Student();
            student.setStuId(i);
            student.setStuName("Adams" + i);
            students.add(student);
        }
        EasyExcel.write(fileName, Student.class).sheet("学生列表").doWrite(students);


    }

    // 2.读Excel
    @Test
    public void testReadExcel() {
        String fileName = "F:\\01.xlsx";
        //
        EasyExcel.read(fileName, Student.class, new StudentListener()).doReadAll();
    }
}
