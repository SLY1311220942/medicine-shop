package com.sly.medicineshop.business.storage.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sly.medicineshop.business.storage.mapper.OrderMapper;
import com.sly.medicineshop.business.storage.model.Order;
import com.sly.medicineshop.business.storage.service.OrderService;
import com.sly.medicineshop.result.BaseResult;
import com.sly.medicineshop.result.ResultStatus;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 订单service实现
 *
 * @author SLY
 * @date 2020/9/30
 */
@DubboService
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Override
    public BaseResult addOrder(Order order) {
        orderMapper.add(order);
        return BaseResult.getInstance(ResultStatus.SAVE_SUCCESS);
    }

    @Override
    public BaseResult deleteOrder(String id) {
        orderMapper.delete(id);
        return BaseResult.getInstance(ResultStatus.DELETE_SUCCESS);
    }

    @Override
    public BaseResult updateOrder(Order order) {
        orderMapper.update(order);
        return BaseResult.getInstance(ResultStatus.UPDATE_SUCCESS);
    }

    @Override
    public BaseResult findOrderList(Order order) {
        Page<Order> page = new Page<>(order.getPageNo(), order.getPageSize());
        List<Order> list = orderMapper.findOrderList(page, order);
        return BaseResult.getInstance(ResultStatus.QUERY_SUCCESS, page.getTotal(), list);
    }

    @Override
    public BaseResult findOrderDetail(Integer id) {
        Order order = orderMapper.findOrderById(id);
        return BaseResult.getInstance(ResultStatus.QUERY_SUCCESS, order);
    }
}
