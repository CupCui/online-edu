package com.mycup.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mycup.edu.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mycup.request.TeacherCondition;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author zhangqiang
 * @since 2020-04-10
 */
public interface EduTeacherService extends IService<EduTeacher> {

    void queryTeacherPageByCondition(Page<EduTeacher> teacherPage, TeacherCondition teacherCondition);

    Map<String, Object> queryTeacherByPage(Page<EduTeacher> teacherPage);
}
