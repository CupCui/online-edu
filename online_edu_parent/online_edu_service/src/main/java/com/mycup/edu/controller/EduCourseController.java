package com.mycup.edu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mycup.edu.entity.EduCourse;
import com.mycup.edu.service.EduCourseService;
import com.mycup.request.CourseInfoVo;
import com.mycup.request.QueryCourse;
import com.mycup.response.CourseConfirmVo;
import com.mycup.response.RetVal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author zhangqiang
 * @since 2020-04-15
 */
@RestController
@RequestMapping("/edu/course")
@CrossOrigin
public class EduCourseController {
    @Autowired
    private EduCourseService courseService;

    // 保存课程信息
    @PostMapping("saveCourseInfo")
    public RetVal saveCourseInfo(CourseInfoVo courseInfoVo) {
        courseService.saveCourseInfo(courseInfoVo);
        return RetVal.success();
    }

    // 课程管理之列表查询
    @PostMapping("getCoursePageByCondition/{pageNum}/{pageSize}")
    public RetVal getCoursePageByCondition(@PathVariable("pageNum") long pageNum,
                                           @PathVariable("pageSize") long pageSize,
                                           QueryCourse queryCourse) {
        Page<EduCourse> coursePage = new Page<>();
        courseService.getCoursePageByCondition(coursePage, queryCourse);
        long total = coursePage.getTotal();
        List<EduCourse> courseList = coursePage.getRecords();
        return RetVal.success().data("total", total).data("courseList", courseList);
    }

    // 根据课程id查询课程
    @GetMapping("{id}")
    public RetVal getCourseById(@PathVariable("id") String id) {
        CourseInfoVo courseInfo = courseService.getCourseById(id);
        return RetVal.success().data("courseInfo", courseInfo);
    }

    // 更改课程信息
    @PostMapping("updateCourseInfo")
    public RetVal updateCourseInfo(CourseInfoVo courseInfoVo) {
        courseService.updateCourseInfo(courseInfoVo);
        return RetVal.success();
    }

    // 删除课程信息
    @DeleteMapping("{id}")
    public RetVal deleteCourseInfo(@PathVariable("id") String id) {
        courseService.deleteCourseInfo(id);
        return RetVal.success();
    }

    // 课程发布信息确认
    @GetMapping("getCourseConfirmInfo/{courseId}")
    public RetVal getCourseConfirmInfo(@PathVariable String courseId) {
        CourseConfirmVo courseConfirm = courseService.getCourseConfirmInfo(courseId);
        return RetVal.success().data("courseConfirm", courseConfirm);
    }

    // 课程发布
    @GetMapping("publishCourse/{courseId}")
    public RetVal publishCourse(@PathVariable String courseId) {
        EduCourse course = new EduCourse();
        course.setId(courseId);
        course.setStatus("Normal");
        courseService.updateById(course);
        return RetVal.success();
    }



}

