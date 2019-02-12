package com.ymjtt.manager.servicce.test;

import com.ymjtt.manager.product.service.ProductCatService;
import com.ymjtt.manager.product.xdo.ProductCatDo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @auther ywx
 * @date 2018/11/20 15:05
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:spring/applicationContext_*.xml"})
public class ProductCatServiceTest {

    @Autowired
    private ProductCatService productCatService;

    @Test
    public void getById() {
//        Map<String, Object> map = productCatService.getDO(1L);
//        System.out.println(map);
    }


    @Test
    public void update() {
        ProductCatDo productCatDo = new ProductCatDo();
        productCatDo.setProductCatId(30026L);
        productCatDo.setProductCatName("adsf");
        productCatDo.setProductCatStatus(1);
        productCatDo.setSortOrder(1);
        productCatDo.setParentId(0L);
        boolean b = productCatService.updateDO(productCatDo);
        System.out.println(b);
    }
}
