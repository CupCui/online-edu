import request from '@/utils/request'

export default {
     // 查询讲师列表
     queryCourseByPage(pageNum, pageSize) {
        return request({
            url: `/edu/front/course/queryCourseByPage/${pageNum}/${pageSize}`,
            method: 'get'
        })
    },
    // 查询课程详细信息
    queryCourseDetailById(courseId) {
        return request({
            url: `/edu/front/course/queryCourseDetailById/${courseId}`,
            method: 'get'
        })
    },
}