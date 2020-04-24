import request from '@/utils/request'

export default {
    // 1.分页查询讲师信息
    queryTeacherPageByCondition(pageNum, pageSize, teacherCondition) {
        return request({
            url: `/edu/teacher/queryTeacherPageByCondition/${pageNum}/${pageSize}`,
            method: 'get',
            params: teacherCondition
        })
    },

    // 2.删除讲师功能
    deleteTeacherById(teacherId) {
        return request({
            url: `/edu/teacher/${teacherId}`,
            method: 'delete'
        })
    },

    // 3.添加讲师
    saveTeacher(teacher) {
        return request({
            url: `/edu/teacher`,
            method: 'post',
            params: teacher
        })
    },

    // 4.根据id查询讲师
    queryTeacherById(teacherId) {
        return request({
            url: `/edu/teacher/${teacherId}`,
            method: 'get'
        })
    },

    // 5.更新讲师
    updateTeacher(teacher) {
        return request({
            url: `/edu/teacher`,
            method: 'put',
            params: teacher
        })
    }
    
}
