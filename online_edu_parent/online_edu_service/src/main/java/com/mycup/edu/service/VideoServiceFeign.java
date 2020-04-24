package com.mycup.edu.service;

import com.mycup.response.RetVal;
import com.mycup.service.VideoService;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Garre
 * @create 2020-04-20 10:27
 */
@FeignClient(value = "EDU-VIDEO")
public interface VideoServiceFeign {
    // 删除单个视频
    @DeleteMapping("/aliyun/{videoId}")
    public RetVal deleteAliyunVideo(@PathVariable("videoId") String videoId) throws Exception;

    // 删除多个视频
    @DeleteMapping("/aliyun/deleteMultiVideo")
    public RetVal deleteMultiVideo(@RequestParam("videoIdList") List<String> videoIdList) throws Exception;
}
