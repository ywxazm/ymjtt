package com.ymjtt.manager.product.service;

import com.github.pagehelper.PageInfo;
import com.ymjtt.manager.product.xdo.ProductAttrDo;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @auther ywx
 * @date 2018/11/20 14:29
 **/

public interface ProductAttrService {

    //Base
    PageInfo<ProductAttrDo> listDO(ProductAttrDo productAttrDo, Integer page, Integer rows);

    ProductAttrDo getDO(Long id);

    boolean removeDO(Long id);

    boolean saveDO(ProductAttrDo productAttrDo);

    boolean updateDO(ProductAttrDo productAttrDo);


    //Others
    PageInfo<List<Map<String, Object>>> listVO(ProductAttrDo productAttrDo, Integer page, Integer rows) throws InvocationTargetException, IllegalAccessException, IntrospectionException;

    List<ProductAttrDo> listNoPage(ProductAttrDo productAttrDo);

    List<ProductAttrDo> listByBelongId(Long belongId);

    List<ProductAttrDo> listOnlyProductAttrById(Long productId);

}
