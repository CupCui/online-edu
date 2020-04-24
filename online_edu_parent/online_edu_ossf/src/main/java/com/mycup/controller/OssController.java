package com.mycup.controller;

import com.mycup.response.RetVal;
import com.mycup.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Garre
 * @create 2020-04-13 14:31
 */
// 文件上传/删除
@RestController
@RequestMapping("/oss")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;

    // 1.上传文件
    @PostMapping(value = "uploadFile")
    public RetVal uploadFile(MultipartFile file) throws Exception {
        String url = ossService.uploadFile(file);
        return RetVal.success().data("url", url);
    }

    // 2.删除文件
    @PostMapping(value = "deleteFile")
    public RetVal deleteFile(String fileName) {
        ossService.deleteFile(fileName);
        return RetVal.success().data("fileName", fileName);
    }
}
