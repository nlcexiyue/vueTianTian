package com.tiantian.controller;

import com.tiantian.dto.ResultData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class UploadController {

    //图片存储路径
    @Value("${ImagePath}")
    private String ImagePath;

    //图片存储ip
    @Value("${IP}")
    private String IP;

    @PostMapping(value = "/upload")
    public ResultData uploadFile(HttpServletRequest request){
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile multipartFile = multipartHttpServletRequest.getFile("file");
        String filename = multipartFile.getOriginalFilename();
        String folderName = UUID.randomUUID().toString().substring(0, 5);
        String folderPath = ImagePath + folderName + "/";
        File folder = new File(folderPath);
        if(!folder.exists()){
            folder.mkdirs();
        }
        String filePath =  folderPath + filename;
        File file = new File(filePath);
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String nginxPath = "http://" + IP + "/"  + folderName + "/" + filename;
        return ResultData.ok(nginxPath);
    }

}
