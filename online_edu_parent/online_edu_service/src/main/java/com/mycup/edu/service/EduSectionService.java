package com.mycup.edu.service;

import com.mycup.edu.entity.EduSection;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程小节 服务类
 * </p>
 *
 * @author Garre
 * @since 2020-04-17
 */
public interface EduSectionService extends IService<EduSection> {

    void addSection(EduSection section);

    void deleteSection(String id);

    void deleteSectionByCourseId(String id);
}
