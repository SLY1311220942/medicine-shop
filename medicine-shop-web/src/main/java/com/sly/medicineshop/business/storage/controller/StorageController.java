package com.sly.medicineshop.business.storage.controller;

import com.sly.medicineshop.business.storage.service.StorageService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 库存controller
 *
 * @author SLY
 * @date 2020/9/28
 */
@RestController
@RequestMapping("/storage")
public class StorageController {
    @DubboReference
    private StorageService storageService;

    /**
     * 测试
     *
     * @author SLY
     * @date 2020/9/28
     * @return java.lang.Object
     */
    @RequestMapping("/test01")
    public Object test01(){
        return storageService.test01();
    }
}
