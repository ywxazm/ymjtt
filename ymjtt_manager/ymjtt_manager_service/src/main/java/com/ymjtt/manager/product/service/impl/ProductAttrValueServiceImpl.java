package com.ymjtt.manager.product.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ymjtt.common.util.jedis.GenerateID;
import com.ymjtt.manager.product.mapper.ProductAttrValueMapper;
import com.ymjtt.manager.product.service.ProductAttrValueService;
import com.ymjtt.manager.product.xdo.ProductAttrValueDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品服务
 * @author  ywx
 * @date    2018/11/21 15:20
 */
@Service
public class ProductAttrValueServiceImpl<T> implements ProductAttrValueService {

    @Autowired
    private ProductAttrValueMapper productAttrValueMapper;

    @Autowired
    private GenerateID generateID;

    @Override
    public PageInfo<ProductAttrValueDo> listDO(ProductAttrValueDo productAttrValueDo, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<ProductAttrValueDo> list = productAttrValueMapper.listDO(productAttrValueDo);
        return new PageInfo<>(list);
    }

    @Override
    public ProductAttrValueDo getDO(Long id) {
        return productAttrValueMapper.getDO(id);
    }

    @Override
    public boolean removeDO(Long id) {
        return productAttrValueMapper.removeDO(id);
    }

    @Override
    public boolean saveDO(ProductAttrValueDo productAttrValueDo) {
        return productAttrValueMapper.saveDO(productAttrValueDo);
    }

    @Override
    public boolean updateDO(ProductAttrValueDo productAttrValueDo) {
        return productAttrValueMapper.updateDO(productAttrValueDo);
    }




    //Others
    @Override
    public List<ProductAttrValueDo> listNoPage(ProductAttrValueDo productAttrValueDo) {
        return productAttrValueMapper.listDO(productAttrValueDo);
    }

    @Override
    public boolean removeList(Long productAttrId) {
        return productAttrValueMapper.removeList(productAttrId);
    }

}
