package com.sly.medicineshop.business.storage.model;

import com.alibaba.fastjson.JSONObject;
import com.sly.medicineshop.util.BaseModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 库存 model
 *
 * @author SLY
 * @date 2020/9/30
 */
@Data
public class Storage extends BaseModel {
    /** int(11) NOT NULL 库存ID */
    private Integer id;
    /** int(11) NULL 商品ID */
    private Integer goodsId;
    /** int(11) NULL 商品公库ID */
    private Integer goodsPid;
    /** varchar(100) NULL 库存数量 */
    private String goodsCount;
    /** varchar(100) NULL 商品名称 */
    private String goodsName;
    /** json NULL 换算单位 */
    private JSONObject conversionUnit;
    /** decimal(14,2) NULL 零售价 */
    private BigDecimal price;
    /** int(11) NULL 状态:0.无效 1.有效 */
    private Integer status;
}
