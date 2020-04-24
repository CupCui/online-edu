package com.mycup.edu.mapper;

import com.mycup.edu.entity.CourseDetailInfo;
import com.mycup.edu.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mycup.response.CourseConfirmVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author zhangqiang
 * @since 2020-04-15
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    CourseConfirmVo getCourseConfirmInfo(String courseId);

    CourseDetailInfo queryCourseDetailById(String courseId);
}
