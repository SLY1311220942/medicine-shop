package com.sly.medicineshop.common.redis.service.impl;

import com.sly.medicineshop.common.redis.constant.CacheConstant;
import com.sly.medicineshop.common.redis.helper.RedisHelper;
import com.sly.medicineshop.common.redis.service.RedisService;
import com.sly.medicineshop.result.BaseResult;
import com.sly.medicineshop.result.ResultStatus;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * redis service实现
 *
 * @author SLY
 * @date 2020/10/7
 */
@DubboService
public class RedisServiceImpl implements RedisService {
    @Resource
    private RedisHelper redisHelper;

    @Override
    public BaseResult putLoginToken(Integer clerkId, String token) {
        redisHelper.putStringValue(CacheConstant.LOGIN_TOKEN_PRE + clerkId, token, 7, TimeUnit.DAYS);
        return BaseResult.getInstance(ResultStatus.SUCCESS);
    }

    @Override
    public BaseResult getLoginToken(Integer clerkId) {
        String token = redisHelper.getStringValue(CacheConstant.LOGIN_TOKEN_PRE + clerkId);
        return BaseResult.getInstance(ResultStatus.SUCCESS, token);
    }
}
