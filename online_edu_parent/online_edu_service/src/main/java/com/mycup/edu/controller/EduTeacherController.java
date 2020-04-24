package com.mycup.edu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mycup.response.RetVal;
import com.mycup.edu.entity.EduTeacher;
import com.mycup.edu.service.EduTeacherService;
import com.mycup.request.TeacherCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author zhangqiang
 * @since 2020-04-10
 */
@RestController
@RequestMapping("/edu/teacher")
@CrossOrigin
public class EduTeacherController {
    @Autowired
    private EduTeacherService teacherService;

    // 1.查询所有讲师
    @GetMapping
    public RetVal getAllTeacher() {
        List<EduTeacher> teacherList = teacherService.list(null);
        return RetVal.success().data("teacherList", teacherList);
    }

    // 2.删除讲师功能
    @DeleteMapping("{id}")
    public RetVal deleteTeacherById(@PathVariable("id") String id) {
        boolean result = teacherService.removeById(id);
        if (!result) {
            return RetVal.error();
        }
        return RetVal.success();
    }

    // 3.分页查询
    @GetMapping("queryTeacherPage/{pageNum}/{pageSize}")
    public RetVal queryTeacherPage(@PathVariable("pageNum") long pageNum,
                                   @PathVariable("pageSize") long pageSize) {
        Page<EduTeacher> teacherPage = new Page<>(pageNum, pageSize);
        teacherService.page(teacherPage, null);

        long totalPage = teacherPage.getTotal();
        List<EduTeacher> teacherList = teacherPage.getRecords();
        return RetVal.success().data("totalPage", totalPage).data("teacherList", teacherList);
    }

    // 4.分页查询 带条件
    @GetMapping("queryTeacherPageByCondition/{pageNum}/{pageSize}")
    public RetVal queryTeacherPageByCondition(@PathVariable("pageNum") long pageNum,
                                   @PathVariable("pageSize") long pageSize,
                                   TeacherCondition teacherCondition) {
        Page<EduTeacher> teacherPage = new Page<>(pageNum, pageSize);

        teacherService.queryTeacherPageByCondition(teacherPage, teacherCondition);

        long totalPage = teacherPage.getTotal();
        List<EduTeacher> teacherList = teacherPage.getRecords();
        return RetVal.success().data("totalPage", totalPage).data("teacherList", teacherList);
    }

    // 5.添加讲师
    @PostMapping
    public RetVal saveTeacher(EduTeacher teacher) {
//    public RetVal saveTeacher(@RequestBody EduTeacher teacher) {
        boolean result = teacherService.save(teacher);
        if (!result) {
            return RetVal.error();
        }
        return RetVal.success();
    }

    // 6.根据id查询讲师
    @GetMapping("{id}")
    public RetVal queryTeacherById(@PathVariable("id") String id) {
        EduTeacher teacher = teacherService.getById(id);
        return RetVal.success().data("teacher", teacher);
    }

    // 7.更新讲师
    @PutMapping
    public RetVal updateTeacher(EduTeacher teacher) {

        boolean result = teacherService.updateById(teacher);
        if (!result) {
            return RetVal.error();
        }
        return RetVal.success();
    }


}

