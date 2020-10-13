package com.sly.medicineshop.util;

import java.io.Serializable;

/**
 * 基础model
 *
 * @author SLY
 * @date 2020/10/7
 */
public class BaseModel implements Serializable {
    private int pageNo = 1;
    private int pageSize = 10;
    private Integer currentShopId;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStartNum() {
        return (pageNo - 1) * pageSize;
    }

    public Integer getCurrentShopId() {
        return currentShopId;
    }

    public void setCurrentShopId(Integer currentShopId) {
        this.currentShopId = currentShopId;
    }
}
