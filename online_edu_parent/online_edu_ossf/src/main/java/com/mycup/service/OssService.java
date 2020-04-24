package com.mycup.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author Garre
 * @create 2020-04-13 15:01
 */
public interface OssService {

    String uploadFile(MultipartFile file) throws Exception;

    void deleteFile(String fileName);
}
