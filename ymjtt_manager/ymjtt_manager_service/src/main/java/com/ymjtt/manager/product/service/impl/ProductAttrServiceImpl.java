package com.ymjtt.manager.product.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ymjtt.common.util.jedis.HashOper;
import com.ymjtt.common.util.jedis.RedisKey;
import com.ymjtt.common.util.objPackage.BeanUtil;
import com.ymjtt.manager.product.mapper.ProductAttrMapper;
import com.ymjtt.manager.product.service.ProductAttrService;
import com.ymjtt.manager.product.service.ProductCatService;
import com.ymjtt.manager.product.service.ProductService;
import com.ymjtt.manager.product.xdo.ProductAttrDo;
import com.ymjtt.manager.product.xdo.ProductCatDo;
import com.ymjtt.manager.product.xdo.ProductDo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * 商品服务
 * @author  ywx
 * @date    2018/11/21 15:20
 */
@Service
public class ProductAttrServiceImpl<T> implements ProductAttrService {

    @Autowired
    private ProductAttrMapper productAttrMapper;

    @Autowired
    private ProductCatService productCatService;

    @Autowired
    private ProductService productService;

    @Autowired
    private HashOper hashOper;

    @Value("${topProductCatId}")
    private Long topProductCatId;

    @Override
    public PageInfo<ProductAttrDo> listDO(ProductAttrDo productAttrDo, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        return new PageInfo<>(productAttrMapper.listDO(productAttrDo));
    }

    @Override
    public ProductAttrDo getDO(Long id) {
        return productAttrMapper.getDO(id);
    }

    @Override
    public boolean removeDO(Long id) {
        return productAttrMapper.removeDO(id);
    }

    @Override
    public boolean saveDO(ProductAttrDo productAttrDo) {
        return productAttrMapper.saveDO(productAttrDo);
    }

    @Override
    public boolean updateDO(ProductAttrDo productAttrDo) {
        return productAttrMapper.updateDO(productAttrDo);
    }




    //Others
    /**
     * 等同于list(), 返回类型DO --> VO
     * @author  ywx
     * @date    2019/2/2 10:14
     * @param   [productAttrDo, page, rows]
     * @return  com.github.pagehelper.PageInfo<com.ymjtt.manager.product.vo.ProductAttrVO>
     */
    @Override
    public PageInfo<List<Map<String, Object>>> listVO(ProductAttrDo productAttrDo, Integer page, Integer rows) throws InvocationTargetException, IllegalAccessException, IntrospectionException {
        PageHelper.startPage(page, rows);
        List<ProductAttrDo>  productAttrDoList = productAttrMapper.listDO(productAttrDo);
        PageInfo pageInfo = new PageInfo(productAttrDoList);
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (ProductAttrDo pa : productAttrDoList) {
            String belongName = hashOper.hget(RedisKey.PRODUCT_ATTR, String.valueOf(pa.getBelongId()));
            if (StringUtils.isEmpty(belongName)) {
                Integer attrType = pa.getAttrType();
                if (attrType == 1) {
                    ProductDo productDo = productService.getDO(pa.getBelongId());
                    belongName = productDo.getProductName();
                }else if (attrType == 2) {
                    ProductCatDo productCatDo = productCatService.getDO(pa.getBelongId());
                    belongName = productCatDo.getProductCatName();
                }else {
                    throw new IllegalArgumentException("AttrType Must == 1 Or 2");
                }
            }
            hashOper.hset(RedisKey.PRODUCT_ATTR, String.valueOf(pa.getBelongId()), belongName);
            Map<String, Object> map = BeanUtil.bean2Map(pa);
            map.put("belongName", belongName);
            resultList.add(map);
        }
        pageInfo.setList(resultList);

        return pageInfo;
    }

    /**
     * 等同于list(), 不做分页
     * @author  ywx
     * @date    2019/2/2 10:14
     * @param   [productAttrDo]
     * @return  java.util.List<com.ymjtt.manager.product.xdo.ProductAttrDo>
     */
    @Override
    public List<ProductAttrDo> listNoPage(ProductAttrDo productAttrDo) {
        return productAttrMapper.listDO(productAttrDo);
    }

    /**
     * 获取 商品 + 类别 属性集合
     * @author  ywx
     * @date    2019/1/29 21:11
     * @param   [belongId]  商品ID / 商品类别ID
     * @return  java.util.List<com.ymjtt.manager.product.xdo.ProductAttrDo>
     */
    @Override
    public List<ProductAttrDo> listByBelongId(Long belongId) {
        //获取商品或类别
        ProductDo productDo = productService.getDO(belongId);
        ProductCatDo productCatDo = productCatService.getDO(belongId);

        //获取商品或类别下的规格
        ProductAttrDo productAttrDo = new ProductAttrDo();
        productAttrDo.setBelongId(belongId);
        List<ProductAttrDo>  productAttrDoList = productAttrMapper.listDO(productAttrDo);

        //获取所有父类别下的规格
        Long lastId = topProductCatId;
        if (null != productDo) {        //belongId是商品
            lastId = productDo.getCid();
        } else if (productCatDo != null) {
            lastId = productCatDo.getParentId();
        }
        for (;!topProductCatId.equals(lastId) ;) {
            productAttrDo.setBelongId(lastId);
            productAttrDoList.addAll(productAttrMapper.listDO(productAttrDo));
            ProductCatDo pc = productCatService.getDO(lastId);
            lastId = pc.getParentId();
        }

        //排序
        productAttrDoList.sort(Comparator.comparingInt(ProductAttrDo::getAttrType));

        return productAttrDoList;
    }

    /**
     * 获取商品下的属性
     * @author  ywx
     * @date    2019/2/3 9:00
     * @param   [productId]
     * @return  java.util.List<com.ymjtt.manager.product.xdo.ProductAttrDo>
     */
    @Override
    public List<ProductAttrDo> listOnlyProductAttrById(Long productId) {
        ProductDo productDo = productService.getDO(productId);
        ProductAttrDo productAttrDo = new ProductAttrDo();
        productAttrDo.setBelongId(productId);
        return productAttrMapper.listDO(productAttrDo);
    }
}
