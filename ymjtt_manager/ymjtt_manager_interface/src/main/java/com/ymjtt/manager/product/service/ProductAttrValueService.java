package com.ymjtt.manager.product.service;

import com.github.pagehelper.PageInfo;
import com.ymjtt.manager.product.xdo.ProductAttrValueDo;

import java.util.List;

/**
 * @auther ywx
 * @date 2018/11/20 14:29
 **/

public interface ProductAttrValueService {

    //Base
    PageInfo<ProductAttrValueDo> listDO(ProductAttrValueDo productAttrValueDo, Integer page, Integer rows);

    ProductAttrValueDo getDO(Long id);

    boolean removeDO(Long id);

    boolean saveDO(ProductAttrValueDo productAttrValueDo);

    boolean updateDO(ProductAttrValueDo productAttrValueDo);


    //Others
    List<ProductAttrValueDo> listNoPage(ProductAttrValueDo productAttrValueDo);

    boolean removeList(Long productAttrId);
}
