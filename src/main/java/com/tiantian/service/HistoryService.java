package com.tiantian.service;

import com.github.pagehelper.PageInfo;
import com.tiantian.dto.ResultData;
import com.tiantian.entity.History;


public interface HistoryService {

    ////添加
    ResultData add(History history);

    //小说列表
    PageInfo<History> list(Integer page, Integer pageSize);

    //    search
    PageInfo<History> searchList(Integer page, Integer pageSize, String keyword);
    //    批量更改状态
    ResultData batchDelete(String[] ids);
}
