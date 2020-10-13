package com.sly.medicineshop.business.shop.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sly.medicineshop.business.shop.mapper.ClerkMapper;
import com.sly.medicineshop.business.shop.model.Clerk;
import com.sly.medicineshop.business.shop.service.ClerkService;
import com.sly.medicineshop.result.BaseResult;
import com.sly.medicineshop.result.ResultStatus;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 店员service实现
 *
 * @author SLY
 * @date 2020/9/30
 */
@DubboService
public class ClerkServiceImpl implements ClerkService {

    @Resource
    private ClerkMapper clerkMapper;

    @Override
    public BaseResult addClerk(Clerk clerk) {
        clerkMapper.add(clerk);
        return BaseResult.getInstance(ResultStatus.SAVE_SUCCESS);
    }

    @Override
    public BaseResult deleteClerk(Integer id) {
        clerkMapper.delete(id);
        return BaseResult.getInstance(ResultStatus.DELETE_SUCCESS);
    }

    @Override
    public BaseResult updateClerk(Clerk clerk) {
        clerkMapper.update(clerk);
        return BaseResult.getInstance(ResultStatus.UPDATE_SUCCESS);
    }

    @Override
    public BaseResult findClerkList(Clerk clerk) {
        Page<Clerk> page = new Page<>(clerk.getPageNo(), clerk.getPageSize());
        List<Clerk> list = clerkMapper.findClerkList(page, clerk);
        return BaseResult.getInstance(ResultStatus.QUERY_SUCCESS, page.getTotal(), list);
    }

    @Override
    public BaseResult findClerkDetail(Integer id) {
        Clerk clerk = clerkMapper.findClerkById(id);
        return BaseResult.getInstance(ResultStatus.QUERY_SUCCESS, clerk);
    }

    @Override
    public BaseResult findByAccount(String account) {
        Clerk clerk = clerkMapper.findByAccount(account);
        return BaseResult.getInstance(ResultStatus.QUERY_SUCCESS, clerk);
    }
}
