package com.sly.medicineshop.business.storage.service.impl;

import com.sly.medicineshop.business.storage.service.StorageService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * 库存service实现
 *
 * @author SLY
 * @date 2020/9/28
 */
@DubboService
public class StorageServiceImpl implements StorageService {

    @Override
    public Object test01() {
        return "测试成功！";
    }
}
