package com.tiantian.mapper;

import com.tiantian.entity.Meteorological;

import java.util.List;

public interface MeteorologicalMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table meteorological
     *
     * @mbggenerated Wed Aug 12 15:05:53 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table meteorological
     *
     * @mbggenerated Wed Aug 12 15:05:53 CST 2020
     */
    int insert(Meteorological record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table meteorological
     *
     * @mbggenerated Wed Aug 12 15:05:53 CST 2020
     */
    Meteorological selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table meteorological
     *
     * @mbggenerated Wed Aug 12 15:05:53 CST 2020
     */
    List<Meteorological> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table meteorological
     *
     * @mbggenerated Wed Aug 12 15:05:53 CST 2020
     */
    int updateByPrimaryKey(Meteorological record);
}