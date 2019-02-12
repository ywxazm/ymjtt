package com.ymjtt.manager.product.service;

import com.github.pagehelper.PageInfo;
import com.ymjtt.manager.product.xdo.ProductDetailDo;
import org.csource.common.MyException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @auther ywx
 * @date 2018/11/20 14:29
 **/

public interface ProductDetailService {

    //Base
    PageInfo<ProductDetailDo> listDO(ProductDetailDo productDetailDo, Integer page, Integer rows);

    ProductDetailDo getDO(Long id);

    boolean removeDO(Long id) throws IOException, MyException;

    boolean saveDO(Long productId, byte[] data, String extensionName) throws IOException, MyException;

    @Deprecated
    boolean updateDO(Long productDetailId, MultipartFile multipartFile) throws IOException, MyException;


    //Others
    List<ProductDetailDo> listNoPage(ProductDetailDo productDetailDo);

    List<ProductDetailDo> listByProductId(Long productId);

    boolean removeByParentId(Long parentId) throws IOException, MyException;
}
