package com.sly.medicineshop.business.storage.controller;

import com.sly.medicineshop.business.storage.model.Goods;
import com.sly.medicineshop.business.storage.service.GoodsService;
import com.sly.medicineshop.result.BaseResult;
import com.sly.medicineshop.result.ResultStatus;
import lombok.extern.java.Log;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;

/**
 * 商品 controller
 *
 * @author SLY
 * @date 2020/9/30
 */
@Log
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @DubboReference
    private GoodsService goodsService;

    /**
     * 新增商品
     *
     * @param goods 商品
     * @author SLY
     * @date 2020/10/7
     */
    @RequestMapping("/addGoods")
    public BaseResult addGoods(Goods goods) {
        try {
            return goodsService.addGoods(goods);
        } catch (Exception e) {
            log.log(Level.SEVERE, "GoodsController-----addGoods异常:", e);
            return BaseResult.getInstance(ResultStatus.SAVE_FAILED);
        }
    }

    /**
     * 删除商品
     *
     * @param id 商品ID
     * @author SLY
     * @date 2020/10/7
     */
    @RequestMapping("/deleteGoods")
    public BaseResult deleteGoods(Integer id) {
        try {
            return goodsService.deleteGoods(id);
        } catch (Exception e) {
            log.log(Level.SEVERE, "GoodsController-----deleteGoods异常:", e);
            return BaseResult.getInstance(ResultStatus.DELETE_FAILED);
        }
    }

    /**
     * 修改商品
     *
     * @param goods 商品
     * @author SLY
     * @date 2020/10/7
     */
    @RequestMapping("/updateGoods")
    public BaseResult updateGoods(Goods goods) {
        try {
            return goodsService.updateGoods(goods);
        } catch (Exception e) {
            log.log(Level.SEVERE, "GoodsController-----updateGoods异常:", e);
            return BaseResult.getInstance(ResultStatus.UPDATE_FAILED);
        }
    }

    /**
     * 查询商品列表
     *
     * @param goods 商品
     * @author SLY
     * @date 2020/10/7
     */
    @RequestMapping("/findGoodsList")
    public BaseResult findGoodsList(Goods goods) {
        try {
            return goodsService.findGoodsList(goods);
        } catch (Exception e) {
            log.log(Level.SEVERE, "GoodsController-----findGoodsList异常:", e);
            return BaseResult.getInstance(ResultStatus.QUERY_FAILED);
        }
    }

    /**
     * 查询商品信息
     *
     * @param id 商品ID
     * @author SLY
     * @date 2020/10/7
     */
    @RequestMapping("/findGoodsDetail")
    public BaseResult findGoodsDetail(String id) {
        try {
            return goodsService.findGoodsDetail(id);
        } catch (Exception e) {
            log.log(Level.SEVERE, "GoodsController-----findGoodsDetail异常:", e);
            return BaseResult.getInstance(ResultStatus.QUERY_FAILED);
        }
    }
}
