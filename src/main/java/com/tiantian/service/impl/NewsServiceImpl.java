package com.tiantian.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tiantian.dto.ResultData;
import com.tiantian.entity.News;
import com.tiantian.mapper.NewsMapper;
import com.tiantian.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsMapper newsMapper;

    @Override
    public ResultData searchNews(@RequestParam(defaultValue = "1") Integer page,
                                 @RequestParam(defaultValue = "20") Integer limit,
                                 String newsName) {
        newsName = "%" + newsName + "%";
        PageHelper.startPage(page, limit);
        List<News> news = newsMapper.searchNews(newsName);
        PageInfo<News> pageInfo = new PageInfo<>(news);
        return ResultData.ok(pageInfo);
    }

    @Override
    public ResultData deleteByPrimaryKey(Integer newsId) {
        Boolean flag = false;
        int i = newsMapper.deleteByPrimaryKey(newsId);
        if(i>0){
            flag = true;
        }
        return ResultData.ok(flag);
    }

    @Override
    public ResultData insert(News record) {
        newsMapper.insert(record);
        return new ResultData(record);
    }

    @Override
    public ResultData selectByPrimaryKey(Integer newsId) {
        return null;
    }

    @Override
    public ResultData selectAll() {
        return null;
    }

    @Override
    public ResultData updateByPrimaryKey(News record) {
        Boolean flag = false;
        int i = newsMapper.updateByPrimaryKey(record);
        if(i>0){
            flag = true;
        }
        return new ResultData(flag);
    }

    @Override
    public ResultData updateTop(Integer id , String newsTop) {
        News news = newsMapper.selectByPrimaryKey(id);
        news.setNewsTop(newsTop);
        Boolean flag = false;
        int i = newsMapper.updateByPrimaryKey(news);
        if(i>0){
            flag = true;
        }
        return new ResultData(flag);
    }
}
