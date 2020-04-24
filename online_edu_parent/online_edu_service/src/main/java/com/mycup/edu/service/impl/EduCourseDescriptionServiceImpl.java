package com.mycup.edu.service.impl;

import com.mycup.edu.entity.EduCourseDescription;
import com.mycup.edu.mapper.EduCourseDescriptionMapper;
import com.mycup.edu.service.EduCourseDescriptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author zhangqiang
 * @since 2020-04-15
 */
@Primary
@Service
public class EduCourseDescriptionServiceImpl extends ServiceImpl<EduCourseDescriptionMapper, EduCourseDescription> implements EduCourseDescriptionService {

}
