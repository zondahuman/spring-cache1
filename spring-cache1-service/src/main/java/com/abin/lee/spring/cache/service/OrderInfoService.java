package com.abin.lee.spring.cache.service;

import com.abin.lee.spring.cache.model.OrderInfo;

import java.util.List;

/**
 * Created by abin on 2017/9/3 2017/9/3.
 * spring-cache1
 * com.abin.lee.spring.cache.service
 */
public interface OrderInfoService {

    OrderInfo insert(OrderInfo record);

    List<OrderInfo> findAll();

    OrderInfo findById(Integer id);

    OrderInfo deleteById(Integer id) ;

    OrderInfo update(OrderInfo record);
}
