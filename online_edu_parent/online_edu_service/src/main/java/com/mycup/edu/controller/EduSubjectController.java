package com.mycup.edu.controller;


import com.mycup.response.RetVal;
import com.mycup.edu.entity.EduSubject;
import com.mycup.edu.service.EduSubjectService;
import com.mycup.response.SubjectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author zhangqiang
 * @since 2020-04-14
 */
@RestController
@RequestMapping("/edu/subject")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;

    // 1.导入课程到数据库
    @PostMapping("uploadSubject")
    public RetVal uploadSubject(@RequestParam("file") MultipartFile file) throws Exception {
        subjectService.uploadSubject(file);
        return RetVal.success();
    }

    // 2.查询所有的课程信息
    @GetMapping("getAllSubject")
    public RetVal getAllSubject() {
        List<SubjectVo> allSubjects =  subjectService.getAllSubject();
        return RetVal.success().data("allSubjects", allSubjects);
    }

    // 3.删除课程分类
    @DeleteMapping("/{id}")
    public RetVal deleteSubjectById(@PathVariable("id") String id) {
        boolean result = subjectService.deleteSubjectById(id);
        if (!result) {
            return RetVal.error();
        }
        return RetVal.success();
    }

    // 3.添加课程一级分类
    @PostMapping("saveParentSubject")
    public RetVal saveParentSubject(EduSubject subject) {
        boolean result = subjectService.saveParentSubject(subject);
        if (!result) {
            return RetVal.error();
        }
        return RetVal.success();
    }

    // 3.添加课程二级分类
    @PostMapping("saveChildSubject")
    public RetVal saveChildSubject(EduSubject subject) {
        boolean result = subjectService.saveChildSubject(subject);
        if (!result) {
            return RetVal.error();
        }
        return RetVal.success();
    }
}

