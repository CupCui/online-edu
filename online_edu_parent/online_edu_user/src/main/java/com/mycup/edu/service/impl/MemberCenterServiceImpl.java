package com.mycup.edu.service.impl;

import com.mycup.edu.entity.MemberCenter;
import com.mycup.edu.mapper.MemberCenterMapper;
import com.mycup.edu.service.MemberCenterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author Garre
 * @since 2020-04-20
 */
@Service
public class MemberCenterServiceImpl extends ServiceImpl<MemberCenterMapper, MemberCenter> implements MemberCenterService {

    @Override
    public Integer queryRegisterNum(String day) {
        Integer count = baseMapper.queryRegisterNum(day);
        return count;
    }
}
