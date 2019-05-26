package com.ymjtt.manager.util;

import com.ymjtt.common.redis.constant.RedisKeyConstant;
import com.ymjtt.common.redis.HashOper;
import com.ymjtt.common.util.date.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @auther ywx
 * @date 2019/3/7 9:08
 **/
@Component
public class GenerateId{

    @Autowired
    private HashOper hashOper;

    /**
     * 以递增长方式生成产品ID
     * @author  ywx
     * @date    2018/12/18 17:55
     * @param   []
     * @return  java.lang.String
     */
    public String generateProductID() {
        String yyyymmdd = DateUtils.LocalDateTime2String(DateUtils.getCurrentDateTime(), DateUtils.DATE_NOFUll_FORMAT);
        String productIdValue = hashOper.hget(RedisKeyConstant.INCR_ID, RedisKeyConstant.PRODUCT);
        if (!StringUtils.isEmpty(productIdValue)) {
            productIdValue = String.valueOf(hashOper.hincrBy(RedisKeyConstant.INCR_ID, RedisKeyConstant.PRODUCT, 1L));
        }else {
            productIdValue = yyyymmdd + "100000";
            hashOper.hset(RedisKeyConstant.INCR_ID, RedisKeyConstant.PRODUCT, productIdValue);
        }
        return productIdValue;
    }

    /**
     * 以自增长的方式, 生成条形码
     * @author  ywx
     * @date    2018/12/18 21:00
     * @param   []
     * @return  java.lang.String
     */
    public String generateProductBarCode() {
        String productBarCodeValue = hashOper.hget(RedisKeyConstant.INCR_ID, RedisKeyConstant.BARCODE);
        if (!StringUtils.isEmpty(productBarCodeValue)) {
            productBarCodeValue = String.valueOf(hashOper.hincrBy(RedisKeyConstant.INCR_ID, RedisKeyConstant.BARCODE, 1L));
        }else {
            productBarCodeValue = "10000000000";
            hashOper.hset(RedisKeyConstant.INCR_ID, RedisKeyConstant.BARCODE, productBarCodeValue);
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
        String productBarCodeValue = hashOper.hget(RedisKeyConstant.INCR_ID, RedisKeyConstant.PRODUCT_CAT);
        if (StringUtils.isEmpty(productBarCodeValue)) {
            hashOper.hset(RedisKeyConstant.INCR_ID, RedisKeyConstant.PRODUCT_CAT, "30000");
            productBarCodeValue = "30000";
        }else {
            productBarCodeValue = String.valueOf(hashOper.hincrBy(RedisKeyConstant.INCR_ID, RedisKeyConstant.PRODUCT_CAT, 1L));
        }
        return productBarCodeValue;
    }



}
