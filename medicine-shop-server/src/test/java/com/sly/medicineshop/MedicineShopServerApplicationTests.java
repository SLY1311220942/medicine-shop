package com.sly.medicineshop;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sly.medicineshop.business.shop.model.Clerk;
import com.sly.medicineshop.util.StorageConvertUtils;
import com.sly.medicineshop.util.StorageUnit;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;

public class MedicineShopServerApplicationTests {

    @Test
    public void test01() {
        StorageUnit unit1 = new StorageUnit("袋", new BigDecimal("10"));
        StorageUnit unit2 = new StorageUnit("小盒", new BigDecimal("3"), unit1);
        StorageUnit unit3 = new StorageUnit("中盒", new BigDecimal("3"), unit2);
        StorageUnit unit = new StorageUnit("大盒", unit3);

        String count = "大盒:1,小盒:2,袋:10";
        String target = "袋";
        BigDecimal totalCount = StorageConvertUtils.convertToMinUnit(count, unit);
        System.out.println(totalCount);

        BigDecimal decimal = StorageConvertUtils.convertToTargetUnitWithOutDismounted(count, unit, "小盒");
        System.out.println(decimal);

        BigDecimal decimal1 = StorageConvertUtils.convertToTargetUnit(count, unit, "小盒");
        System.out.println(decimal1);

        String addition = StorageConvertUtils.addition("大盒:1,小盒:2,袋:10", "大盒:2,中盒:5,小盒:2,袋:10", unit);
        System.out.println(addition);
    }

    @Test
    public void test02() {
        StorageUnit unit1 = new StorageUnit("袋", new BigDecimal("10"));
        StorageUnit unit2 = new StorageUnit("小盒", new BigDecimal("3"), unit1);
        StorageUnit unit3 = new StorageUnit("中盒", new BigDecimal("3"), unit2);
        StorageUnit unit = new StorageUnit("大盒", unit3);


        String addition = StorageConvertUtils.subtraction("大盒:4,中盒:5,小盒:2,袋:20", "大盒:4,中盒:5,小盒:2,袋:10", unit);
        System.out.println(addition);
    }

    @Test
    public void test03() {
        String str = "asdasasd,";
        if (str.endsWith(",")) {
            str = str.substring(0, str.length() - 1);
        }
        System.out.println(str);
    }

    @Test
    public void test04(){
        ObjectMapper objectMapper = new ObjectMapper();
        Clerk clerk = objectMapper.convertValue(null, Clerk.class);
        System.out.println(clerk);
    }

    @Test
    public void test05() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://192.168.100.101:9200/_cat/indices?format=json");
        CloseableHttpResponse response = client.execute(httpGet);
        String content = EntityUtils.toString(response.getEntity());
        System.out.println(content);
    }


}
