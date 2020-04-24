package com.mycup.utils;

import com.aliyun.oss.ClientException;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;

/**
 * @author Garre
 * @create 2020-04-18 15:37
 */
public class VideoUtils {
    public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) throws Exception {
        String regionId = "cn-shanghai";  // 点播服务接入区域
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }
}
