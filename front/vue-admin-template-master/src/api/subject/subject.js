import request from '@/utils/request'

export default {
    // 1.查询所有学科信息
    queryAllSubject() {
        return request({
            url: `/edu/subject/getAllSubject`,
            method: 'get'
        })
    },
    // 1.删除课程分类
    deleteSubjectById(id) {
        return request({
            url: `/edu/subject/${id}`,
            method: 'delete'
        })
    },
    // 1.添加一级分类课程分类
    saveParentSubject(subject) {
        return request({
            url: `/edu/subject/saveParentSubject`,
            method: 'post',
            params: subject
        })
    },
    // 1.添加二级分类课程分类
    saveChildSubject(subject) {
        return request({
            url: `/edu/subject/saveChildSubject`,
            method: 'post',
            params: subject
        })
    }
}
