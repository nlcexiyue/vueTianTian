package com.tiantian.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tiantian.dto.ResultData;
import com.tiantian.entity.Images;
import com.tiantian.service.ImagesService;
import com.tiantian.tools.Base64ImageUtils;
import com.tiantian.tools.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.jedis.JedisClusterConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/7/8
 * \* Time: 9:38
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@RestController
@RequestMapping(value = "/images")
public class ImagesController {

    @Autowired
    private ImagesService imagesService;

    @Autowired
    private RedisTemplate redisTemplate;


    @Value("${ImagesPath}")
    private String ImagesPath;

    @Value("${NginxPath}")
    private String NginxPath;

    @PostMapping(value = "/deleteImagesById")
    public ResultData deleteImagesById(String id) {
        if (!id.contains(",")) {
            id = id + ",";
        }
        String[] split = id.split(",");
        for (String s : split) {
            int i = Integer.parseInt(s);
            imagesService.deleteByPrimaryKey(i);
        }
        return ResultData.ok();
    }


    @PostMapping(value = "/selectImagesById")
    public ResultData selectImagesById(Integer id) {
        Images images = imagesService.selectByPrimaryKey(id);
        return ResultData.ok(images);
    }

    @PostMapping(value = "/selectImagesAll")
    public ResultData selectImagesAll(@RequestParam(defaultValue = "1") Integer page,
                                      @RequestParam(defaultValue = "20") Integer limit) {
        List<Images> imagesList1 = imagesService.selectAll();
//        for (Images images : imagesList1) {
//            String path = images.getPath();
//            String s = ImagesPath + path.substring(26);
//            Integer id = images.getId();
//            File file = new File(s);
//            String content = Base64ImageUtils.encodeImageToBase64(file);
//            List<Object> list = Lists.newArrayList();
//            Map<String, Object> map = Maps.newHashMap();
//            map.put("content",content);
//            // 将图片信息以map的形式存储再redis
////            RedisUtil redisUtil = new RedisUtil();
////            redisUtil.set("images"+id,content);
//            //存入缓存
//            redisTemplate.boundValueOps("images"+id).set(content);
//            System.out.println("true");
//            String []arr = {"images" + id};
//
//            RedisUtil redisUtil = new RedisUtil();
//            redisUtil.del(arr);
//            Boolean delete = redisTemplate.delete("images" + id);
//            System.out.println(delete);
//        }
        PageHelper.startPage(page, limit);
        List<Images> imagesList = imagesService.selectAll();
        PageInfo pageInfo = new PageInfo(imagesList);
        return ResultData.ok(pageInfo);
    }


    @PostMapping(value = "/uploadImages")
    public ResultData uploadImages(HttpServletRequest request) {
        if (request instanceof MultipartHttpServletRequest) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            MultipartFile multipartFile = multipartHttpServletRequest.getFile("file");
            String uuid = UUID.randomUUID().toString();

            String originalFilename = multipartFile.getOriginalFilename();
            String[] split = originalFilename.split("\\.");
            String filePath1 = NginxPath + uuid + "." + split[1];
            String filePath = ImagesPath + uuid + "." + split[1];
            File file = new File(filePath);
            try {
                multipartFile.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Images images = new Images(null, originalFilename, filePath1);
            imagesService.insert(images);

            return ResultData.ok();


        }

        return ResultData.error();
    }
}