package com.tiantian.service;
import com.github.pagehelper.PageInfo;
import com.tiantian.dto.ResultData;
import com.tiantian.entity.Discount;


public interface DiscountService {

    ResultData add(Discount discount);

    //    page:当前页
//    pagesize:页容量
    PageInfo<Discount> list(Integer page, Integer pageSize);
    ResultData updateStatus(Integer id, int status);
    //   批量删除
    ResultData batchDelete(String[] ids);

    ResultData delete(Integer actId, Integer actStatus);
    Discount findById(Integer id);
    ResultData edit(Discount discount);


    PageInfo<Discount> searchList(Integer page, Integer pageSize, String keyword);

}
