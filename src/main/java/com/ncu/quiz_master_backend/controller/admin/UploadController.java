package com.ncu.quiz_master_backend.controller.admin;

import com.ncu.quiz_master_backend.anno.Log;
import com.ncu.quiz_master_backend.entity.Result;
import com.ncu.quiz_master_backend.utils.AliOSSUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    AliOSSUtils aliOSSUtils;

    @Log
    @PostMapping
    public Result upload(MultipartFile image) throws IOException {

//        log.info("文件上传：{},{},{}",username,age,image);
//        //获取原始文件名
//        String originalFilename = image.getOriginalFilename();
//        //将文件存储在本地的磁盘目录
//        image.transferTo(new File("D:/JetBrains/IdeaProjects/tlias-web-management/images/"+originalFilename));
//        //但是如果多次上传同名文件，会覆盖原本文件，因此可以采用随机取名，见下
//
//        //构建新的文件名
//        String extname = originalFilename.substring(originalFilename.lastIndexOf("."));//文件扩展名
//        String newFileName = UUID.randomUUID().toString()+extname;//随机名+文件扩展名
//        //将文件存储在本地的磁盘目录
//        image.transferTo(new File("D:/JetBrains/IdeaProjects/tlias-web-management/images/"+newFileName));
//        //但是存储在本地有丢失的风险，因此使用阿里云的OSS对象存储服务，如下所示已封装到工具类aliOSSUtils中了

        String url = aliOSSUtils.upload(image);
        return Result.success(url);
    }
}
