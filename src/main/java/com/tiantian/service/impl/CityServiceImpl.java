package com.tiantian.service.impl;

import com.tiantian.entity.City;
import com.tiantian.mapper.CityMapper;
import com.tiantian.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/8/12
 * \* Time: 15:26
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityMapper cityMapper;

    @Override
    public Boolean deleteByPrimaryKey(Integer id) {
        int i = cityMapper.deleteByPrimaryKey(id);
        if(i!=0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Boolean insert(City record) {
        int i = cityMapper.insert(record);
        if(i!=0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public City selectByPrimaryKey(Integer id) {
        return cityMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<City> selectAll() {
        return cityMapper.selectAll();
    }

    @Override
    public Boolean updateByPrimaryKey(City record) {
        int i = cityMapper.updateByPrimaryKey(record);
        if(i!=0){
            return true;
        }else{
            return false;
        }
    }
}