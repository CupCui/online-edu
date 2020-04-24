package com.mycup.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mycup.edu.entity.CourseDetailInfo;
import com.mycup.edu.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mycup.request.CourseInfoVo;
import com.mycup.request.QueryCourse;
import com.mycup.response.CourseConfirmVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author zhangqiang
 * @since 2020-04-15
 */
public interface EduCourseService extends IService<EduCourse> {

    void saveCourseInfo(CourseInfoVo courseInfoVo);

    void getCoursePageByCondition(Page<EduCourse> coursePage, QueryCourse queryCourse);

    CourseInfoVo getCourseById(String id);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    void deleteCourseInfo(String id);

    CourseConfirmVo getCourseConfirmInfo(String courseId);

    List<EduCourse> queryCourseByTeacherId(String teacherId);

    Map<String, Object> queryCourseByPage(Page<EduCourse> coursePage);

    CourseDetailInfo queryCourseDetailById(String courseId);
}
