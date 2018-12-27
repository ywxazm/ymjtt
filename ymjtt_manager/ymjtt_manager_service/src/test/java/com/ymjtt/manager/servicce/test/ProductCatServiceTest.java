package com.ymjtt.manager.servicce.test;

import com.ymjtt.common.vo.ProductCatNodeVO;
import com.ymjtt.manager.service.ProductCatService;
import com.ymjtt.manager.xdo.ProductCatDo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

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
    public void listByPid() {
        List<ProductCatNodeVO> listByPid = productCatService.listByPid(0L, null);
        listByPid.forEach(System.out::println);
    }

    @Test
    public void listDetailByPid() {
        List<ProductCatDo> listByPid = productCatService.listDetailByPid(1L, null);
        listByPid.forEach(System.out::println);
    }

    @Test
    public void getById() {
        ProductCatDo productCatDo = productCatService.getById(1L);
        System.out.println(productCatDo);
    }


    @Test
    public void update() {
        ProductCatDo productCatDo = new ProductCatDo();
        productCatDo.setProductCatId(30026L);
        productCatDo.setProductCatName("adsf");
        productCatDo.setProductCatStatus(1);
        productCatDo.setSortOrder(1);
        productCatDo.setParentId(0L);
        productCatDo.setParentCat(0);
        boolean b = productCatService.update(productCatDo);
        System.out.println(b);
    }
}
