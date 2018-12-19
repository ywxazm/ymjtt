package com.ymjtt.manager.service.impl;

import com.ymjtt.common.vo.ProductCatNodeVO;
import com.ymjtt.manager.mapper.ProductCatMapper;
import com.ymjtt.manager.service.ProductCatService;
import com.ymjtt.manager.xdo.ProductCatDo;
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

    @Override
    public List<ProductCatNodeVO> listByPid(Long pid) {
        logger.debug("the thread id is {}, ProductCatServiceImpl.listDetailByPid() param pid = {}", Thread.currentThread().getId(), pid);
        Long startTime = System.currentTimeMillis();

        List<ProductCatDo> productCatDos = productCatMapper.listByPid(pid);
        List<ProductCatNodeVO> productCatNodeVOS = new ArrayList<>();
        for (ProductCatDo productCatDo : productCatDos) {
            ProductCatNodeVO productCatNodeVO = new ProductCatNodeVO();
            productCatNodeVO.setId(productCatDo.getProductCatId());
            productCatNodeVO.setName(productCatDo.getProductCatName());
            productCatNodeVO.setStatus(productCatDo.getProductCatStatus() == 1 ? "open" : "close");
            productCatNodeVOS.add(productCatNodeVO);
        }

        logger.debug("the thread id is {}, ProductCatServiceImpl.listDetailByPid() service cast time is {}"
                , Thread.currentThread().getId(), System.currentTimeMillis() - startTime);
        return productCatNodeVOS;
    }

    @Override
    public List<ProductCatDo> listDetailByPid(Long pid) {
        logger.debug("the thread id is {}, ProductCatServiceImpl.listDetailByPid() param pid = {}", Thread.currentThread().getId(), pid);
        Long startTime = System.currentTimeMillis();

        List<ProductCatDo> productCatDos = productCatMapper.listByPid(pid);

        logger.debug("the thread id is {}, ProductCatServiceImpl.listDetailByPid() service cast time is {}"
                , Thread.currentThread().getId(), System.currentTimeMillis() - startTime);
        return productCatDos;
    }

    @Override
    public ProductCatDo getById(Long id) {
        logger.debug("the thread id is {}, ProductCatServiceImpl.getById() param id = {}", Thread.currentThread().getId(), id);
        Long startTime = System.currentTimeMillis();

        ProductCatDo productCatDo = productCatMapper.getById(id);

        logger.debug("the thread id is {}, ProductCatServiceImpl.getById() service cast time is {}"
                , Thread.currentThread().getId(), System.currentTimeMillis() - startTime);
        return productCatDo;
    }
}
