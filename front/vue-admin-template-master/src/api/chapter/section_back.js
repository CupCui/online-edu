import request from '@/utils/request'
export default {
    //1.添加小节
    addSection(section) {
        return request({
            url: '/edu/section/',
            method: 'post',
            params: section
        })
    },
    //2.删除小节
    deleteSection(id) {
        return request({
            url: '/edu/section/' + id,
            method: 'delete'
        })
    },
    
    //3.更新小节
    updateSection(section) {
        return request({
            url: '/edu/section/',
            method: 'put',
            params: section

        })
    },
    //4.根据id查询小节
    getSectionById(id) {
        return request({
            url: '/edu/section/' + id,
            method: 'get'
        })
    },
    //4.删除单个视频
    deleteAliyunVideo(videoId) {
        return request({
            url: `/aliyun/${videoId}`,
            method: 'delete'
        })
    },
}