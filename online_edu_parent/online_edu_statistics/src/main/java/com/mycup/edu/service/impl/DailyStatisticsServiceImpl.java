package com.mycup.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mycup.edu.entity.DailyStatistics;
import com.mycup.edu.mapper.DailyStatisticsMapper;
import com.mycup.edu.service.DailyStatisticsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mycup.edu.service.UserServiceFeign;
import com.mycup.response.RetVal;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author Garre
 * @since 2020-04-20
 */
@Service
public class DailyStatisticsServiceImpl extends ServiceImpl<DailyStatisticsMapper, DailyStatistics> implements DailyStatisticsService {

    @Autowired
    private UserServiceFeign userServiceFeign;

    @Override
    public void generateData(String day) {
        // 通过远程RPC拿到数据
        // 把数据存储到数据库中
        RetVal retVal = userServiceFeign.queryRegisterNum(day);
        // 改日注册用户数
        Integer registerNum = (Integer) retVal.getData().get("count");

        DailyStatistics dailyStatistics = new DailyStatistics();
        dailyStatistics.setRegisterNum(registerNum);
        dailyStatistics.setDateCalculated(day);
        // 造数据
        dailyStatistics.setLoginNum(RandomUtils.nextInt(300, 400));
        dailyStatistics.setCourseNum(RandomUtils.nextInt(100, 300));
        dailyStatistics.setVideoViewNum(RandomUtils.nextInt(300, 400));

        baseMapper.insert(dailyStatistics);
    }

    @Override
    public Map<String, Object> showStatistics(String dataType, String beginTime, String endTime) {
        // 根据查询条件查询统计表信息
        QueryWrapper<DailyStatistics> wrapper = new QueryWrapper<>();
        wrapper.between("date_calculated", beginTime, endTime);
        List<DailyStatistics> dailyStatistics = baseMapper.selectList(wrapper);

        // 根据数据类型返回对应的数据信息
        // 构建xy坐标Map
        ArrayList<String> xData = new ArrayList<>();
        ArrayList<Integer> yData = new ArrayList<>();
        for (DailyStatistics dailyStatistic : dailyStatistics) {
            String dateCalculated = dailyStatistic.getDateCalculated();
            xData.add(dateCalculated);  // x坐标存储日期
            switch (dataType) {
                case "login_num":
                    Integer loginNum = dailyStatistic.getLoginNum();
                    yData.add(loginNum);
                    break;
                case "register_num":
                    Integer registerNum = dailyStatistic.getRegisterNum();
                    yData.add(registerNum);
                    break;
                case "video_view_num":
                    Integer videoViewNum = dailyStatistic.getVideoViewNum();
                    yData.add(videoViewNum);
                    break;
                case "course_num":
                    Integer courseNum = dailyStatistic.getCourseNum();
                    yData.add(courseNum);
                    break;
                default:
                    break;
            }
        }
        // 返回两个数据信息 x坐标y坐标
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("xData", xData);
        retMap.put("yData", yData);
        return retMap;
    }
}
