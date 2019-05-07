package com.ymjtt.manager.product.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ymjtt.common.vo.NodeVO;
import com.ymjtt.manager.product.mapper.ProductCatMapper;
import com.ymjtt.manager.product.mapper.ProductMapper;
import com.ymjtt.manager.product.service.ProductCatService;
import com.ymjtt.manager.product.vo.ProductCatVO;
import com.ymjtt.manager.product.xdo.ProductCatDo;
import com.ymjtt.manager.util.GenerateId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @auther ywx
 * @date 2018/11/20 14:32
 **/
@Service
public class ProductCatServiceImpl implements ProductCatService {

    @Autowired
    private ProductCatMapper productCatMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private GenerateId generateID;

    @Value("${topProductCatId}")
    private Long topProductCatId;

    @Override
    public PageInfo<ProductCatDo> listDO(ProductCatDo productCatDo, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<ProductCatDo> list = productCatMapper.listDO(productCatDo);
        return new PageInfo<>(list);
    }

    @Override
    public ProductCatDo getDO(Long id) {
        return productCatMapper.getDO(id);
    }

    @Override
    public Boolean updateDO(ProductCatDo productCatDo) {
        return productCatMapper.updateDO(productCatDo);
    }

    @Override
    public Boolean saveDO(ProductCatDo productCatDo) {
        productCatDo.setProductCatId(Long.parseLong(generateID.generateProductCatId()));
        return productCatMapper.saveDO(productCatDo);
    }

    @Override
    public Boolean removeDO(Long id) {
        return productCatMapper.removeDO(id);
    }


    //Others
    /**
     * 查询子
     * @author  ywx
     * @date    2019/1/25 11:01
     * @param   [parentId]  父ID
     * @return  java.util.List<com.ymjtt.manager.menu.vo.MenuVO>
     */
    @Override
    public List<NodeVO> listByParentId(Long parentId) {
        List<ProductCatDo> productCatDoList = productCatMapper.listByParentId(parentId);
        List<NodeVO> productCatVOList = new ArrayList<>();
        for(ProductCatDo productCatDo : productCatDoList) {
            List<ProductCatDo> productCatDos = productCatMapper.listByParentId(productCatDo.getProductCatId());
            ProductCatVO productCatVO = new ProductCatVO(productCatDo);
            productCatVO.setIfNextNode(productCatDos != null && !productCatDos.isEmpty());
            productCatVOList.add(productCatVO);
        }
        return productCatVOList;
    }

    /**
     * 查询是否有子
     * @author  ywx
     * @date    2019/1/27 20:28
     * @param   [id] 父ID
     * @return  boolean
     */
    @Override
    public boolean ifParentNode(Long id) {
        List<ProductCatDo> productCatDoList = productCatMapper.listByParentId(id);
        return null != productCatDoList && !productCatDoList.isEmpty();
    }

    /**
     * 查找所有父DO
     * @author  ywx
     * @date    2019/1/27 20:28
     * @param   [sonId] 子ID
     * @return  java.util.List<com.ymjtt.common.vo.NodeVO>
     */
    @Override
    public List<NodeVO> listBySonId(Long sonId) {
        List<NodeVO> productCatVOList = new ArrayList<>();
        do {
            ProductCatDo productCatDo = productCatMapper.getDO(sonId);
            if (null == productCatDo) {
                break;
            }
            sonId = productCatDo.getParentId();
            productCatVOList.add(new ProductCatVO(productCatDo));
        }while (!topProductCatId.equals(sonId));
        Collections.reverse(productCatVOList);
        return productCatVOList;
    }

}
