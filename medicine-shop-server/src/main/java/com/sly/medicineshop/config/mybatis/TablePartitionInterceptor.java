package com.sly.medicineshop.config.mybatis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;

/**
 * 分表拦截器
 *
 * @author SLY
 * @date 2020/10/10
 */
@Intercepts(@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class}))
public class TablePartitionInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = PluginUtils.realTarget(invocation.getTarget());
        // 获取值path
        String valuePath = getValuePath(statementHandler);
        Object parameterObject = statementHandler.getBoundSql().getParameterObject();
        // 获取值
        Integer value = getValue(valuePath, parameterObject);

        String sql = statementHandler.getBoundSql().getSql();
        MetaObject metaObject = SystemMetaObject.forObject(statementHandler);

        for (String tableName : TablePartitionConstant.TABLE_NAMES) {
            sql = sql.replaceAll(tableName, tableName + "_" + value / 1000);
        }
        metaObject.setValue("delegate.boundSql.sql", sql);
        return invocation.proceed();
    }

    /**
     * 获取值的path
     */
    private String getValuePath(StatementHandler statementHandler) {
        try {
            Field field = statementHandler.getClass().getDeclaredField("delegate");
            field.setAccessible(true);
            BaseStatementHandler preparedStatementHandler = (BaseStatementHandler) field.get(statementHandler);
            Field mappedStatementField = BaseStatementHandler.class.getDeclaredField("mappedStatement");
            mappedStatementField.setAccessible(true);
            MappedStatement mappedStatement = (MappedStatement) mappedStatementField.get(preparedStatementHandler);
            String methodFullName = mappedStatement.getId();
            String className = methodFullName.substring(0, methodFullName.lastIndexOf('.'));
            Method[] methods = Class.forName(className).getDeclaredMethods();

            String valuePath = "";
            String methodName = methodFullName.substring(methodFullName.lastIndexOf('.') + 1);
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    TablePartition tablePartition = method.getAnnotation(TablePartition.class);
                    valuePath = tablePartition.value();
                }
            }
            return valuePath;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取值
     */
    private Integer getValue(String valuePath, Object parameterObject) {
        String[] keys = valuePath.split("\\.");
        Integer value = -1;
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(parameterObject));
        for (int i = 0; i < keys.length; i++) {
            if (i == keys.length - 1) {
                value = jsonObject.getObject(keys[i], Integer.class);
            } else {
                jsonObject = jsonObject.getJSONObject(keys[i]);
            }
        }
        if (value == null || value < 0) {
            throw new RuntimeException("分表参数异常:");
        }
        return value;
    }
}
