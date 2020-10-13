package com.sly.medicineshop.util;

import java.math.BigDecimal;

/**
 * 库存单位
 *
 * @author SLY
 * @date 2020/9/30
 */
public class StorageUnit {

    /**
     * 单位名称
     */
    private String name;
    /**
     * 进位到上级需要的数量
     */
    private BigDecimal carryNumber;
    /**
     * 下级进位到本级需要的数量
     */
    private StorageUnit subUnit;

    private BigDecimal number = new BigDecimal("0");

    public StorageUnit() {
    }

    public StorageUnit(String name, BigDecimal carryNumber) {
        this.name = name;
        this.carryNumber = carryNumber;
    }

    public StorageUnit(String name, StorageUnit subUnit) {
        this.name = name;
        this.subUnit = subUnit;
    }

    public StorageUnit(String name, BigDecimal carryNumber, StorageUnit subUnit) {
        this.name = name;
        this.carryNumber = carryNumber;
        this.subUnit = subUnit;
    }


    public boolean isLastUnit() {
        return carryNumber == null || carryNumber.compareTo(new BigDecimal("1")) <= 0;
    }

    public boolean isBeginUnit() {
        return subUnit == null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCarryNumber() {
        return carryNumber;
    }

    public void setCarryNumber(BigDecimal carryNumber) {
        this.carryNumber = carryNumber;
    }

    public StorageUnit getSubUnit() {
        return subUnit;
    }

    public void setSubUnit(StorageUnit subUnit) {
        this.subUnit = subUnit;
    }

    public BigDecimal getNumber() {
        return number;
    }

    public void setNumber(BigDecimal number) {
        this.number = number;
    }


}
