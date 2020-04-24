package com.mycup.edu.controller;


import com.mycup.edu.service.MemberCenterService;
import com.mycup.response.RetVal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author Garre
 * @since 2020-04-20
 */
@RestController
@RequestMapping("/member/center")
@CrossOrigin
public class MemberCenterController {

    @Autowired
    private MemberCenterService memberCenterService;

    // 获取指定日期注册人数
    @GetMapping("queryRegisterNum/{day}")
    public RetVal queryRegisterNum(@PathVariable("day") String day) {
        Integer count = memberCenterService.queryRegisterNum(day);
        return RetVal.success().data("count", count);
    }

}

