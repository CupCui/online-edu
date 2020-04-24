package com.mycup.edu.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mycup.edu.entity.CourseDetailInfo;
import com.mycup.edu.entity.EduCourse;
import com.mycup.edu.entity.EduTeacher;
import com.mycup.edu.service.EduChapterService;
import com.mycup.edu.service.EduCourseService;
import com.mycup.response.ChapterVo;
import com.mycup.response.RetVal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Garre
 * @create 2020-04-22 15:15
 */
@RestController
@RequestMapping("/edu/front/course")
@CrossOrigin
public class FrontCourseController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduChapterService chapterService;

    // 查询课程列表
    @GetMapping("queryCourseByPage/{pageNum}/{pageSize}")
    public RetVal queryCourseByPage(@PathVariable("pageNum") Long pageNum,
                                     @PathVariable("pageSize") Long pageSize) {
        Page<EduCourse> coursePage = new Page<>(pageNum, pageSize);
        Map<String, Object> pageMap = courseService.queryCourseByPage(coursePage);
        return RetVal.success().data(pageMap);
    }

    // 查询课程详细信息
    @GetMapping("queryCourseDetailById/{courseId}")
    public RetVal queryCourseDetailById(@PathVariable("courseId") String courseId) {
        // 根据课程id查询章节和小节信息
        List<ChapterVo> chapterAndSection = chapterService.getChapterAndSection(courseId);
        // 获取其他4张表中数据信息
        CourseDetailInfo courseDetailInfo =  courseService.queryCourseDetailById(courseId);
        return RetVal.success()
                .data("chapterAndSection", chapterAndSection)
                .data("courseDetailInfo", courseDetailInfo);
    }
}
