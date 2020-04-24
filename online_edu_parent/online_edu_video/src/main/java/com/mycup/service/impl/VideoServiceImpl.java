package com.mycup.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.mycup.service.VideoService;
import com.mycup.utils.VideoUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author Garre
 * @create 2020-04-18 15:09
 */
@Service
public class VideoServiceImpl implements VideoService {
    //账号AK信息请填写(必选)
    @Value("${aliyun.video.accessKeyId}")
    public String accessKeyId;
    //账号AK信息请填写(必选)
    @Value("${aliyun.video.accessKeySecret}")
    public String accessKeySecret;

    @Override
    public String uploadAliyunVideo(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();

        String fileName = file.getOriginalFilename();
        String title = fileName.substring(0, fileName.indexOf("."));

        UploadStreamRequest request = new UploadStreamRequest(accessKeyId, accessKeySecret, title, fileName, inputStream);

        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);

        return response.getVideoId();
    }

    @Override
    public void deleteSingleVideo(String videoId) throws Exception {
        DefaultAcsClient client = VideoUtils.initVodClient(accessKeyId, accessKeySecret);

        DeleteVideoRequest request = new DeleteVideoRequest();
        //支持传入多个视频ID，多个用逗号分隔
        request.setVideoIds(videoId);
        client.getAcsResponse(request);
    }
}
