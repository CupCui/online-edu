package com.mycup.edu.controller;


import com.mycup.edu.service.DailyStatisticsService;
import com.mycup.response.RetVal;
import org.ehcache.core.spi.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Struct;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author Garre
 * @since 2020-04-20
 */
@RestController
@RequestMapping("/daily/statistics")
@CrossOrigin
public class DailyStatisticsController {

    @Autowired
    private DailyStatisticsService dailyStatisticsService;

    // 生成数据
    @GetMapping("generateData/{day}")
    public RetVal generateData(@PathVariable("day") String day) {
        dailyStatisticsService.generateData(day);
        return RetVal.success();
    }

    // 显示统计数据
    @GetMapping("showStatistics/{dataType}/{beginTime}/{endTime}")
    public RetVal showStatistics(@PathVariable("dataType") String dataType,
                                 @PathVariable("beginTime") String beginTime,
                                 @PathVariable("endTime") String endTime) {
        // 返回echarts的X坐标Y坐标信息
        Map<String, Object> retMap = dailyStatisticsService.showStatistics(dataType, beginTime, endTime);

        return RetVal.success().data(retMap);
    }
}

