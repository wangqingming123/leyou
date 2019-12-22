package com.leyou.upload.controller;

import com.leyou.upload.service.uploadImageService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("upload")
public class UploadController {

    @Autowired
    private uploadImageService uploadImageService;

    @PostMapping("image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {

        String url = uploadImageService.uploadImage(file);//上传文件返回图片地址

        if(StringUtils.isBlank(url)){
            return ResponseEntity.badRequest().build();
        }
        System.out.println(url);
        return ResponseEntity.status(HttpStatus.CREATED).body(url);
    }
}
