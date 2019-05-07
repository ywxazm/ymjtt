package com.ymjtt.manager.product.web;

import com.github.pagehelper.PageInfo;
import com.ymjtt.common.redis.HashOper;
import com.ymjtt.common.result.CodeResult;
import com.ymjtt.common.result.DataGridVO;
import com.ymjtt.common.result.ResultVO;
import com.ymjtt.common.vo.NodeVO;
import com.ymjtt.manager.product.service.ProductAttrService;
import com.ymjtt.manager.product.service.ProductAttrValueService;
import com.ymjtt.manager.product.service.ProductCatService;
import com.ymjtt.manager.product.service.ProductService;
import com.ymjtt.manager.product.vo.ProductAttrVO;
import com.ymjtt.manager.product.vo.ProductCatVO;
import com.ymjtt.manager.product.vo.ProductVO;
import com.ymjtt.manager.product.xdo.ProductAttrDo;
import com.ymjtt.manager.product.xdo.ProductAttrValueDo;
import com.ymjtt.manager.product.xdo.ProductCatDo;
import com.ymjtt.manager.product.xdo.ProductDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.JedisCluster;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * 商品
 * @author  ywx
 * @date    2018/11/21 15:53
 */
@Service
@RequestMapping("/manager/productAttr")
public class ProductAttrControl {

    @Autowired
    private ProductAttrService productAttrService;

    @Autowired
    private ProductAttrValueService productAttrValueService;

    @Autowired
    private ProductCatService productCatService;

    @Autowired
    private ProductService productService;

    @Autowired
    private HashOper hashOper;

    @ResponseBody
    @RequestMapping(value = "/list")
    public DataGridVO<ProductAttrDo> list(ProductAttrDo productAttrDo, Integer page, Integer rows) {
        PageInfo<ProductAttrDo> pageInfo = productAttrService.listDO(productAttrDo, page, rows);
        return new DataGridVO<>(pageInfo.getList(), pageInfo.getTotal());
    }

    @ResponseBody
    @RequestMapping(value = "/get")
    public ProductAttrDo get(Long id) {        //TODO: 指定参数必传的注解, 如果不传, id = null, 报错
        return productAttrService.getDO(id);
    }

    @ResponseBody
    @RequestMapping(value = "/remove")
    public ResultVO remove(Long id) {
        ProductAttrValueDo productAttrValueDo = new ProductAttrValueDo();
        productAttrValueDo.setProductAttrId(id);
        List<ProductAttrValueDo> productAttrValueDoList = productAttrValueService.listNoPage(productAttrValueDo);
        if (null != productAttrValueDoList || !productAttrValueDoList.isEmpty()) {
            ResultVO.buildFailResult(CodeResult.REMOVE_FAIL, "ProductAttrId = " + id + " Contain Attr Value");
        }
        return ResultVO.buildResult(productAttrService.removeDO(id), CodeResult.REMOVE_SUCCESS, CodeResult.REMOVE_FAIL);
    }

    @ResponseBody
    @RequestMapping(value = "/save")
    public ResultVO save(ProductAttrDo productAttrDo) {
        return ResultVO.buildResult(productAttrService.saveDO(productAttrDo), CodeResult.SAVE_SUCCESS, CodeResult.SAVE_FAIL);
    }

    @ResponseBody
    @RequestMapping(value = "/update")
    public ResultVO update(ProductAttrDo productAttrDo){
        return ResultVO.buildResult(productAttrService.updateDO(productAttrDo), CodeResult.UPDATE_SUCCESS, CodeResult.UPDATE_FAIL);
    }

    /* Others */
    /**
     * 等同于list(), 新增belongName的字段
     * @author  ywx
     * @date    2019/1/24 14:32
     * @param   [id] 规格ID
     * @return  com.ymjtt.common.result.ResultVO
     */
    @ResponseBody
    @RequestMapping(value = "/listContainBelongName")
    public DataGridVO listContainBelongName(ProductAttrDo productAttrDo, Integer page, Integer rows) throws InvocationTargetException, IllegalAccessException, IntrospectionException {
        PageInfo<List<Map<String, Object>>> pageInfo = productAttrService.listVO(productAttrDo, page, rows);
        return new DataGridVO<>(pageInfo.getList(), pageInfo.getTotal());
    }

    /**
     * 等同于get(), 新增belongName的字段 + 上级目录DO
     * @author  ywx
     * @date    2019/1/24 14:32
     * @param   [id] 规格ID
     * @return  com.ymjtt.common.result.ResultVO
     */
    @ResponseBody
    @RequestMapping(value = "/getContainParentDo")
    public ResultVO getContainParentDo(Long id) {
        ProductAttrDo productAttrDo = productAttrService.getDO(id);
        ProductAttrVO productAttrVO = new ProductAttrVO(productAttrDo);
        Integer attrType = productAttrDo.getAttrType();
        String belongName;
        List<NodeVO> nodeVOList;
        switch (attrType) {
            case 1: {
                belongName = productService.getDO(productAttrDo.getBelongId()).getProductName();
                ProductDo productDo = productService.getDO(productAttrDo.getBelongId());
                ProductVO productVO = new ProductVO(productDo);
                nodeVOList = productCatService.listBySonId(productDo.getCid());
                nodeVOList.add(productVO);
                break;
            }
            case 2: {
                belongName = productCatService.getDO(productAttrDo.getBelongId()).getProductCatName();
                ProductCatDo productCatDo = productCatService.getDO(productAttrDo.getBelongId());
                ProductCatVO productCatVO = new ProductCatVO(productCatDo);
                nodeVOList = productCatService.listBySonId(productCatDo.getParentId());
                nodeVOList.add(productCatVO);
                break;
            }
            default: {
                throw new IllegalArgumentException("AttrType Must == 1 Or 2");
            }
        }
        productAttrVO.setBelongName(belongName);
        nodeVOList.add(new ProductAttrVO(productAttrDo));
        productAttrVO.setUpLevelNodeList(nodeVOList);
        return ResultVO.buildSuccessResult(productAttrVO);
    }

    /**
     * 查询商品所有规格(包含所属类别规格)
     * @author  ywx
     * @date    2019/2/11 15:34
     * @param   [productId] 商品ID
     * @return  java.util.List<com.ymjtt.manager.product.xdo.ProductAttrDo>
     */
    @ResponseBody
    @RequestMapping(value = "/listAttrByProductId")
    public List<ProductAttrDo> listAttrByProductId(Long productId) {
        return productAttrService.listByBelongId(productId);
    }

}
