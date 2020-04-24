package com.mycup.service;

import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Garre
 * @create 2020-04-18 15:09
 */
public interface VideoService {
    String uploadAliyunVideo(MultipartFile file) throws Exception;

    void deleteSingleVideo(String videoId) throws Exception;
}
