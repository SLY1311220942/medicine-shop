package com.sly.medicineshop.business.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sly.medicineshop.business.shop.model.Clerk;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 店员mapper
 *
 * @author SLY
 * @date 2020/10/6
 */
public interface ClerkMapper {
    /**
     * 新增店员
     *
     * @param clerk 店员
     * @return int
     * @author SLY
     * @date 2020/10/6
     */
    int add(Clerk clerk);

    /**
     * 删除店员
     *
     * @param id 店员ID
     * @return int
     * @author SLY
     * @date 2020/10/6
     */
    int delete(Integer id);

    /**
     * 修改店员
     *
     * @param clerk 店员
     * @return int
     * @author SLY
     * @date 2020/10/6
     */
    int update(Clerk clerk);

    /**
     * 查询店员列表
     *
     * @param page  分页参数
     * @param clerk 店员
     * @return java.util.List
     * @author SLY
     * @date 2020/10/6
     */
    List<Clerk> findClerkList(Page page, @Param("clerk") Clerk clerk);

    /**
     * 查询店员信息
     *
     * @param id 店员ID
     * @return com.sly.medicineshop.business.shop.model.Clerk
     * @author SLY
     * @date 2020/10/6
     */
    Clerk findClerkById(Integer id);

    /**
     * 根据账号店员查询信息
     *
     * @param account 店员账号
     * @return com.sly.medicineshop.business.shop.model.Clerk
     * @author SLY
     * @date 2020/10/9
     */
    Clerk findByAccount(String account);
}
