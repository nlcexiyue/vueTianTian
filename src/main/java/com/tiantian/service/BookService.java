package com.tiantian.service;

import com.github.pagehelper.PageInfo;
import com.tiantian.dto.ResultData;
import com.tiantian.entity.Book;


import java.util.List;

public interface BookService{

////添加
    ResultData add(Book book);

    ResultData updataCondition(Integer bookId ,Integer condition);

//小说列表
    PageInfo<Book> list(Integer page, Integer pageSize);

//    更新状态
    ResultData updateStatus(Integer bookId, Integer condition);
//通过id去查找数据
    Book findById(Integer bookId);
//更新基本信息
    ResultData edit(Book book);

//    批量更改状态
    ResultData batchDelete(String[] ids);

//    search
    PageInfo<Book> searchList(Integer page, Integer pageSize, String keyword);
    PageInfo<Book> searchList1(Integer page, Integer pageSize, String keyword1);

    /**
     *
     * @param bookType
     * @return
     */
    List<Book> findByType(Integer bookType);

    /**
     * 首页推荐，随机记载
     * @return
     */
    List<Book> findRandList();


    /**
     * 通过模糊查询
     * @return
     */
    List<Book> findByLikeName(String likeName);
}
