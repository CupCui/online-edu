package com.mycup.edu.service;

import com.mycup.edu.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mycup.response.SubjectVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author zhangqiang
 * @since 2020-04-14
 */
public interface EduSubjectService extends IService<EduSubject> {

    void uploadSubject(MultipartFile file) throws Exception;

    EduSubject existSubject(String id, String subjectName);

    List<SubjectVo> getAllSubject();

    boolean deleteSubjectById(String id);

    boolean saveParentSubject(EduSubject subject);

    boolean saveChildSubject(EduSubject subject);
}
