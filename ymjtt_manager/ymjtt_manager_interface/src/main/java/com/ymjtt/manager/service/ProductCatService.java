package com.ymjtt.manager.service;

import com.ymjtt.common.vo.ProductCatNodeVO;
import com.ymjtt.manager.xdo.ProductCatDo;

import java.util.List;

/**
 * @auther ywx
 * @date 2018/11/20 14:29
 **/

public interface ProductCatService {

    List<ProductCatNodeVO> listByPid(Long pid);

    List<ProductCatDo> listDetailByPid(Long pid);

    ProductCatDo getById(Long id);

}
