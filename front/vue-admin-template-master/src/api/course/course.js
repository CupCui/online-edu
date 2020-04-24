import request from '@/utils/request'

export default {
    // 1.查询所有学科信息
    getAllTeacher() {
        return request({
            url: `/edu/teacher`,
            method: 'get'
        })
    },
    // 2.查询所有课程信息
    getAllSubject() {
        return request({
            url: `/edu/subject/getAllSubject`,
            method: 'get'
        })
    },
    // 3.保存课程信息
    saveCourseInfo(courseInfo) {
        return request({
            url: `/edu/course/saveCourseInfo`,
            method: 'post',
            params: courseInfo
        })
    },
    // 3.保存课程信息
    getCoursePageByCondition(pageNum, pageSize, queryCourse) {
        return request({
            url: `/edu/course/getCoursePageByCondition/${pageNum}/${pageSize}`,
            method: 'post',
            params: queryCourse
        })
    },
    // 4.根据课程id查询课程
    getCourseById(id) {
        return request({
            url: `/edu/course/${id}`,
            method: 'get'
        })
    },
     // 5.修改课程基本信息
     updateCourseInfo(courseInfoVo) {
        return request({
            url: `/edu/course/updateCourseInfo`,
            method: 'post',
            params: courseInfoVo
        })
    },
    // 7.修改课程基本信息
    deleteCourseInfo(id) {
        return request({
            url: `/edu/course/${id}`,
            method: 'delete'
        })
    },
    // 8.课程发布信息确认
    getCourseConfirmInfo(courseId) {
        return request({
            url: `/edu/course/getCourseConfirmInfo/${courseId}`,
            method: 'get'
        })
    },
    // 9.课程发布
    publishCourse(courseId) {
        return request({
            url: `/edu/course/publishCourse/${courseId}`,
            method: 'get'
        })
    }

}

