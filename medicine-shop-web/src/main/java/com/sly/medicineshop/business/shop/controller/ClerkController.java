package com.sly.medicineshop.business.shop.controller;

import com.sly.medicineshop.business.shop.model.Clerk;
import com.sly.medicineshop.business.shop.service.ClerkService;
import com.sly.medicineshop.common.redis.service.RedisService;
import com.sly.medicineshop.result.BaseResult;
import com.sly.medicineshop.result.ResultStatus;
import com.sly.medicineshop.util.JwtUtils;
import lombok.extern.java.Log;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;

/**
 * 店员 controller
 *
 * @author SLY
 * @date 2020/9/30
 */
@Log
@RestController
@RequestMapping("/clerk")
public class ClerkController {

    @DubboReference
    private ClerkService clerkService;
    @DubboReference
    private RedisService redisService;

    /**
     * 新增店员
     *
     * @param clerk 店员
     * @author SLY
     * @date 2020/10/6
     */
    @RequestMapping("/addClerk")
    public BaseResult addClerk(Clerk clerk) {
        try {
            return clerkService.addClerk(clerk);
        } catch (Exception e) {
            log.log(Level.SEVERE, "ClerkController-----addClerk异常:", e);
            return BaseResult.getInstance(ResultStatus.SAVE_FAILED);
        }
    }

    /**
     * 删除店员
     *
     * @param id 店员ID
     * @author SLY
     * @date 2020/10/6
     */
    @RequestMapping("/deleteClerk")
    public BaseResult deleteClerk(Integer id) {
        try {
            return clerkService.deleteClerk(id);
        } catch (Exception e) {
            log.log(Level.SEVERE, "ClerkController-----deleteClerk异常:", e);
            return BaseResult.getInstance(ResultStatus.DELETE_FAILED);
        }
    }

    /**
     * 修改店员
     *
     * @param clerk 店员
     * @author SLY
     * @date 2020/10/6
     */
    @RequestMapping("/updateClerk")
    public BaseResult updateClerk(Clerk clerk) {
        try {
            return clerkService.updateClerk(clerk);
        } catch (Exception e) {
            log.log(Level.SEVERE, "ClerkController-----updateClerk异常:", e);
            return BaseResult.getInstance(ResultStatus.UPDATE_FAILED);
        }
    }

    /**
     * 查询店员列表
     *
     * @param clerk 店员
     * @author SLY
     * @date 2020/10/6
     */
    @RequestMapping("/findClerkList")
    public BaseResult findClerkList(Clerk clerk) {
        try {
            return clerkService.findClerkList(clerk);
        } catch (Exception e) {
            log.log(Level.SEVERE, "ClerkController-----findClerkList异常:", e);
            return BaseResult.getInstance(ResultStatus.QUERY_FAILED);
        }
    }

    /**
     * 查询店员信息
     *
     * @param id 店员ID
     * @author SLY
     * @date 2020/10/6
     */
    @RequestMapping("/findClerkDetail")
    public BaseResult findClerkDetail(Integer id) {
        try {
            return clerkService.findClerkDetail(id);
        } catch (Exception e) {
            log.log(Level.SEVERE, "ClerkController-----findClerkDetail异常:", e);
            return BaseResult.getInstance(ResultStatus.QUERY_FAILED);
        }
    }

    @RequestMapping("/login")
    public BaseResult login(Clerk clerk) {
        try {
            BaseResult result = clerkService.findByAccount(clerk.getAccount());
            Clerk existClerk = result.getData(Clerk.class);
            if (existClerk == null) {
                return BaseResult.getInstance(ResultStatus.LOGIN_INFORMATION_ERROR);
            }

            if (!clerk.getPassword().equals(existClerk.getPassword())) {
                return BaseResult.getInstance(ResultStatus.LOGIN_INFORMATION_ERROR);
            }

            redisService.putLoginToken(existClerk.getId(), JwtUtils.getToken(existClerk));
            existClerk.setPassword("");
            return BaseResult.getInstance(ResultStatus.LOGIN_SUCCESS, existClerk);
        } catch (Exception e) {
            log.log(Level.SEVERE, "ClerkController-----login异常:", e);
            return BaseResult.getInstance(ResultStatus.LOGIN_FAILED);
        }
    }
}
