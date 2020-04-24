package com.mycup.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mycup.edu.entity.EduChapter;
import com.mycup.edu.entity.EduSection;
import com.mycup.edu.handler.EduException;
import com.mycup.edu.mapper.EduChapterMapper;
import com.mycup.edu.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mycup.edu.service.EduSectionService;
import com.mycup.response.ChapterVo;
import com.mycup.response.SectionVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author Garre
 * @since 2020-04-17
 */
@Primary
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduSectionService sectionService;

    @Override
    public boolean saveChapter(EduChapter chapter) {
        //判断是否存在
        EduChapter existChapter = existChapter(chapter.getCourseId(), chapter.getTitle());
        if(existChapter==null){
            int insert = baseMapper.insert(chapter);
            return insert>0;
        }else{
            throw new EduException(20001,"章节已经重复");
        }
    }

    public EduChapter existChapter(String courseId, String chapterName) {
        QueryWrapper<EduChapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        queryWrapper.eq("title", chapterName);
        EduChapter chapter = baseMapper.selectOne(queryWrapper);
        return chapter;

    }

    @Override
    public boolean deleteChapter(String chapterId) {
        //判断是否有小节
        QueryWrapper<EduSection> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("chapter_id",chapterId);
        int count = sectionService.count(queryWrapper);
        //没有小节
        if(count==0){
            int i = baseMapper.deleteById(chapterId);
            return i>0;
        }else{
            throw new EduException(20001,"该章节存在小节");
        }
    }

    @Override
    public List<ChapterVo> getChapterAndSection(String courseId) {
        // 查询所有章节
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        List<EduChapter> chapterList = baseMapper.selectList(wrapper);

        // 查询所有小节
        QueryWrapper<EduSection> sectionWrapper = new QueryWrapper<>();
        sectionWrapper.eq("course_id", courseId);
        List<EduSection> sectionList = sectionService.list(sectionWrapper);

        // 封装小节信息
        // 迭代章节
        ArrayList<ChapterVo> chapterVos = new ArrayList<>();
        for (EduChapter chapter : chapterList) {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter, chapterVo);
            // 迭代小节
            ArrayList<SectionVo> chapterChildren = new ArrayList<>();
            for (EduSection section : sectionList) {
                // 用小节的chapterId与章节比对
                if (chapter.getId().equals(section.getChapterId())) {
                    SectionVo sectionVo = new SectionVo();
                    BeanUtils.copyProperties(section, sectionVo);
                    chapterChildren.add(sectionVo);
                }

                chapterVo.setChildren(chapterChildren);
            }
            chapterVos.add(chapterVo);
        }

        return chapterVos;
    }

    // 根据课程id删除章节
    @Override
    public void deleteChapterByCourseId(String id) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", id);
        baseMapper.delete(wrapper);
    }
}
