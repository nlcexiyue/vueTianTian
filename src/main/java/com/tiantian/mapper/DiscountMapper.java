package com.tiantian.mapper;


import com.tiantian.entity.Discount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DiscountMapper {
    //   List<Dicount> findAll();
    Boolean save(Discount discount);


    List<Discount> findList();
    Boolean updateStatus(@Param("id") Integer id, @Param("status") Integer Status);
    //   批量更改状态（软删除）
    Boolean batchUpdateStatus(String[] ids);


    Boolean deleteById(@Param("actId") Integer actId, @Param("actStatus") Integer actStatus);
    Discount findById(@Param("id") Integer id);
    Boolean update(Discount discount);

    /**
     * 通过活动名字查找
     * @param actName
     * @return
     */
    List<Discount> findByactName(String actName);


}
