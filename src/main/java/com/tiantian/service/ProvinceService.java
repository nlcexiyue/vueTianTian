package com.tiantian.service;

import com.tiantian.entity.Province;

import java.util.List;

public interface ProvinceService {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table province
     *
     * @mbggenerated Wed Aug 12 15:05:53 CST 2020
     */
    Boolean deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table province
     *
     * @mbggenerated Wed Aug 12 15:05:53 CST 2020
     */
    Boolean insert(Province record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table province
     *
     * @mbggenerated Wed Aug 12 15:05:53 CST 2020
     */
    Province selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table province
     *
     * @mbggenerated Wed Aug 12 15:05:53 CST 2020
     */
    List<Province> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table province
     *
     * @mbggenerated Wed Aug 12 15:05:53 CST 2020
     */
    Boolean updateByPrimaryKey(Province record);
}
