package com.sly.medicineshop.result;

/**
 * 返回状态接口
 *
 * @author sly
 * @date 2020/9/30
 */
public interface IStatus {
    /**
     * 获取状态
     */
    public int getStatus();

    /**
     * 获取信息
     */
    public String getMessage();

    /**
     * 获取名称
     */
    public String getName();
}
