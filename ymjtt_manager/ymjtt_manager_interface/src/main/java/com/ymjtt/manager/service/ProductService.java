package com.ymjtt.manager.service;

import com.github.pagehelper.PageInfo;
import com.ymjtt.common.vo.ProductCatNodeVO;
import com.ymjtt.manager.xdo.ProductDo;

import java.util.List;
import java.util.Map;

/**
 * @auther ywx
 * @date 2018/11/20 14:29
 **/

public interface ProductService {

    ProductDo getById(Long id);

    PageInfo<List<Map<String, Object>>> list(String criteria, Integer currPage, Integer pageNum);

    boolean remove(String id);

    boolean add(ProductDo productDo);

    boolean update(ProductDo productDo);



    List<ProductDo> listByCid(Long cid);

    PageInfo<ProductDo> listByDo(ProductDo productDo, Integer currPage, Integer pageSize);

    ProductCatNodeVO getCatById(Long id);

    int removeList(String ids);



}
