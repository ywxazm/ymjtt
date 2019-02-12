package com.ymjtt.manager.product.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ymjtt.common.util.jedis.GenerateID;
import com.ymjtt.manager.product.mapper.ProductMapper;
import com.ymjtt.manager.product.service.ProductService;
import com.ymjtt.manager.product.xdo.ProductDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品服务
 * @author  ywx
 * @date    2018/11/21 15:20
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private GenerateID generateID;

    @Override
    public PageInfo<ProductDo> listDO(ProductDo productDo, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<ProductDo> list = productMapper.listDO(productDo);
        return new PageInfo<>(list);
    }

    @Override
    public ProductDo getDO(Long id) {
        return productMapper.getDO(id);
    }

    @Override
    public boolean removeDO(Long id) {
        return productMapper.removeDO(id);
    }

    @Override
    public boolean saveDO(ProductDo productDo) {
        synchronized (ProductService.class) {
            productDo.setProductId(Long.parseLong(generateID.generateProductID()));
            productDo.setBarcode(generateID.generateProductBarCode());
        }
        return productMapper.saveDO(productDo);
    }

    @Override
    public boolean updateDO(ProductDo productDo) {
        return productMapper.updateDO(productDo);
    }




    //Others
    /**
     * 等同于listDo, 只是不带分页信息返回
     * @author  ywx
     * @date    2019/1/25 8:53
     * @param   [productDo]
     * @return  java.util.List<com.ymjtt.manager.product.xdo.ProductDo>
     */
    @Override
    public List<ProductDo> listNoPage(ProductDo productDo) {
        return productMapper.listDO(productDo);
    }



}
