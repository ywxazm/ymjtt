package com.ymjtt.common.util.jedis;

/**
 * 定义Redis Key Name
 * @auther ywx
 * @date 2018/12/18 17:56
 **/
public class RedisKey {

    private RedisKey() {
    }

    /* 菜单 */
    public static final String MENU = "Menu";

    /* 商品模块 */
    public static final String PRODUCT_CAT = "ProductCat";
    public static final String PRODUCT = "Product";
    public static final String PRODUCT_CAT_ID = "ProductCatId";
    public static final String BARCODE = "Barcode";
    public static final String PRODUCT_ATTR = "ProductAttr";

    /* 内容模块 */
    public static final String CONTENT_CAT = "ContentCat";
    public static final String CONTENT = "Content";

    /* ID */
    public static final String ID = "ID";

}
