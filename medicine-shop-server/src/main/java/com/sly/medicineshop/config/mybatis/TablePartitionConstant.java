package com.sly.medicineshop.config.mybatis;

import java.util.ArrayList;
import java.util.List;

/**
 * 分表常量
 *
 * @author SLY
 * @date 2020/10/10
 */
public class TablePartitionConstant {

    public static final List<String> TABLE_NAMES = new ArrayList<String>(){
        {
            add("G_BATCH_STORAGE");
            add("G_GOODS");
            add("G_ORDER");
            add("G_ORDER_ITEM");
            add("G_STORAGE");
        }
    };
}
