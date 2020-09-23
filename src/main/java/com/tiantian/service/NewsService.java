package com.tiantian.service;

import com.tiantian.dto.ResultData;
import com.tiantian.entity.News;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface NewsService {
    ResultData searchNews(@RequestParam(defaultValue = "1") Integer page,
                          @RequestParam(defaultValue = "20") Integer limit,
                          String newsName);

    ResultData deleteByPrimaryKey(Integer newsId);

    ResultData insert(News record);

    ResultData selectByPrimaryKey(Integer newsId);

    ResultData selectAll();

    ResultData updateByPrimaryKey(News record);

    ResultData updateTop(Integer id , String newsTop);
}
