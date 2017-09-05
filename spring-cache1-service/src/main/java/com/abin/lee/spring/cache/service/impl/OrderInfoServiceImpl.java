package com.abin.lee.spring.cache.service.impl;

import com.abin.lee.spring.cache.common.util.JsonUtil;
import com.abin.lee.spring.cache.dao.OrderInfoMapper;
import com.abin.lee.spring.cache.model.OrderInfo;
import com.abin.lee.spring.cache.model.OrderInfoExample;
import com.abin.lee.spring.cache.service.OrderInfoService;
import com.google.common.primitives.Ints;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Resource
    OrderInfoMapper orderInfoMapper;

    @Override
//    @CachePut(value="OrderInfo", key = "#id")
    @CachePut(value="OrderInfo", key = "#record.id")
    public OrderInfo insert(OrderInfo record) {
        this.orderInfoMapper.insert(record);
        LOGGER.info("record= " + JsonUtil.toJson(record));
        return record;
    }


    @Override
    @Cacheable(value="OrderInfoList")
    public List<OrderInfo> findAll() {
        OrderInfoExample example = new OrderInfoExample();
        List<OrderInfo> list = this.orderInfoMapper.selectByExample(example);
        return list;
    }

    @Override
    @Cacheable(value="OrderInfo", key = "#id")
    public OrderInfo findById(final Integer id) {
        OrderInfo orderInfo = this.orderInfoMapper.selectByPrimaryKey(id);
       return orderInfo;
    }

    @Override
    @CacheEvict(value = "OrderInfo", key = "#id") //移除指定key的数据
    public OrderInfo deleteById(final Integer id) {
        this.orderInfoMapper.deleteByPrimaryKey(id);
        return null;
    }


    @Override
    @CachePut(value="OrderInfo", key = "#record.id")
    public OrderInfo update(OrderInfo record) {
        this.orderInfoMapper.updateByPrimaryKey(record);
        return record;
    }
}
