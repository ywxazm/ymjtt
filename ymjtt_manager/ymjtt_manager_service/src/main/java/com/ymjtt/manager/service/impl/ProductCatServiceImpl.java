package com.ymjtt.manager.service.impl;

import com.ymjtt.common.util.jedis.GenerateID;
import com.ymjtt.common.vo.ProductCatNodeVO;
import com.ymjtt.manager.mapper.ProductCatMapper;
import com.ymjtt.manager.mapper.ProductMapper;
import com.ymjtt.manager.service.ProductCatService;
import com.ymjtt.manager.xdo.ProductCatDo;
import com.ymjtt.manager.xdo.ProductDo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther ywx
 * @date 2018/11/20 14:32
 **/
@Service
public class ProductCatServiceImpl implements ProductCatService {

    private static final Logger logger = LoggerFactory.getLogger(ProductCatService.class);

    @Autowired
    private ProductCatMapper productCatMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private GenerateID generateID;

    @Override
    public List<ProductCatNodeVO> listByPid(Long pid, String productCatName) {
        logger.debug("the thread id is {}, ProductCatServiceImpl.listDetailByPid() param pid = {}, productCatName = {}"
                , Thread.currentThread().getId(), pid, productCatName);
        Long startTime = System.currentTimeMillis();

        List<ProductCatDo> productCatDos = productCatMapper.listByPid(pid, productCatName);
        List<ProductCatNodeVO> productCatNodeVOS = new ArrayList<>();
        for (ProductCatDo productCatDo : productCatDos) {
            ProductCatNodeVO productCatNodeVO = new ProductCatNodeVO();
            productCatNodeVO.setId(productCatDo.getProductCatId());
            productCatNodeVO.setName(productCatDo.getProductCatName());
            productCatNodeVO.setParentType(productCatDo.getParentCat() == 1);
            productCatNodeVO.setStatus(productCatDo.getProductCatStatus() == 1 ? "open" : "close");
            productCatNodeVOS.add(productCatNodeVO);
        }

        logger.debug("the thread id is {}, ProductCatServiceImpl.listDetailByPid() service cast time is {}"
                , Thread.currentThread().getId(), System.currentTimeMillis() - startTime);
        return productCatNodeVOS;
    }

    @Override
    public List<ProductCatDo> listDetailByPid(Long pid, String productCatName) {
        logger.debug("the thread id is {}, ProductCatServiceImpl.listDetailByPid() param pid = {}, productCatName = {}"
                , Thread.currentThread().getId(), pid, productCatName);
        Long startTime = System.currentTimeMillis();

        List<ProductCatDo> productCatDos = productCatMapper.listByPid(pid, productCatName);

        logger.debug("the thread id is {}, ProductCatServiceImpl.listDetailByPid() service cast time is {}"
                , Thread.currentThread().getId(), System.currentTimeMillis() - startTime);
        return productCatDos;
    }

    @Override
    public ProductCatDo getById(Long id) {
        logger.debug("the thread id is {}, ProductCatServiceImpl.getById() param id = {}"
                , Thread.currentThread().getId(), id);
        Long startTime = System.currentTimeMillis();

        ProductCatDo productCatDo = productCatMapper.getById(id);

        logger.debug("the thread id is {}, ProductCatServiceImpl.getById() service cast time is {}"
                , Thread.currentThread().getId(), System.currentTimeMillis() - startTime);
        return productCatDo;
    }

    @Override
    public Boolean update(ProductCatDo productCatDo) {
        logger.debug("the thread id is {}, ProductCatServiceImpl.update() param productCatDo = {}"
                , Thread.currentThread().getId(), productCatDo);
        Long startTime = System.currentTimeMillis();

        Boolean result = productCatMapper.update(productCatDo);

        logger.debug("the thread id is {}, ProductCatServiceImpl.update() service cast time is {}"
                , Thread.currentThread().getId(), System.currentTimeMillis() - startTime);
        return result;
    }

    @Override
    public Boolean add(ProductCatDo productCatDo) {
        logger.debug("the thread id is {}, ProductCatServiceImpl.add() param productCatDo = {}"
                , Thread.currentThread().getId(), productCatDo);
        Long startTime = System.currentTimeMillis();

        productCatDo.setProductCatId(Long.parseLong(generateID.generateProductCatId()));
        Boolean result = productCatMapper.add(productCatDo);

        logger.debug("the thread id is {}, ProductCatServiceImpl.add() service cast time is {}"
                , Thread.currentThread().getId(), System.currentTimeMillis() - startTime);
        return result;
    }

    @Override
    public String remove(Long id) {
        logger.debug("the thread id is {}, ProductCatServiceImpl.remove() param id = {}"
                , Thread.currentThread().getId(), id);
        Long startTime = System.currentTimeMillis();

        //包含子种类, 不允许删除
        List<ProductCatNodeVO> list = listByPid(id, null);
        if (!list.isEmpty()) {
            return "contain son cat";
        }

        //包含商品, 不允许删除
        List<ProductDo> productDoList = productMapper.listByCid(id);
        if (null == productDoList) {
            return "contain product";
        }

        Boolean result = productCatMapper.remove(id);

        logger.debug("the thread id is {}, ProductCatServiceImpl.remove() service cast time is {}"
                , Thread.currentThread().getId(), System.currentTimeMillis() - startTime);
        return String.valueOf(result);
    }
}
