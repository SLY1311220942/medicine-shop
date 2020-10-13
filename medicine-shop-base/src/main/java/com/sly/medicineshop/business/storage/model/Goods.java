package com.sly.medicineshop.business.storage.model;

import com.sly.medicineshop.util.BaseModel;
import lombok.Data;

/**
 * 商品 model
 *
 * @author SLY
 * @date 2020/9/30
 */
@Data
public class Goods extends BaseModel {
    /** int(11) NOT NULL 商品ID */
    private Integer id;
    /** int(11) NULL 商品公库ID */
    private Integer pid;
    /** varchar(100) NULL 商品名称 */
    private String name;
    /** int(11) NULL 商品类型 */
    private Integer type;
    /** int(11) NULL 状态:0.无效 1.有效 */
    private Integer status;
    /** int(11) NULL 店铺ID */
    private Integer shopId;
    /** varchar(32) NULL 商品编号 */
    private String goodsNumber;
}
