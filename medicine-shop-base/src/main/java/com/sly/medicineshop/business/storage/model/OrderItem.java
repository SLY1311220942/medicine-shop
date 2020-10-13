package com.sly.medicineshop.business.storage.model;

import com.sly.medicineshop.util.BaseModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单内容 model
 *
 * @author SLY
 * @date 2020/9/30
 */
@Data
public class OrderItem extends BaseModel {
    /** int(11) NOT NULL 订单内容ID */
    private Integer id;
    /** int(11) NULL 订单ID */
    private Integer orderId;
    /** int(11) NULL 商品ID */
    private Integer goodsId;
    /** varchar(100) NULL 商品名称 */
    private String goodsName;
    /** decimal(10,0) NULL 商品单价 */
    private BigDecimal goodsPrice;
    /** decimal(10,0) NULL 商品数量 */
    private BigDecimal goodsCount;
    /** int(11) NULL 商品公库ID */
    private Integer goodsPid;
    /** decimal(10,0) NULL 商品总价 */
    private BigDecimal goodsTotalFee;
}
