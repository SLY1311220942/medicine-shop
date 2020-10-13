package com.sly.medicineshop;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author SLY
 * @date 2020/9/28
 */
@EnableDubbo
@SpringBootApplication
public class MedicineShopWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedicineShopWebApplication.class, args);
    }

}
