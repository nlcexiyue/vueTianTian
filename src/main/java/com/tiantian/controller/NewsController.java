package com.tiantian.controller;

import com.tiantian.dto.ResultData;
import com.tiantian.entity.News;
import com.tiantian.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/news")
public class NewsController {
    @Autowired
    private NewsService newsService;



    //添加
    @PostMapping(value = "/addNews")
    public ResultData addNews(News news){
        return newsService.insert(news);
    }

    //搜索
    @GetMapping(value = "/searchNews")
    public ResultData searchNews(@RequestParam(defaultValue = "1") Integer page,
                                 @RequestParam(defaultValue = "20") Integer limit,
                                 String newsName){
        if (newsName == null){
            newsName = "";
        }
        System.out.println(newsName);
        return newsService.searchNews(page,limit,newsName);
    }

    //单个删除
    @GetMapping(value = "/deleteNews")
    public ResultData deleteNews(Integer id){
        return newsService.deleteByPrimaryKey(id);
    }

    //编辑
    @PostMapping(value = "/updateNews")
    public ResultData updateNews(News news){
        return  newsService.updateByPrimaryKey(news);
    }

    //多选删除
    @GetMapping(value = "/deleteAllNews")
    public ResultData deleteAllNews(String ids){
        String[] split = ids.split(",");
        for (String s : split) {
            int id = Integer.parseInt(s);
            newsService.deleteByPrimaryKey(id);
        }
        return ResultData.ok();
    }

    //修改置顶状态
    @PostMapping(value = "/updateTop")
    public ResultData updateTop(Integer id , String newsTop){
        return newsService.updateTop(id, newsTop);
    }




}
