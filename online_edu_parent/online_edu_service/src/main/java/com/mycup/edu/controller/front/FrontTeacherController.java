package com.mycup.edu.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mycup.edu.entity.EduCourse;
import com.mycup.edu.entity.EduTeacher;
import com.mycup.edu.service.EduCourseService;
import com.mycup.edu.service.EduTeacherService;
import com.mycup.response.RetVal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Garre
 * @create 2020-04-22 11:44
 */
@RestController
@RequestMapping("/edu/front/teacher")
@CrossOrigin
public class FrontTeacherController {

    @Autowired
    private EduTeacherService teacherService;

    @Autowired
    private EduCourseService courseService;

    // 查询讲师列表
    @GetMapping("queryTeacherByPage/{pageNum}/{pageSize}")
    public RetVal queryTeacherByPage(@PathVariable("pageNum") Long pageNum,
                                     @PathVariable("pageSize") Long pageSize) {
        Page<EduTeacher> teacherPage = new Page<>(pageNum, pageSize);
        Map<String, Object> pageMap = teacherService.queryTeacherByPage(teacherPage);
        return RetVal.success().data(pageMap);
    }

    // 根据讲师id查询讲师信息
    @GetMapping("queryTeacherDetailById/{teacherId}")
    public RetVal queryTeacherDetailById(@PathVariable("teacherId") String teacherId) {
        // 查询讲师的基本信息
        EduTeacher teacher = teacherService.getById(teacherId);

        List<EduCourse> courseList = courseService.queryCourseByTeacherId(teacherId);
        return RetVal.success().data("teacher", teacher).data("courseList", courseList);
    }
}
