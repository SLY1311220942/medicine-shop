package com.sly.medicineshop.business.storage.model;

import com.sly.medicineshop.util.BaseModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品批次库存 model
 *
 * @author SLY
 * @date 2020/9/30
 */
@Data
public class BatchStorage extends BaseModel {
    /** int(11) NOT NULL 库存批次ID */
    private Integer id;
    /** int(11) NULL 商品ID */
    private Integer goodsId;
    /** varchar(100) NULL 批次数量 */
    private String goodsCount;
    /** varchar(100) NULL 商品名称 */
    private String goodsName;
    /** varchar(24) NULL 批次编号 */
    private String batchNumber;
    /** decimal(14,2) NULL 出库价格 */
    private BigDecimal batchPrice;
    /** smallint(6) NULL 批次类型（1.入库 2.出库） */
    private Integer batchType;
    /** varchar(100) NULL 批次入库数量 */
    private String batchCount;
    /** decimal(10,0) NULL 批次总价格 */
    private BigDecimal batchTotalFee;
    /** int(11) NULL 出库批次ID */
    private Integer outBatchId;
    /** decimal(10,0) NULL 最小单位出库数量 */
    private BigDecimal miniUnitCount;
    /** int(11) NULL 商店ID */
    private Integer shopId;
    /** int(11) NULL 店员ID */
    private Integer clerkId;
    /** int(11) NULL 状态:0.无效 1.有效 */
    private Integer status;
}
