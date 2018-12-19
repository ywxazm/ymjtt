package com.ymjtt.manager.mapper;

import com.ymjtt.manager.xdo.ProductCatDo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @auther ywx
 * @date 2018/11/21 9:55
 **/
public interface ProductCatMapper {

    /**
     * 父ID查询
     * @author  ywx
     * @date    2018/11/28 16:47
     * @param   [parentId]
     * @return  java.util.List<com.ymjtt.manager.xdo.ProductCatDo>
     */
    List<ProductCatDo> listByPid(@Param("pid") Long pid);

    /**
     * ID查询
     * @author  ywx
     * @date    2018/11/28 16:47
     * @param   [productCatId]
     * @return  com.ymjtt.manager.xdo.ProductCatDo
     */
    ProductCatDo getById(@Param("id") Long id);

}
