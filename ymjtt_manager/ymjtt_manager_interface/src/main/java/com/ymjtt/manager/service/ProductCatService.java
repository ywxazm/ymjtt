package com.ymjtt.manager.service;

import com.ymjtt.common.vo.ProductCatNodeVO;
import com.ymjtt.manager.xdo.ProductCatDo;

import java.util.List;

/**
 * @auther ywx
 * @date 2018/11/20 14:29
 **/

public interface ProductCatService {

    List<ProductCatNodeVO> listByPid(Long pid, String productCatName);

    List<ProductCatDo> listDetailByPid(Long pid, String productCatName);

    ProductCatDo getById(Long id);

    Boolean update(ProductCatDo productCatDo);

    Boolean add(ProductCatDo productCatDo);

    String remove(Long id);

}
