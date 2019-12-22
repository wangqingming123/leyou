package com.leyou.upload.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface uploadImageService {

    /*
    * 上传图片
    * */
    String uploadImage(MultipartFile file) throws IOException;
}
