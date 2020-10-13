package com.sly.medicineshop.config.mybatis;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisPlus 配置
 *
 * @author SLY
 * @date 2020/10/7
 */
@Configuration
@ConditionalOnClass(value = {PaginationInterceptor.class})
public class MybatisPlusConfig {

    /**
     * 分页拦截器
     *
     * @author SLY
     * @date 2020/7/29
     */
    @ConditionalOnClass(TablePartitionInterceptor.class)
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }

    /**
     * 分表拦截器
     *
     * @author SLY
     * @date 2020/10/12
     */
    @Bean
    public TablePartitionInterceptor tablePartitionInterceptor(){
        TablePartitionInterceptor tablePartitionInterceptor = new TablePartitionInterceptor();
        return tablePartitionInterceptor;
    }

}
