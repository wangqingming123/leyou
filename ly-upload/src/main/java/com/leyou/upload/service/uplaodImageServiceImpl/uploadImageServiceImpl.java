package com.leyou.upload.service.uplaodImageServiceImpl;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.domain.ThumbImageConfig;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.leyou.upload.service.uploadImageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class uploadImageServiceImpl  implements uploadImageService  {


    private Logger logger=LoggerFactory.getLogger(uploadImageServiceImpl.class);
    /*
    * 上传图片客户端
    * */
    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    /*
    * 上传缩略图客户端
    * */
    @Autowired
    private ThumbImageConfig thumbImageConfig;

    /*
    * 允许上传的图片类型
    * .jpg 对应的就是 image/jpeg
    * */
    private static  final List<String> ALLOWED_TYPE = Arrays.asList("image/jpeg", "image/png");

    @Override
    public String uploadImage(MultipartFile file)  {


        System.out.println(file.getContentType());
        //校验格式是否符合规范
        if (!ALLOWED_TYPE.contains(file.getContentType())){
            logger.info("文件类型不允许：{}",file.getOriginalFilename());
            return null;
        }

        try {
            //校验文件内容
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if (bufferedImage==null){
                logger.info("文件内容不合法：{}",file.getOriginalFilename());
                return null;
            }

            //获取文件后缀
            String suffix = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");

            //上传到fastDFS
            StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), suffix, null);
            System.out.println(storePath);
            System.out.println(storePath.getFullPath());
            System.out.println("分支dev +处理冲突，当前分支 master");
            //返回服务器url地址
            System.out.println("Dev 分支提交代码 不同电脑测试 111");
            return "image.leyou.com/"+storePath.getFullPath();

        }catch (Exception e){
            e.printStackTrace();
            logger.info("服务器内部错误：{}",file.getOriginalFilename());
        }

        return null;
    }
}
