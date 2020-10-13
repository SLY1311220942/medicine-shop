package com.sly.medicineshop.common.redis.service;

import com.sly.medicineshop.result.BaseResult;

/**
 * redis service接口
 *
 * @author SLY
 * @date 2020/10/7
 */
public interface RedisService {

    /**
     * 保存登录token
     *
     * @param clerkId 店员ID
     * @param token   登录token
     * @return BaseResult
     * @author SLY
     * @date 2020/10/7
     */
    BaseResult putLoginToken(Integer clerkId, String token);

    /**
     * 获取登录token
     *
     * @param clerkId 店员ID
     * @return BaseResult
     * @author SLY
     * @date 2020/10/7
     */
    BaseResult getLoginToken(Integer clerkId);
}
