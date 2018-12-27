package com.ymjtt.common.util.jedis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @auther ywx
 * @date 2018/12/18 17:54
 **/
@Component
public class GenerateID {

    @Autowired
    private JedisClient jedisClient;

    /**
     * 以递增长方式生成产品ID, 1亿开始
     * @author  ywx
     * @date    2018/12/18 17:55
     * @param   []
     * @return  java.lang.String
     */
    public String generateProductID() {
        String productIdValue = jedisClient.get(RedisKey.PRODUCT_ID);
        if (null != productIdValue) {
            productIdValue = String.valueOf(jedisClient.incr(RedisKey.PRODUCT_ID));
        }else {
            jedisClient.set(RedisKey.PRODUCT_ID, "100000000");
            productIdValue = "100000000";
        }
        return productIdValue;
    }

    /**
     * 以自增长的方式, 生成条形码, 2亿开始
     * @author  ywx
     * @date    2018/12/18 21:00
     * @param   []
     * @return  java.lang.String
     */
    public String generateProductBarCode() {
        String productBarCodeValue = jedisClient.get(RedisKey.PRODUCT_BARCODE);
        if (null != productBarCodeValue) {
            productBarCodeValue = String.valueOf(jedisClient.incr(RedisKey.PRODUCT_BARCODE));
        }else {
            jedisClient.set(RedisKey.PRODUCT_BARCODE, "200000000");
            productBarCodeValue = "200000000";
        }
        return productBarCodeValue;
    }

    /**
     * 以自增长的方式, 商品种类, 30000开始
     * @author  ywx
     * @date    2018/12/18 21:00
     * @param   []
     * @return  java.lang.String
     */
    public String generateProductCatId() {
        String productBarCodeValue = jedisClient.get(RedisKey.PRODUCT_CAT_ID);
        if (null != productBarCodeValue) {
            productBarCodeValue = String.valueOf(jedisClient.incr(RedisKey.PRODUCT_CAT_ID));
        }else {
            jedisClient.set(RedisKey.PRODUCT_CAT_ID, "30000");
            productBarCodeValue = "30000";
        }
        return productBarCodeValue;
    }
}
