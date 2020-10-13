package com.sly.medicineshop.business.storage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sly.medicineshop.business.storage.model.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品mapper
 *
 * @author SLY
 * @date 2020/10/6
 */
public interface GoodsMapper {

    /**
     * 新增商品
     *
     * @param goods 商品
     * @return int
     * @author SLY
     * @date 2020/10/7
     */
    int add(Goods goods);

    /**
     * 删除商品
     *
     * @param id 商品ID
     * @return int
     * @author SLY
     * @date 2020/10/7
     */
    int delete(Integer id);

    /**
     * 修改商品
     *
     * @param goods 商品
     * @return int
     * @author SLY
     * @date 2020/10/7
     */
    int update(Goods goods);

    /**
     * 查询商品列表
     *
     * @param page  分页参数
     * @param goods 商品
     * @return java.util.List
     * @author SLY
     * @date 2020/10/7
     */
    List<Goods> findGoodsList(Page page, @Param("goods") Goods goods);

    /**
     * 查询商品信息
     *
     * @param id 商品ID
     * @return com.sly.medicineshop.business.storage.model.Goods
     * @author SLY
     * @date 2020/10/7
     */
    Goods findGoodsById(String id);
}
