package com.mycup;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @author Garre
 * @create 2020-04-14 11:04
 */
public class StudentListener extends AnalysisEventListener<Student> {
    // 一行一行读Excel数据
    @Override
    public void invoke(Student student, AnalysisContext context) {
        System.out.println("student" + student);
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头" + headMap);
    }

    // 读取excel完成执行
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println("读取完成");
    }
}
