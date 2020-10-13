package com.sly.medicineshop.business.storage.controller;

import com.sly.medicineshop.business.storage.model.Order;
import com.sly.medicineshop.business.storage.service.OrderService;
import com.sly.medicineshop.result.BaseResult;
import com.sly.medicineshop.result.ResultStatus;
import com.sly.medicineshop.util.BaseModel;
import lombok.extern.java.Log;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;

/**
 * 订单 controller
 *
 * @author SLY
 * @date 2020/9/30
 */
@Log
@RestController
@RequestMapping("/order")
public class OrderController {

    @DubboReference
    private OrderService orderService;

    /**
     * 新增订单
     *
     * @param order 订单
     * @author SLY
     * @date 2020/10/7
     */
    @RequestMapping("/addOrder")
    public BaseResult addOrder(Order order) {
        try {
            return orderService.addOrder(order);
        } catch (Exception e) {
            log.log(Level.SEVERE, "OrderController-----addOrder异常:", e);
            return BaseResult.getInstance(ResultStatus.SAVE_FAILED);
        }
    }

    /**
     * 删除订单
     *
     * @param id 订单ID
     * @author SLY
     * @date 2020/10/7
     */
    @RequestMapping("/deleteOrder")
    public BaseResult deleteOrder(String id) {
        try {
            return orderService.deleteOrder(id);
        } catch (Exception e) {
            log.log(Level.SEVERE, "OrderController-----deleteOrder异常:", e);
            return BaseResult.getInstance(ResultStatus.DELETE_FAILED);
        }
    }

    /**
     * 修改订单
     *
     * @param order 订单
     * @author SLY
     * @date 2020/10/7
     */
    @RequestMapping("/updateOrder")
    public BaseResult updateOrder(Order order) {
        try {
            return orderService.updateOrder(order);
        } catch (Exception e) {
            log.log(Level.SEVERE, "OrderController-----updateOrder异常:", e);
            return BaseResult.getInstance(ResultStatus.UPDATE_FAILED);
        }
    }

    /**
     * 查询订单列表
     *
     * @param order 订单
     * @author SLY
     * @date 2020/10/7
     */
    @RequestMapping("/findOrderList")
    public BaseResult findOrderList(Order order) {
        try {
            return orderService.findOrderList(order);
        } catch (Exception e) {
            log.log(Level.SEVERE, "OrderController-----findOrderList异常:", e);
            return BaseResult.getInstance(ResultStatus.UPDATE_FAILED);
        }
    }

    /**
     * 查询订单详情
     *
     * @param id 订单ID
     * @author SLY
     * @date 2020/10/7
     */
    @RequestMapping("/findOrderDetail")
    public BaseResult findOrderDetail(Integer id) {
        try {
            return orderService.findOrderDetail(id);
        } catch (Exception e) {
            log.log(Level.SEVERE, "OrderController-----findOrderDetail异常:", e);
            return BaseResult.getInstance(ResultStatus.UPDATE_FAILED);
        }
    }
}
