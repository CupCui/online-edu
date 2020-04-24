package com.mycup.edu.service;

import com.mycup.edu.entity.DailyStatistics;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author Garre
 * @since 2020-04-20
 */
public interface DailyStatisticsService extends IService<DailyStatistics> {

    void generateData(String day);

    Map<String, Object> showStatistics(String dataType, String beginTime, String endTime);
}
