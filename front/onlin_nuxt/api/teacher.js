import request from '@/utils/request'

export default {
    // 根据id查询讲师
    queryTeacherById(teacherId) {
        return request({
            url: '/edu/teacher/' + teacherId,
            method: 'get'
        })
    },
     // 查询讲师列表
     queryTeacherByPage(pageNum, pageSize) {
        return request({
            url: `/edu/front/teacher/queryTeacherByPage/${pageNum}/${pageSize}`,
            method: 'get'
        })
    },
    // 查询讲师列表
    queryTeacherDetailById(teacherId) {
        return request({
            url: `/edu/front/teacher/queryTeacherDetailById/${teacherId}`,
            method: 'get'
        })
    },
}