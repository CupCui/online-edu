package com.mycup.controller;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mycup.response.RetVal;
import com.mycup.service.VideoService;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.Path;
import java.util.List;

/**
 * @author Garre
 * @create 2020-04-18 15:06
 */
@RestController
@RequestMapping("/aliyun")
@CrossOrigin
public class VideoController {

    @Autowired
    private VideoService videoService;

    // 视频上传
    @PostMapping("uploadAliyunVideo")
    public RetVal uploadAliyunVideo(@RequestParam("file") MultipartFile file) throws Exception {
        String videoId = videoService.uploadAliyunVideo(file);
        return RetVal.success().data("videoId", videoId);
    }

    // 删除单个视频
    @DeleteMapping("{videoId}")
    public RetVal deleteAliyunVideo(@PathVariable String videoId) throws Exception {
        videoService.deleteSingleVideo(videoId);
        return RetVal.success();
    }

    // 删除多个视频
    @DeleteMapping("deleteMultiVideo")
    public RetVal deleteMultiVideo(@RequestParam List<String> videoIdList) throws Exception {
        String videoList = StringUtils.join(videoIdList, ',');
        videoService.deleteSingleVideo(videoList);
        return RetVal.success();
    }
}
