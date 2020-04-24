package com.mycup.edu.mapper;

import com.mycup.edu.entity.MemberCenter;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author Garre
 * @since 2020-04-20
 */
public interface MemberCenterMapper extends BaseMapper<MemberCenter> {
    Integer queryRegisterNum(String day);
}
