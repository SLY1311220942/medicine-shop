package com.sly.medicineshop.business.shop.model;

import com.sly.medicineshop.util.BaseModel;
import lombok.Data;

/**
 * 商店 model
 *
 * @author SLY
 * @date 2020/9/30
 */
@Data
public class Shop extends BaseModel {
    /** int(11) NOT NULL 店铺ID */
    private Integer id;
    /** varchar(32) NULL 店铺名称 */
    private String name;
    /** varchar(24) NULL 手机 */
    private String phone;
    /** varchar(24) NULL 电话 */
    private String telephone;
    /** varchar(240) NULL 地址 */
    private String address;
    /** int(11) NULL 状态:0.无效 1.有效 */
    private Integer status;
}
