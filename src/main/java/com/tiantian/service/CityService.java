package com.tiantian.service;

import com.tiantian.entity.City;

import java.util.List;

public interface CityService {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbggenerated Wed Aug 12 15:05:53 CST 2020
     */
    Boolean deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbggenerated Wed Aug 12 15:05:53 CST 2020
     */
    Boolean insert(City record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbggenerated Wed Aug 12 15:05:53 CST 2020
     */
    City selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbggenerated Wed Aug 12 15:05:53 CST 2020
     */
    List<City> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table city
     *
     * @mbggenerated Wed Aug 12 15:05:53 CST 2020
     */
    Boolean updateByPrimaryKey(City record);
}
