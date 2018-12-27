package com.ymjtt.manager.service;

import com.ymjtt.manager.xdo.MenuDo;
import com.ymjtt.manager.xdo.ProductImgDo;

import java.util.List;

/**
 *
 * @author  ywx
 * @date    2018/11/22 17:57
 */
public interface ProductImgService {

    List<ProductImgDo> listByPid(Long productId);

    boolean add(ProductImgDo productImgDo);

    boolean remove(Long id);

    boolean removeByPid(Long productId);

    boolean update(ProductImgDo productImgDo);

}
