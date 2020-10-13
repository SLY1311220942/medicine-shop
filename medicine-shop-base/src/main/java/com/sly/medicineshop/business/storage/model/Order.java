package com.sly.medicineshop.business.storage.model;

import com.sly.medicineshop.util.BaseModel;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单 model
 *
 * @author SLY
 * @date 2020/9/30
 */
@Data
public class Order extends BaseModel {
    /** int(11) NOT NULL 订单ID */
    private Integer id;
    /** varchar(32) NULL 订单编号 */
    private String orderNumber;
    /** int(11) NULL 店员ID */
    private Integer clerkId;
    /** int(11) NULL 店铺ID */
    private Integer shopId;
    /** datetime NULL 订单时间 */
    private LocalDateTime orderTime;
    /** decimal(10,0) NULL 订单实际价格 */
    private BigDecimal orderPrice;
    /** decimal(10,0) NULL 折扣金额 */
    private BigDecimal discountAmount;
    /** decimal(10,0) NULL 原价 */
    private BigDecimal originalPrice;
    /** int(11) NULL 状态:0.无效 1.有效 */
    private Integer status;

    /* 扩展字段 */
    /** 订单起始时间 */
    private String orderTimeStart;
    /** 订单截止时间 */
    private String orderTimeEnd;

}
