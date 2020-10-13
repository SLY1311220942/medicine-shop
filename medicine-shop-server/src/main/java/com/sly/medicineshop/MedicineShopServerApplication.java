package com.sly.medicineshop;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类型
 *
 * @author SLY
 * @date 2020/9/28
 */
@EnableDubbo
@SpringBootApplication
@MapperScan("com.sly.medicineshop.*.*.mapper")
public class MedicineShopServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedicineShopServerApplication.class, args);
    }

}
