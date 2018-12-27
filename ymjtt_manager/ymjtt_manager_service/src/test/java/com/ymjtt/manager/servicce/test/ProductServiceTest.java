package com.ymjtt.manager.servicce.test;

import com.github.pagehelper.PageInfo;
import com.ymjtt.common.util.jedis.JedisClient;
import com.ymjtt.manager.service.ProductService;
import com.ymjtt.manager.xdo.ProductDo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author  ywx
 * @date    2018/11/21 15:26
 */
@SuppressWarnings("unchecked")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:spring/applicationContext_*.xml"})
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private JedisClient jedisClient;

    @Test
    public void getById() {
        ProductDo productDo = productService.getById(860275L);
        System.out.println(productDo);
    }

    @Test
    public void getByCid() {
        List<ProductDo> list = productService.listByCid(560L);
        list.forEach(System.out::println);
    }

    @Test
    public void list() {
        PageInfo pageInfo = productService.list(null, 1, 5);
        List list = pageInfo.getList();
        list.stream().forEach(System.out::println);
    }

    @Test
    public void listByDo() {
        ProductDo productDo = new ProductDo();
        productDo.setTitle("手");
        productDo.setPrice(1000D);
        productDo.setPriceAgain(20000D);
        PageInfo<ProductDo> pageInfo = productService.listByDo(productDo, 1, 5);
        List<ProductDo> list = pageInfo.getList();
        list.forEach(System.out::println);
    }

    @Test
    public void remove() {
        boolean result = productService.remove("1023433");
        System.out.println(result);
    }

    @Test
    public void removeList() {
        int count = productService.removeList("1039296,1059373,1082433");
        System.out.println(count);
    }

    @Test
    public void add() {
        ProductDo productDo = new ProductDo();
        productDo.setTitle("111");
        productDo.setSellPoint("222");
        productDo.setPrice(3333D);
        productDo.setBarcode("44444");
        productDo.setImage("55555");
//        productDo.setCid(6666L);
        productDo.setCreateOper("mm");
        productDo.setLastupdateOper("nn");
        boolean result = productService.add(productDo);
        System.out.println(result);
    }

    @Test
    public void update() {
        ProductDo productDo = new ProductDo();
        productDo.setProductId(143771131488372L);
        productDo.setPrice(9909D);
        boolean result = productService.update(productDo);
        System.out.println(result);
    }

    //另一种更新, 利用一级缓存, 这是不可以的   TODO: hibernate 在事务结束时,会将更新过的数据进行序列化, 而mybatis仅仅是查询的缓存, 没再进行序列化的过程
    @Test
    public void updateAgain() {
        ProductDo productDo = new ProductDo();
        productDo.setProductId(143771131488372L);
        boolean result = productService.update(productDo);
        System.out.println(result);
    }

}
