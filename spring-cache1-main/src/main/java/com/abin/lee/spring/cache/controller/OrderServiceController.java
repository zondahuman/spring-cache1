package com.abin.lee.spring.cache.controller;

import com.abin.lee.spring.cache.common.util.JsonUtil;
import com.abin.lee.spring.cache.model.OrderInfo;
import com.abin.lee.spring.cache.service.OrderInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by abin on 2017/9/3 2017/9/3.
 * spring-cache1
 * com.abin.lee.spring.cache.controller
 */
@Controller
@RequestMapping("/order")
public class OrderServiceController {

    protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Resource
    OrderInfoService orderInfoService;

    @RequestMapping(value = "/insert", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String insert(@RequestParam(value = "age") Integer age, @RequestParam(value = "name") String name) {
        String result = "FAILURE";
        try {
            OrderInfo record = new OrderInfo();
            record.setName(name);
            record.setAge(age);
            record.setCreateTime(new Date());
            record.setUpdateTime(new Date());
            record.setVersion(0);
            this.orderInfoService.insert(record);
            result = "SUCCESS";
        } catch (Exception e) {
            LOGGER.error("e={}", e);
            result = "FAILURE";
        }
        return result;
    }

    @RequestMapping(value = "/findById", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public OrderInfo findById(@RequestParam(value = "id") Integer id) {
        OrderInfo orderInfo = null;
        try {
            orderInfo = this.orderInfoService.findById(id);
        } catch (Exception e) {
            LOGGER.error("e={}", e);
        }
        return orderInfo;
    }

    @RequestMapping(value = "/findAll", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<OrderInfo> findAll() {
        List<OrderInfo> list = null;
        try {
            list = this.orderInfoService.findAll();
        } catch (Exception e) {
            LOGGER.error("e={}", e);
        }
        return list;
    }

    @RequestMapping(value = "/deleteById", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String deleteById(@RequestParam(value = "id") Integer id) {
        String result = "FAILURE";
        try {
            this.orderInfoService.deleteById(id);
            result = "SUCCESS";
        } catch (Exception e) {
            LOGGER.error("e={}", e);
            result = "FAILURE";
        }
        return result;
    }


    @RequestMapping(value = "/update", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String update(@RequestParam(value = "id") Integer id, @RequestParam(value = "age") Integer age, @RequestParam(value = "name") String name) {
        String result = "FAILURE";
        Integer result1 = 1;
        try {
            OrderInfo record = new OrderInfo();
            record.setId(id);
            record.setName(name);
            record.setAge(age);
            record.setCreateTime(new Date());
            record.setUpdateTime(new Date());
            record.setVersion(0);
            this.orderInfoService.update(record);
            result = "SUCCESS";
        } catch (Exception e) {
            LOGGER.error("e={}", e);
            result = "FAILURE";
        }
        return result;
    }





}
