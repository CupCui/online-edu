package com.mycup.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mycup.edu.entity.EduTeacher;
import com.mycup.edu.mapper.EduTeacherMapper;
import com.mycup.edu.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mycup.request.TeacherCondition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author zhangqiang
 * @since 2020-04-10
 */
@Primary
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public void queryTeacherPageByCondition(Page<EduTeacher> teacherPage, TeacherCondition teacherCondition) {
        String name = teacherCondition.getName();
        Integer level = teacherCondition.getLevel();
        String beginTime = teacherCondition.getBeginTime();
        String endTime = teacherCondition.getEndTime();

        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();

        if (StringUtils.isNotEmpty(name)) {
            wrapper.like("name", name);
        }
//        if (level != 0 && level != null) {
        if (level != null) {
            wrapper.eq("level", level);
        }
        if (StringUtils.isNotEmpty(beginTime)) {
            wrapper.ge("gmt_create", beginTime);
        }
        if (StringUtils.isNotEmpty(endTime)) {
            wrapper.le("gmt_create", endTime);
        }

        wrapper.orderByDesc("gmt_modified");

        baseMapper.selectPage(teacherPage, wrapper);
    }

    @Override
    public Map<String, Object> queryTeacherByPage(Page<EduTeacher> teacherPage) {
        baseMapper.selectPage(teacherPage, null);
        List<EduTeacher> teacherList = teacherPage.getRecords();
        long pages = teacherPage.getPages();
        long total = teacherPage.getTotal();
        long currentPage = teacherPage.getCurrent();
        boolean hasNext = teacherPage.hasNext();
        boolean previous = teacherPage.hasPrevious();
        long size = teacherPage.getSize();

        Map<String, Object> map = new HashMap<>();
        map.put("teacherList", teacherList);
        map.put("pages", pages);
        map.put("total", total);
        map.put("currentPage", currentPage);
        map.put("hasNext", hasNext);
        map.put("previous", previous);
        map.put("size", size);

        return map;
    }
}
