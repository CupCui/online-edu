package com.mycup.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.mycup.edu.entity.EduSubject;
import com.mycup.edu.entity.ExcelSubject;
import com.mycup.edu.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Garre
 * @create 2020-04-14 11:33
 */
@Component
public class SubjectListener extends AnalysisEventListener<ExcelSubject> {

    @Autowired
    private EduSubjectService subjectService;

    // 1.一行一行读取excel
    @Override
    public void invoke(ExcelSubject excelSubject, AnalysisContext context) {
        // 把数据写到数据库

        // 1.一级课程分类
        EduSubject parentSubject = subjectService.existSubject("0", excelSubject.getParentSubjectName());
        // 如果为空，说明数据库中没有该课程
        if (parentSubject == null) {
            parentSubject = new EduSubject();
            parentSubject.setParentId("0");
            parentSubject.setTitle(excelSubject.getParentSubjectName());
            // 保存到数据库中
            subjectService.save(parentSubject);
        }

        // 2.二级分类
        String parentId = parentSubject.getId();
        EduSubject childSubject = subjectService.existSubject(parentId, excelSubject.getChildSubjectName());
        // 如果为空，说明数据库中没有该课程
        if (childSubject == null) {
            childSubject = new EduSubject();
            childSubject.setParentId(parentId);
            childSubject.setTitle(excelSubject.getChildSubjectName());
            // 保存到数据库中
            subjectService.save(childSubject);
        }

    }

    // 2.读取完成后
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        System.out.println("读取完成后");
    }
}
