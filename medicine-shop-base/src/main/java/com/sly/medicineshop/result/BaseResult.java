package com.sly.medicineshop.result;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 公共返回对象
 *
 * @author sly
 * @date 2020/9/30
 */
public class BaseResult extends HashMap<String, Object> {

    private static final long serialVersionUID = -1937095802242495571L;

    public boolean isSuccess() {
        if (ResultStatus.SUCCESS.getStatus() == getStatus()) {
            return true;
        }
        return false;
    }

    public static BaseResult getInstance() {
        return new BaseResult();
    }

    public static BaseResult getInstance(Integer status) {
        return new BaseResult(status);
    }

    public static BaseResult getInstance(Integer status, String message) {
        return new BaseResult(status, message);
    }

    public static BaseResult getInstance(IStatus status, String message) {
        return new BaseResult(status, message);
    }

    public static BaseResult getInstance(IStatus status) {
        return new BaseResult(status);
    }

    public static BaseResult getInstance(IStatus status, Object data) {
        return new BaseResult(status, "data", data);
    }

    public static BaseResult getInstance(Integer status, String message, String key, Object value) {
        return new BaseResult(status, message, key, value);
    }

    public static BaseResult getInstance(IStatus status, String key, Object value) {
        return new BaseResult(status, key, value);
    }

    public static BaseResult getInstance(Integer status, String message, Map<String, Object> dataMap) {
        return new BaseResult(status, message, dataMap);
    }

    public static BaseResult getInstance(IStatus status, Map<String, Object> dataMap) {
        return new BaseResult(status, dataMap);
    }

    public static BaseResult getInstance(Integer status, String message, Integer total, List<?> rows) {
        return new BaseResult(status, message, total, rows);
    }

    public static BaseResult getInstance(IStatus status, Integer total, List<?> rows) {
        return new BaseResult(status, total, rows);
    }

    public static BaseResult getInstance(IStatus status, Long total, List<?> rows) {
        return new BaseResult(status, total, rows);
    }

    /**
     * 空参构造方法
     */
    public BaseResult() {

    }

    /**
     * 构造方法
     *
     * @param status 状态
     */
    public BaseResult(Integer status) {
        setStatus(status);
    }

    /**
     * 构造方法
     *
     * @param status  状态
     * @param message 消息
     */
    public BaseResult(Integer status, String message) {
        setStatus(status);
        setMessage(message);
    }

    /**
     * 构造方法
     *
     * @param status  状态
     * @param message 消息
     */
    public BaseResult(IStatus status, String message) {
        setStatus(status.getStatus());
        setMessage(message);
    }

    /**
     * 构造方法
     *
     * @param status 状态
     */
    public BaseResult(IStatus status) {
        setStatus(status.getStatus());
        setMessage(status.getMessage());
    }

    /**
     * 构造方法
     *
     * @param status  状态
     * @param message 消息
     * @param key     键
     * @param value   值
     */
    public BaseResult(Integer status, String message, String key, Object value) {
        setStatus(status);
        setMessage(message);
        put(key, value);
    }

    /**
     * 构造方法
     *
     * @param status 状态
     * @param key    键
     * @param value  值
     */
    public BaseResult(IStatus status, String key, Object value) {
        setStatus(status.getStatus());
        setMessage(status.getMessage());
        put(key, value);
    }

    /**
     * 构造方法
     *
     * @param status  状态
     * @param message 消息
     * @param dataMap 数据map
     */
    public BaseResult(Integer status, String message, Map<String, Object> dataMap) {
        setDataMap(dataMap);
        setStatus(status);
        setMessage(message);
    }

    /**
     * 构造方法
     *
     * @param status  状态
     * @param dataMap 数据map
     */
    public BaseResult(IStatus status, Map<String, Object> dataMap) {
        setDataMap(dataMap);
        setStatus(status.getStatus());
        setMessage(status.getMessage());
    }

    /**
     * 构造方法
     *
     * @param status  状态
     * @param message 消息
     * @param total   总数
     * @param rows    列表数据
     */
    public BaseResult(Integer status, String message, Integer total, List<?> rows) {
        setStatus(status);
        setMessage(message);
        setTotal(total);
        setRows(rows);
    }

    /**
     * 构造方法
     *
     * @param status 状态
     * @param total  总数
     * @param rows   列表数据
     */
    public BaseResult(IStatus status, Integer total, List<?> rows) {
        setStatus(status.getStatus());
        setMessage(status.getMessage());
        setTotal(total);
        setRows(rows);
    }

    /**
     * 构造方法
     *
     * @param status 状态
     * @param total  总数
     * @param rows   列表数据
     */
    public BaseResult(IStatus status, Long total, List<?> rows) {
        setStatus(status.getStatus());
        setMessage(status.getMessage());
        setTotal(total.intValue());
        setRows(rows);
    }

    /**
     * 获取返回状态
     *
     * @return java.lang.Integer
     * @author sly
     */
    public Integer getStatus() {
        return (Integer) get("status");
    }

    /**
     * 设置返回状态
     *
     * @param status 状态
     * @author sly
     */
    public BaseResult setStatus(Integer status) {
        put("status", status);
        return this;
    }

    /**
     * 获取返回信息
     *
     * @return java.lang.String
     * @author sly
     */
    public String getMessage() {
        return (String) get("message");
    }

    /**
     * 设置返回信息
     *
     * @param message 消息
     * @author sly
     */
    public BaseResult setMessage(String message) {
        put("message", message);
        return this;
    }

    /**
     * 获取返回分页数据
     *
     * @return java.util.List
     * @author sly
     */
    public List<?> getRows() {
        return (List<?>) get("rows");
    }

    /**
     * 设置返回分页数据
     *
     * @param rows 列表数据
     * @author sly
     */
    public BaseResult setRows(List<?> rows) {
        put("rows", rows);
        return this;
    }

    /**
     * 获取返回分页数据数量
     *
     * @return java.lang.Integer
     * @author sly
     */
    public Integer getTotal() {
        return (Integer) get("total");
    }

    /**
     * 设置返回分页数据数量
     *
     * @param total 总数
     * @author sly
     */
    public BaseResult setTotal(Integer total) {
        put("total", total);
        return this;
    }

    /**
     * 设置数据map
     *
     * @param dataMap 数据map
     * @author sly
     */
    public BaseResult setDataMap(Map<String, Object> dataMap) {
        Set<String> keySet = dataMap.keySet();
        for (String key : keySet) {
            this.put(key, dataMap.get(key));
        }
        return this;
    }

    /**
     * 获取结果里的对象
     *
     * @param key   键
     * @param clazz 类型
     * @return com.sly.medicineshop.result.BaseResult
     * @author sly
     */
    public <T> T getResultObject(String key, Class<T> clazz) {
        return JSON.parseObject(JSON.toJSONString(get(key)), clazz);
    }

    /**
     * 获取结果里的list
     *
     * @param key   键
     * @param clazz 类型
     * @return com.sly.medicineshop.result.BaseResult
     * @author sly
     */
    public <T> List<T> getResultArray(String key, Class<T> clazz) {
        return JSON.parseArray(JSON.toJSONString(get(key)), clazz);
    }

    /**
     * @param key   键
     * @param value 值
     * @return com.sly.medicineshop.result.BaseResult
     * @author sly
     */
    public BaseResult append(String key, Object value) {
        put(key, value);
        return this;
    }

    /**
     * 获取data
     *
     * @return java.lang.Object
     * @author SLY
     * @date 2020/10/7
     */
    public Object getData() {
        return get("data");
    }

    /**
     * 获取data
     *
     * @param clazz 类型
     * @return java.lang.Class
     * @author SLY
     * @date 2020/10/7
     */
    public <T> T getData(Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(get("data"), clazz);
    }
}
