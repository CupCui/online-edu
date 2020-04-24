package com.mycup.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mycup.edu.entity.EduSection;
import com.mycup.edu.handler.EduException;
import com.mycup.edu.mapper.EduSectionMapper;
import com.mycup.edu.service.EduSectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mycup.edu.service.VideoServiceFeign;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程小节 服务实现类
 * </p>
 *
 * @author Garre
 * @since 2020-04-17
 */
@Primary
@Service
public class EduSectionServiceImpl extends ServiceImpl<EduSectionMapper, EduSection> implements EduSectionService {

    @Autowired
    private VideoServiceFeign videoServiceFeign;

    @Override
    public void addSection(EduSection section) {
        //1.判断是否存在小节
        QueryWrapper<EduSection> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id",section.getCourseId());
        queryWrapper.eq("chapter_id",section.getChapterId());
        queryWrapper.eq("title",section.getTitle());
        EduSection existSection = baseMapper.selectOne(queryWrapper);
        if(existSection==null){
            baseMapper.insert(section);
        }else{
            throw new EduException(20001,"存在重复的小节");
        }
    }

    // 删除小节
    @Override
    public void deleteSection(String id) {
        // 查询小节视频id
        EduSection section = baseMapper.selectById(id);
        String videoSourceId = section.getVideoSourceId();

        if (StringUtils.isNotEmpty(videoSourceId)) {
            // 远程调用video微服务删视频
            try {
                videoServiceFeign.deleteAliyunVideo(videoSourceId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        baseMapper.deleteById(id);
    }

    // 根据课程id删除小节信息
    @Override
    public void deleteSectionByCourseId(String id) {
        QueryWrapper<EduSection> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", id);
        List<EduSection> sectionList = baseMapper.selectList(wrapper);
        // 拿到小节视频id
        ArrayList<String> videoList = new ArrayList<>();
        for (EduSection section : sectionList) {
            String videoSourceId = section.getVideoSourceId();
            if (StringUtils.isNotEmpty(videoSourceId)) {
                videoList.add(videoSourceId);
            }
        }

        // 删除小节视频
        try {
            videoServiceFeign.deleteMultiVideo(videoList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 删除小节
        baseMapper.delete(wrapper);
    }
}

