package com.tiantian.service.impl;

import com.tiantian.entity.Province;
import com.tiantian.mapper.ProvinceMapper;
import com.tiantian.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/8/12
 * \* Time: 15:14
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Service
public class ProvinceServiceImpl implements ProvinceService {

    @Autowired
    private ProvinceMapper provinceMapper;

    @Override
    public Boolean deleteByPrimaryKey(Integer id) {
        int i = provinceMapper.deleteByPrimaryKey(id);
        if(i!=0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Boolean insert(Province record) {
        int i = provinceMapper.insert(record);
        if(i!=0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Province selectByPrimaryKey(Integer id) {
        return provinceMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Province> selectAll() {
        return provinceMapper.selectAll();
    }

    @Override
    public Boolean updateByPrimaryKey(Province record) {
        int i = provinceMapper.updateByPrimaryKey(record);
        if(i!=0){
            return true;
        }else{
            return false;
        }
    }
}