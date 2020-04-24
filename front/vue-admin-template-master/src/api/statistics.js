import request from '@/utils/request'

export default {
    // 生成数据
    generateData(day) {
        return request({
            url: `/daily/statistics/generateData/${day}`,
            method: 'get'
        })
    },
    // 获取显示数据信息
    showStatistics(queryObj) {
        return request({
            url: `/daily/statistics/showStatistics/${queryObj.dataType}/${queryObj.beginTime}/${queryObj.endTime}`,
            method: 'get'
        })
    }
}