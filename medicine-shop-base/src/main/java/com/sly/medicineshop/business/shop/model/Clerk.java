package com.sly.medicineshop.business.shop.model;

import com.sly.medicineshop.util.BaseModel;
import lombok.Data;

/**
 * 店员 model
 *
 * @author SLY
 * @date 2020/9/30
 */
@Data
public class Clerk extends BaseModel {
    /** int(11) NOT NULL 店员ID */
    private Integer id;
    /** varchar(32) NULL 账号 */
    private String account;
    /** varchar(32) NULL 密码 */
    private String password;
    /** varchar(32) NULL 名称 */
    private String name;
    /** varchar(24) NULL 电话 */
    private String phone;
    /** varchar(64) NULL 邮箱 */
    private String email;
    /** int(11) NULL 店铺ID */
    private Integer shopId;
    /** int(11) NULL 状态:0.无效 1.有效 */
    private Integer status;
}
