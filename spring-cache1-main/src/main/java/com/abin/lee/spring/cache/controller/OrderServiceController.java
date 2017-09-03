package com.abin.lee.spring.cache.controller;

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
import java.util.List;

/**
 * Created by abin on 2017/9/3 2017/9/3.
 * spring-cache1
 * com.abin.lee.spring.cache.controller
 */
@Controller
@RequestMapping("/order")
public class OrderServiceController {

    protected final static Logger logger = LoggerFactory.getLogger(OrderServiceController.class);

    @Resource
    OrderInfoService orderInfoService;

    @RequestMapping(value = "/insert", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String insert(@RequestParam(value = "age") Integer age, @RequestParam(value = "name") String name) {
        String result = "FIAILURE";
        try {
            this.orderInfoService.insert(age, name);
            result = "SUCCESS";
        } catch (Exception e) {
            logger.error("e={}", e);
            result = "FIAILURE";
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
            logger.error("e={}", e);
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
            logger.error("e={}", e);
        }
        return list;
    }




}
