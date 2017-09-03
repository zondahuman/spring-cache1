package com.abin.lee.spring.cache.service.impl;

import com.abin.lee.spring.cache.dao.OrderInfoMapper;
import com.abin.lee.spring.cache.model.OrderInfo;
import com.abin.lee.spring.cache.model.OrderInfoExample;
import com.abin.lee.spring.cache.service.OrderInfoService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
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
    @CachePut(value="OrderInfo", key = "#record.id")
    public int insert(OrderInfo record) {
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
    @Cacheable(value="OrderInfo", key = "#id")
    public OrderInfo findById(Integer id) {
        OrderInfo orderInfo = this.orderInfoMapper.selectByPrimaryKey(id);
       return orderInfo;
    }

    @Override
    @CacheEvict(value = "OrderInfo", key = "#id") //移除指定key的数据
    public int deleteById(Integer id) {
        this.orderInfoMapper.deleteByPrimaryKey(id);
        return 0;
    }
}
