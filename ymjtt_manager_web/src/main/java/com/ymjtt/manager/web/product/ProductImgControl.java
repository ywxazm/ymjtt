package com.ymjtt.manager.web.product;

import com.ymjtt.common.result.CodeResult;
import com.ymjtt.common.result.ResultVO;
import com.ymjtt.manager.service.ProductImgService;
import com.ymjtt.manager.xdo.ProductImgDo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @auther ywx
 * @date 2018/11/20 16:34
 **/
@Service
@RequestMapping("/manager/productImg")
public class ProductImgControl {

    private static final Logger logger = LoggerFactory.getLogger(ProductImgControl.class);

    @Autowired
    private ProductImgService productImgService;

    @ResponseBody
    @RequestMapping(value = "/listByPid")
    public ResultVO listByPid(Long pid) {
        logger.debug("the thread id is {}, ProductImgControl.listByPid() param pid = {}"
                , Thread.currentThread().getId(), pid);
        Long startTime = System.currentTimeMillis();

        List<ProductImgDo> productImgDoList = productImgService.listByPid(pid);

        logger.debug("the thread id is {}, ProductImgControl.listByPid() cast time is {}"
                , Thread.currentThread().getId(), System.currentTimeMillis() - startTime);
        return ResultVO.buildSuccessResult(productImgDoList);
    }

    @ResponseBody
    @RequestMapping(value = "/add")
    public ResultVO add(ProductImgDo productImgDo) {
        logger.debug("the thread id is {}, ProductImgControl.add() param productCatDo = {}"
                , Thread.currentThread().getId(), productImgDo);
        Long startTime = System.currentTimeMillis();

        Boolean result = productImgService.add(productImgDo);

        logger.debug("the thread id is {}, ProductImgControl.add() cast time is {}"
                , Thread.currentThread().getId(), System.currentTimeMillis() - startTime);
        return result ? ResultVO.buildSuccessResult("add product img success")
                : ResultVO.buildErrorResult(CodeResult.REMOVE_PRODUCT_IMG_FAIL);
    }

    @ResponseBody
    @RequestMapping(value = "/update")
    public ResultVO update(ProductImgDo productImgDo) {
        logger.debug("the thread id is {}, ProductImgControl.update() param productImgDo = {}"
                , Thread.currentThread().getId(), productImgDo);
        Long startTime = System.currentTimeMillis();

        Boolean result = productImgService.update(productImgDo);

        logger.debug("the thread id is {}, ProductImgControl.update() cast time is {}"
                , Thread.currentThread().getId(), System.currentTimeMillis() - startTime);
        return result ? ResultVO.buildSuccessResult("update product img success")
                : ResultVO.buildErrorResult(CodeResult.UPDATE_PRODUCT_IMG_FAIL);
    }

    @ResponseBody
    @RequestMapping(value = "/remove")
    public ResultVO remove(Long id) {
        logger.debug("the thread id is {}, ProductImgControl.remove() param id = {}"
                , Thread.currentThread().getId(), id);
        Long startTime = System.currentTimeMillis();

        boolean result = productImgService.remove(id);

        logger.debug("the thread id is {}, ProductImgControl.remove() cast time is {}"
                , Thread.currentThread().getId(), System.currentTimeMillis() - startTime);
        return result ? ResultVO.buildSuccessResult("remove product img success")
                : ResultVO.buildErrorResult(CodeResult.REMOVE_PRODUCT_IMG_FAIL);
    }

    @ResponseBody
    @RequestMapping(value = "/removeByPid")
    public ResultVO removeByPid(Long productId) {
        logger.debug("the thread id is {}, ProductImgControl.remove() param productId = {}"
                , Thread.currentThread().getId(), productId);
        Long startTime = System.currentTimeMillis();

        boolean result = productImgService.remove(productId);

        logger.debug("the thread id is {}, ProductImgControl.remove() cast time is {}"
                , Thread.currentThread().getId(), System.currentTimeMillis() - startTime);
        return result ? ResultVO.buildSuccessResult("remove product img success")
                : ResultVO.buildErrorResult(CodeResult.REMOVE_PRODUCT_IMG_FAIL);
    }
}
