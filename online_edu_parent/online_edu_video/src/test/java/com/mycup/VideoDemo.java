package com.mycup;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;

import java.util.List;

/**
 * @author Garre
 * @create 2020-04-18 14:43
 */
public class VideoDemo {
    //账号AK信息请填写(必选)
    private static final String accessKeyId = "LTAI4FxfCwTgvJx5KVWC3fFt";
    //账号AK信息请填写(必选)
    private static final String accessKeySecret = "XQ6jWN2wzood9GSAUXN7I0JQY2vHIH";

    /*获取播放地址函数*/
    public static GetPlayInfoResponse getPlayInfo(DefaultAcsClient client) throws Exception {
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        request.setVideoId("7f0551ac8e194124ba0e5c728ab48a76");
        return client.getAcsResponse(request);
    }
    /*以下为调用示例*/
    public static void main(String[] argv) throws Exception {

        DefaultAcsClient client = initVodClient(accessKeyId, accessKeySecret);

        GetPlayInfoResponse response = new GetPlayInfoResponse();
        try {
            response = getPlayInfo(client);
            List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
            //播放地址
            for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
                System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
            }
            //Base信息
            System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        System.out.print("RequestId = " + response.getRequestId() + "\n");
    }

    public static DefaultAcsClient initVodClient(String accessKeyId, String accessKeySecret) throws Exception {
        String regionId = "cn-shanghai";  // 点播服务接入区域
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }

}
