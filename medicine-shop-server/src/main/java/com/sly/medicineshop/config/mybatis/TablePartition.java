package com.sly.medicineshop.config.mybatis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 分表注解
 *
 * @author SLY
 * @date 2020/10/10
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TablePartition {
    /**
     * 取值字段
     */
    String value() default "";

    /**
     * 默认分表， 为false时， 此注解无效
     */
    boolean split() default true;
}
