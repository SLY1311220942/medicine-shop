package com.sly.medicineshop.config;

import com.sly.medicineshop.interceptor.AuthInterceptor;
import com.sly.medicineshop.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * web mvc 配置
 *
 * @author SLY
 * @date 2020/10/7
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Resource
    private LoginInterceptor loginInterceptor;
    @Resource
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns(
                "/clerk/login"
        );
        registry.addInterceptor(authInterceptor).addPathPatterns("/**");
    }
}
