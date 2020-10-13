package com.sly.medicineshop.business.storage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sly.medicineshop.business.storage.model.Order;
import com.sly.medicineshop.config.mybatis.TablePartition;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单mapper
 *
 * @author SLY
 * @date 2020/10/6
 */
public interface OrderMapper {
    /**
     * 新增订单
     *
     * @param order 订单
     * @return int
     * @author SLY
     * @date 2020/10/7
     */
    int add(Order order);

    /**
     * 删除订单
     *
     * @param id 订单ID
     * @return int
     * @author SLY
     * @date 2020/10/7
     */
    int delete(String id);

    /**
     * 修改订单
     *
     * @param order 订单
     * @return int
     * @author SLY
     * @date 2020/10/7
     */
    int update(Order order);

    /**
     * 查询订单列表
     *
     * @param page  分页参数
     * @param order 订单
     * @return java.util.List
     * @author SLY
     * @date 2020/10/7
     */
    @TablePartition("order.shopId")
    List<Order> findOrderList(Page page, @Param("order") Order order);

    /**
     * 查询订单详情
     *
     * @param id 订单ID
     * @return com.sly.medicineshop.business.storage.model.Order
     * @author SLY
     * @date 2020/10/7
     */
    Order findOrderById(Integer id);
}
