package com.ymjtt.manager.product.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ymjtt.common.util.fastdfs.FastDFSUtil;
import com.ymjtt.manager.product.mapper.ProductDetailMapper;
import com.ymjtt.manager.product.service.ProductDetailService;
import com.ymjtt.manager.product.xdo.ProductDetailDo;
import org.csource.common.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * 商品服务
 * @author  ywx
 * @date    2018/11/21 15:20
 */
@Service
public class ProductDetailServiceImpl implements ProductDetailService {

    @Autowired
    private ProductDetailMapper productDetailMapper;

    @Autowired
    private FastDFSUtil fastDFSUtil;

    @Override
    public PageInfo<ProductDetailDo> listDO(ProductDetailDo productDetailDo, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        return new PageInfo<>(productDetailMapper.listDO(productDetailDo));
    }

    @Override
    public ProductDetailDo getDO(Long id) {
        return productDetailMapper.getDO(id);
    }

    @Override
    public boolean removeDO(Long id) throws IOException, MyException {
        ProductDetailDo productDetailDo = productDetailMapper.getDO(id);
        if (fastDFSUtil.remove(productDetailDo.getImage())) {
            return productDetailMapper.removeDO(id);
        }
        return false;
    }

    @Override
    public boolean saveDO(Long productId, byte[] data, String extensionName) throws IOException, MyException {
        String path = fastDFSUtil.save(data, extensionName, new HashMap<>());
        ProductDetailDo productDetailDo = new ProductDetailDo();
        productDetailDo.setProductId(productId);
        productDetailDo.setImage(path);
        productDetailDo.setCreateOper("ywx");
        productDetailDo.setLastupdateOper("ywx");
        return productDetailMapper.saveDO(productDetailDo);
    }

    @Deprecated
    @Override
    public boolean updateDO(Long productDetailId, MultipartFile multipartFile) throws IOException, MyException {
        ProductDetailDo productDetailDo = productDetailMapper.getDO(productDetailId);
        if (!fastDFSUtil.remove(productDetailDo.getImage())) {
            return false;
        }
        String path = fastDFSUtil.save(multipartFile, new HashMap<>());
        productDetailDo.setImage(path);
        productDetailDo.setLastupdateOper("ywx");
        return productDetailMapper.updateDO(productDetailDo);
    }


    //Others
    @Override
    public List<ProductDetailDo> listNoPage(ProductDetailDo productDetailDo) {
        return productDetailMapper.listDO(productDetailDo);
    }

    /**
     * 获取商品详情
     * @author  ywx
     * @date    2019/1/29 21:08
     * @param   [productId] 商品ID
     * @return  java.util.List<com.ymjtt.manager.product.xdo.ProductDetailDo>
     */
    @Override
    public List<ProductDetailDo> listByProductId(Long productId) {
        ProductDetailDo productDetailDo = new ProductDetailDo();
        productDetailDo.setProductId(productId);
        return productDetailMapper.listDO(productDetailDo);
    }

    @Override
    public boolean removeByParentId(Long parentId) throws IOException, MyException {
        ProductDetailDo productDetailDo = new ProductDetailDo();
        productDetailDo.setProductId(parentId);
        List<ProductDetailDo> productDetailDoList = productDetailMapper.listDO(productDetailDo);
        for (ProductDetailDo p : productDetailDoList) {
            fastDFSUtil.remove(p.getImage());
        }
        return productDetailMapper.removeByParentId(parentId);
    }
}
