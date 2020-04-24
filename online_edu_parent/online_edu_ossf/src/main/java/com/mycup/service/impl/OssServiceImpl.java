package com.mycup.service.impl;

import com.mycup.oss.OssTemplate;
import com.mycup.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * @author Garre
 * @create 2020-04-13 15:04
 */
@Service
public class OssServiceImpl implements OssService {
    @Autowired
    private OssTemplate ossTemplate;

    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        String originalFilename = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String filename = uuid + originalFilename;

        String retUrl = ossTemplate.upload(filename, inputStream);

        return retUrl;
    }

    @Override
    public void deleteFile(String fileName) {
        ossTemplate.delete(fileName);
    }
}
