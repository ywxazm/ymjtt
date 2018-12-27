package com.ymjtt.manager.service.impl;

import com.ymjtt.manager.mapper.ProductImgMapper;
import com.ymjtt.manager.service.ProductImgService;
import com.ymjtt.manager.xdo.ProductImgDo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther ywx
 * @date 2018/12/27 16:45
 **/
@Service
public class ProductImgServiceImpl implements ProductImgService {

    private static final Logger logger = LoggerFactory.getLogger(ProductImgService.class);

    @Autowired
    private ProductImgMapper productImgMapper;

    @Override
    public List<ProductImgDo> listByPid(Long productId) {
        logger.debug("the thread id is {}, ProductImgService.listByPid() param productId = {}", Thread.currentThread().getId(), productId);

        Long startTime = System.currentTimeMillis();

        List<ProductImgDo> productImgDoList = productImgMapper.listByPid(productId);

        logger.debug("the thread id is {}, ProductImgService.listByPid() service cast time is {}"
                , Thread.currentThread().getId(), System.currentTimeMillis() - startTime);
        return productImgDoList;
    }

    @Override
    public boolean add(ProductImgDo productImgDo) {
        logger.debug("the thread id is {}, ProductImgService.add() param productImgDo = {}", Thread.currentThread().getId(), productImgDo);

        Long startTime = System.currentTimeMillis();

        boolean result = productImgMapper.add(productImgDo);

        logger.debug("the thread id is {}, ProductImgService.add() service cast time is {}"
                , Thread.currentThread().getId(), System.currentTimeMillis() - startTime);
        return result;
    }

    @Override
    public boolean remove(Long id) {
        logger.debug("the thread id is {}, ProductImgService.remove() param id = {}", Thread.currentThread().getId(), id);

        Long startTime = System.currentTimeMillis();

        boolean result = productImgMapper.remove(id);

        logger.debug("the thread id is {}, ProductImgService.remove() service cast time is {}"
                , Thread.currentThread().getId(), System.currentTimeMillis() - startTime);
        return result;
    }

    @Override
    public boolean removeByPid(Long productId) {
        logger.debug("the thread id is {}, ProductImgService.removeByPid() param productId = {}", Thread.currentThread().getId(), productId);

        Long startTime = System.currentTimeMillis();

        boolean result = productImgMapper.removeByPid(productId);

        logger.debug("the thread id is {}, ProductImgService.removeByPid() service cast time is {}"
                , Thread.currentThread().getId(), System.currentTimeMillis() - startTime);
        return result;
    }

    @Override
    public boolean update(ProductImgDo productImgDo) {
        logger.debug("the thread id is {}, ProductImgService.update() param productImgDo = {}", Thread.currentThread().getId(), productImgDo);

        Long startTime = System.currentTimeMillis();

        boolean result = productImgMapper.update(productImgDo);

        logger.debug("the thread id is {}, ProductImgService.update() service cast time is {}"
                , Thread.currentThread().getId(), System.currentTimeMillis() - startTime);
        return result;
    }
}
