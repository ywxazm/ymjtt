package com.ymjtt.manager.product.service;

import com.github.pagehelper.PageInfo;
import com.ymjtt.manager.product.xdo.ProductDo;

import java.util.List;

/**
 * @auther ywx
 * @date 2018/11/20 14:29
 **/
public interface ProductService {

    //Base
    PageInfo<ProductDo> listDO(ProductDo productDo, Integer page, Integer rows);

    ProductDo getDO(Long id);

    boolean removeDO(Long id);

    boolean saveDO(ProductDo productDo);

    boolean updateDO(ProductDo productDo);


    //Others
    List<ProductDo> listNoPage(ProductDo productDo);


}
