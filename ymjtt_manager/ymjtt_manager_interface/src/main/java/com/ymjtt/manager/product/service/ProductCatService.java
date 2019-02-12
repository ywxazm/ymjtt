package com.ymjtt.manager.product.service;

import com.github.pagehelper.PageInfo;
import com.ymjtt.common.vo.NodeVO;
import com.ymjtt.manager.product.xdo.ProductCatDo;

import java.util.List;

/**
 * @auther ywx
 * @date 2018/11/20 14:29
 **/

public interface ProductCatService {

    //Base
    PageInfo<ProductCatDo> listDO(ProductCatDo productCatDo, Integer page, Integer rows);

    ProductCatDo getDO(Long id);

    Boolean removeDO(Long id);

    Boolean saveDO(ProductCatDo productCatDo);

    Boolean updateDO(ProductCatDo productCatDo);

    //Others
    List<NodeVO> listByParentId(Long parentId);

    List<NodeVO> listBySonId(Long sonId);

    boolean ifParentNode(Long id);
}
