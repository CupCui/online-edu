package com.mycup.edu.service;

import com.mycup.edu.entity.MemberCenter;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author Garre
 * @since 2020-04-20
 */
public interface MemberCenterService extends IService<MemberCenter> {

    Integer queryRegisterNum(String day);
}
