package com.tiantian.mapper;



import com.tiantian.entity.Collect;

import java.util.List;

public interface CollectMapper {

//    显示所有信息
    List<Collect> findList();

    /**
     * 通过用户名查找
     *   %aa%
     * @param readerName
     * @return
     */
    List<Collect> findByName(String readerName);


}
