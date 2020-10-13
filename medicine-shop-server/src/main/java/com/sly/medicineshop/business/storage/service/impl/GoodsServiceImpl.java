package com.sly.medicineshop.business.storage.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sly.medicineshop.business.storage.mapper.GoodsMapper;
import com.sly.medicineshop.business.storage.model.Goods;
import com.sly.medicineshop.business.storage.service.GoodsService;
import com.sly.medicineshop.result.BaseResult;
import com.sly.medicineshop.result.ResultStatus;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品service实现
 *
 * @author SLY
 * @date 2020/9/30
 */
@DubboService
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public BaseResult addGoods(Goods goods) {
        goodsMapper.add(goods);
        return BaseResult.getInstance(ResultStatus.SAVE_SUCCESS);
    }

    @Override
    public BaseResult deleteGoods(Integer id) {
        goodsMapper.delete(id);
        return BaseResult.getInstance(ResultStatus.DELETE_SUCCESS);
    }

    @Override
    public BaseResult updateGoods(Goods goods) {
        goodsMapper.update(goods);
        return BaseResult.getInstance(ResultStatus.UPDATE_SUCCESS);
    }

    @Override
    public BaseResult findGoodsList(Goods goods) {
        Page<Goods> page = new Page<>(goods.getPageNo(), goods.getPageSize());
        List<Goods> list = goodsMapper.findGoodsList(page, goods);
        return BaseResult.getInstance(ResultStatus.QUERY_SUCCESS, page.getTotal(), list);
    }

    @Override
    public BaseResult findGoodsDetail(String id) {
        Goods goods = goodsMapper.findGoodsById(id);
        return BaseResult.getInstance(ResultStatus.QUERY_SUCCESS, goods);
    }
}
