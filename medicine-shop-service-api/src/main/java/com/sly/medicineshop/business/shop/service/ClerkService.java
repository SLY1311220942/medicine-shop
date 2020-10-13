package com.sly.medicineshop.business.shop.service;

import com.sly.medicineshop.business.shop.model.Clerk;
import com.sly.medicineshop.result.BaseResult;

/**
 * 店员service接口
 *
 * @author SLY
 * @date 2020/9/30
 */
public interface ClerkService {
    /**
     * 新增店员
     *
     * @param clerk 店员
     * @return BaseResult
     * @author SLY
     * @date 2020/10/6
     */
    BaseResult addClerk(Clerk clerk);

    /**
     * 删除店员
     *
     * @param id 店员ID
     * @return BaseResult
     * @author SLY
     * @date 2020/10/6
     */
    BaseResult deleteClerk(Integer id);

    /**
     * 修改店员
     *
     * @param clerk 店员
     * @return BaseResult
     * @author SLY
     * @date 2020/10/6
     */
    BaseResult updateClerk(Clerk clerk);

    /**
     * 查询店员列表
     *
     * @param clerk 店员
     * @return BaseResult
     * @author SLY
     * @date 2020/10/6
     */
    BaseResult findClerkList(Clerk clerk);

    /**
     * 查询店员信息
     *
     * @param id 店员ID
     * @return BaseResult
     * @author SLY
     * @date 2020/10/6
     */
    BaseResult findClerkDetail(Integer id);

    /**
     * 根据账号店员查询信息
     *
     * @param account 店员账号
     * @return BaseResult
     * @author SLY
     * @date 2020/10/9
     */
    BaseResult findByAccount(String account);
}
