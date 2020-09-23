package com.tiantian.service.impl;

import com.tiantian.entity.Images;
import com.tiantian.mapper.ImagesMapper;
import com.tiantian.service.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/7/8
 * \* Time: 9:36
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Service
public class ImagesServiceImpl implements ImagesService {
    @Autowired
    private ImagesMapper imagesMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return imagesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Images record) {
        return imagesMapper.insert(record);
    }

    @Override
    public Images selectByPrimaryKey(Integer id) {
        return imagesMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Images> selectAll() {
        return imagesMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Images record) {
        return imagesMapper.updateByPrimaryKey(record);
    }
}