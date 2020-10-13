package com.sly.medicineshop.business.storage.service;

import com.sly.medicineshop.business.storage.model.Order;
import com.sly.medicineshop.result.BaseResult;

/**
 * 订单service接口
 *
 * @author SLY
 * @date 2020/9/30
 */
public interface OrderService {
    /**
     * 新增订单
     *
     * @param order 订单
     * @return BaseResult
     * @author SLY
     * @date 2020/10/7
     */
    BaseResult addOrder(Order order);

    /**
     * 删除订单
     *
     * @param id 订单ID
     * @return BaseResult
     * @author SLY
     * @date 2020/10/7
     */
    BaseResult deleteOrder(String id);

    /**
     * 修改订单
     *
     * @param order 订单
     * @return BaseResult
     * @author SLY
     * @date 2020/10/7
     */
    BaseResult updateOrder(Order order);

    /**
     * 查询订单列表
     *
     * @param order 订单
     * @return BaseResult
     * @author SLY
     * @date 2020/10/7
     */
    BaseResult findOrderList(Order order);

    /**
     * 查询订单详情
     *
     * @param id 订单ID
     * @return BaseResult
     * @author SLY
     * @date 2020/10/7
     */
    BaseResult findOrderDetail(Integer id);
}
