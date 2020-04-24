package com.mycup.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mycup.edu.entity.EduSubject;
import com.mycup.edu.entity.ExcelSubject;
import com.mycup.edu.handler.EduException;
import com.mycup.edu.listener.SubjectListener;
import com.mycup.edu.mapper.EduSubjectMapper;
import com.mycup.edu.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mycup.response.SubjectVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.security.auth.Subject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author zhangqiang
 * @since 2020-04-14
 */
@Primary
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {
    @Autowired
    private SubjectListener subjectListener;

    @Override
    public void uploadSubject(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        EasyExcel.read(inputStream, ExcelSubject.class, subjectListener).doReadAll();
    }

    // 判断数据库是否存在该课程
    @Override
    public EduSubject existSubject(String id, String subjectName) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", subjectName);
        wrapper.eq("parent_id", id);
        EduSubject subject = baseMapper.selectOne(wrapper);
        return subject;
    }

    @Override
    public List<SubjectVo> getAllSubject() {
        // a.查询所有的课程分类
        List<EduSubject> allSubject = baseMapper.selectList(null);

        ArrayList<SubjectVo> parentSubjectVos = new ArrayList<>();  // 所有的父课程

        // b.查询所有父课程
        for (EduSubject subject : allSubject) {
            if ("0".equals(subject.getParentId())) {
                SubjectVo subjectVo = new SubjectVo();
                // 把subject中属性赋值给subjectVo
                BeanUtils.copyProperties(subject, subjectVo);
                parentSubjectVos.add(subjectVo);
            }
        }

        // c.存放所有父课程
        Map<String, SubjectVo> parentSubjectMap = new HashMap<>();
        for (SubjectVo parentSubjectVo : parentSubjectVos) {
            parentSubjectMap.put(parentSubjectVo.getId(), parentSubjectVo);
        }

        // d.查找所有子课程
        for (EduSubject subject : allSubject) {
            if (!"0".equals(subject.getParentId())) {
                // 父课程
                SubjectVo parentSubjectVo = parentSubjectMap.get(subject.getParentId());
                SubjectVo childSubjectVo = new SubjectVo();
                // 把subject中属性赋值给subjectVo
                BeanUtils.copyProperties(subject, childSubjectVo);
                parentSubjectVo.getChildren().add(childSubjectVo);
            }
        }

        return parentSubjectVos;
    }

    @Override
    public boolean deleteSubjectById(String id) {
        // 删除父节点
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);
        Integer count = baseMapper.selectCount(wrapper);
        if (count == 0) {    // 没有子节点
            int rows = baseMapper.deleteById(id);
            return rows>0;
        } else {
            throw new EduException(20001, "该节点存在子节点");
        }
    }

    @Override
    public boolean saveParentSubject(EduSubject subject) {
        EduSubject parentSubject = existSubject("0", subject.getTitle());
        if (parentSubject == null) {
            parentSubject = new EduSubject();
            parentSubject.setId(subject.getId());
            parentSubject.setTitle(subject.getTitle());
            int rows = baseMapper.insert(parentSubject);
            return rows>0;
        }
        return false;
    }

    @Override
    public boolean saveChildSubject(EduSubject subject) {
        EduSubject childSubject = existSubject(subject.getParentId(), subject.getTitle());
        if (childSubject == null) {
            childSubject = new EduSubject();
            childSubject.setId(subject.getId());
            childSubject.setTitle(subject.getTitle());
            childSubject.setParentId(subject.getParentId());
            int rows = baseMapper.insert(childSubject);
            return rows>0;
        }
        return false;
    }
}
