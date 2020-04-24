package com.mycup.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Garre
 * @create 2020-04-13 14:37
 */
@Component
public class OssTemplate {

    // 获取OSS配置文件信息
    @Value("${oss.endpoint}")
    public String endpoint;
    @Value("${oss.accessKeyId}")
    public String accessKeyId;
    @Value("${oss.accessKeySecret}")
    public String accessKeySecret;
    @Value("${oss.bucketName}")
    public String bucketName;


    // 1.上传文件
    public String upload(String fileName, InputStream inputStream) throws FileNotFoundException {

        // 文件夹名+文件名
        fileName = "pic/" + fileName;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        ossClient.putObject("onlineedu-oss", fileName, inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();

        // https://onlineedu-oss.oss-cn-beijing.aliyuncs.com/default.jpg
        String retUrl = "https://" + bucketName + "." + endpoint + "/" + fileName;
        return retUrl;
    }


    // 2. 删除单个文件
    public void delete(String fileName) {
        // 文件夹名+文件名
        fileName = "pic/" + fileName;

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
        ossClient.deleteObject(bucketName, fileName);

        // 关闭OSSClient。
        ossClient.shutdown();

    }


    // 3. 批量删除文件
    public void deleteBatch() {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 删除文件。key等同于ObjectName，表示删除OSS文件时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        List<String> keys = new ArrayList<String>();
        keys.add("default.jpg");
        keys.add("banner2.jpg");

        DeleteObjectsResult deleteObjectsResult = ossClient.deleteObjects(new DeleteObjectsRequest(bucketName).withKeys(keys));
        List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();

        // 关闭OSSClient。
        ossClient.shutdown();

    }


}
