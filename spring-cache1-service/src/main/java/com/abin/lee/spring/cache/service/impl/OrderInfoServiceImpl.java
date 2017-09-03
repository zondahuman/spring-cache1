package com.abin.lee.spring.cache.service.impl;

import com.abin.lee.spring.cache.dao.OrderInfoMapper;
import com.abin.lee.spring.cache.model.OrderInfo;
import com.abin.lee.spring.cache.model.OrderInfoExample;
import com.abin.lee.spring.cache.service.OrderInfoService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by abin on 2017/9/3 2017/9/3.
 * spring-cache1
 * com.abin.lee.spring.cache.service
 */
@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Resource
    OrderInfoMapper orderInfoMapper;

    @Override
    public int insert(Integer age, String name) {
        OrderInfo record = new OrderInfo();
        record.setName(name);
        record.setAge(age);
        record.setCreateTime(new Date());
        record.setUpdateTime(new Date());
        record.setVersion(0);
        return this.orderInfoMapper.insert(record);
    }


    @Override
    @Cacheable(value="findAll")
    public List<OrderInfo> findAll() {
        OrderInfoExample example = new OrderInfoExample();
        List<OrderInfo> list = this.orderInfoMapper.selectByExample(example);
        return list;
    }

    @Override
    @Cacheable(value="findById")
    public OrderInfo findById(Integer id) {
        OrderInfo orderInfo = this.orderInfoMapper.selectByPrimaryKey(id);
       return orderInfo;
    }




}
