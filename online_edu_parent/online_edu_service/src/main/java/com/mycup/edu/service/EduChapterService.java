package com.mycup.edu.service;

import com.mycup.edu.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mycup.response.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author Garre
 * @since 2020-04-17
 */
public interface EduChapterService extends IService<EduChapter> {

    boolean saveChapter(EduChapter chapter);

    boolean deleteChapter(String chapterId);

    List<ChapterVo> getChapterAndSection(String courseId);

    void deleteChapterByCourseId(String id);
}
