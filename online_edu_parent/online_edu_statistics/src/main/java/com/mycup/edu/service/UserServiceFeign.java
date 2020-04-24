package com.mycup.edu.service;

import com.mycup.response.RetVal;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Garre
 * @create 2020-04-20 15:33
 */
@FeignClient(value = "EDU-USER")
public interface UserServiceFeign {
    // 获取指定日期注册人数
    @GetMapping("/member/center/queryRegisterNum/{day}")
    public RetVal queryRegisterNum(@PathVariable("day") String day);
}
