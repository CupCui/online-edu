package com.mycup.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mycup.edu.entity.CourseDetailInfo;
import com.mycup.edu.entity.EduCourse;
import com.mycup.edu.entity.EduCourseDescription;
import com.mycup.edu.entity.EduTeacher;
import com.mycup.edu.handler.EduException;
import com.mycup.edu.mapper.EduCourseMapper;
import com.mycup.edu.service.EduChapterService;
import com.mycup.edu.service.EduCourseDescriptionService;
import com.mycup.edu.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mycup.edu.service.EduSectionService;
import com.mycup.request.CourseInfoVo;
import com.mycup.request.QueryCourse;
import com.mycup.response.CourseConfirmVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author zhangqiang
 * @since 2020-04-15
 */
@Primary
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService descriptionService;

    @Autowired
    private EduChapterService chapterService;

    @Autowired
    private EduSectionService sectionService;

    @Override
    public void saveCourseInfo(CourseInfoVo courseInfoVo) {
        // 保存课程信息
        EduCourse course = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, course);
        baseMapper.insert(course);

        // 保存描述信息
        String id = course.getId();
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setId(id);
        courseDescription.setDescription(courseInfoVo.getDescription());
        descriptionService.save(courseDescription);

    }

    @Override
    public void getCoursePageByCondition(Page<EduCourse> coursePage, QueryCourse queryCourse) {
        String title = queryCourse.getTitle();
        String status = queryCourse.getStatus();
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(title)) {
            wrapper.eq("title", title);
        }
        if (StringUtils.isNotEmpty(status)) {
            wrapper.eq("status", status);
        }
        baseMapper.selectPage(coursePage, wrapper);
        // TODO
    }

    @Override
    public CourseInfoVo getCourseById(String id) {
        EduCourse course = baseMapper.selectById(id);
        EduCourseDescription courseDescription = descriptionService.getById(id);

        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(course, courseInfoVo);
        if (courseDescription != null) {
            courseInfoVo.setDescription(courseDescription.getDescription());
        }
        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        // 修改课程基本信息
        EduCourse course = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, course);
        baseMapper.updateById(course);

        // 修改课程描述信息
        EduCourseDescription description = new EduCourseDescription();
        description.setId(courseInfoVo.getId());
        description.setDescription(courseInfoVo.getDescription());
        descriptionService.updateById(description);

    }

    @Override
    public void deleteCourseInfo(String id) {
        // 删除章节
        chapterService.deleteChapterByCourseId(id);
        // 删除小节信息
        sectionService.deleteSectionByCourseId(id);
        // 删除小节视频

        // 删除课程基本信息
        int rows = baseMapper.deleteById(id);
        if (rows == 0) {
            throw new EduException(20001, "删除课程信息失败");
        }
        // 删除课程描述信息
        descriptionService.removeById(id);
    }

    @Override
    public CourseConfirmVo getCourseConfirmInfo(String courseId) {
        return baseMapper.getCourseConfirmInfo(courseId);
    }

    @Override
    public List<EduCourse> queryCourseByTeacherId(String teacherId) {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id", teacherId);
        List<EduCourse> courseList = baseMapper.selectList(wrapper);
        return courseList;
    }

    @Override
    public Map<String, Object> queryCourseByPage(Page<EduCourse> coursePage) {
        baseMapper.selectPage(coursePage, null);
        List<EduCourse> courseList = coursePage.getRecords();
        long pages = coursePage.getPages();
        long total = coursePage.getTotal();
        long currentPage = coursePage.getCurrent();
        boolean hasNext = coursePage.hasNext();
        boolean previous = coursePage.hasPrevious();
        long size = coursePage.getSize();

        Map<String, Object> map = new HashMap<>();
        map.put("courseList", courseList);
        map.put("pages", pages);
        map.put("total", total);
        map.put("currentPage", currentPage);
        map.put("hasNext", hasNext);
        map.put("previous", previous);
        map.put("size", size);

        return map;
    }

    @Override
    public CourseDetailInfo queryCourseDetailById(String courseId) {
        CourseDetailInfo courseDetailInfo = baseMapper.queryCourseDetailById(courseId);
        return courseDetailInfo;
    }
}
