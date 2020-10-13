package com.sly.medicineshop.business.storage.service;

import com.sly.medicineshop.business.storage.model.Goods;
import com.sly.medicineshop.result.BaseResult;

/**
 * 商品service接口
 *
 * @author SLY
 * @date 2020/9/30
 */
public interface GoodsService {
    /**
     * 新增商品
     *
     * @param goods 商品
     * @return BaseResult
     * @author SLY
     * @date 2020/10/7
     */
    BaseResult addGoods(Goods goods);

    /**
     * 删除商品
     *
     * @param id 商品ID
     * @return BaseResult
     * @author SLY
     * @date 2020/10/7
     */
    BaseResult deleteGoods(Integer id);

    /**
     * 修改商品
     *
     * @param goods 商品
     * @return BaseResult
     * @author SLY
     * @date 2020/10/7
     */
    BaseResult updateGoods(Goods goods);

    /**
     * 查询商品列表
     *
     * @param goods 商品
     * @return BaseResult
     * @author SLY
     * @date 2020/10/7
     */
    BaseResult findGoodsList(Goods goods);

    /**
     * 查询商品信息
     *
     * @param id 商品ID
     * @return BaseResult
     * @author SLY
     * @date 2020/10/7
     */
    BaseResult findGoodsDetail(String id);
}
