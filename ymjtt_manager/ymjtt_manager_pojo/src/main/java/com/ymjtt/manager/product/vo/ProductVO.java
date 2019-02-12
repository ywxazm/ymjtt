package com.ymjtt.manager.product.vo;

import com.ymjtt.common.vo.NodeVO;
import com.ymjtt.manager.product.xdo.ProductDo;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther ywx
 * @date 2019/1/26 17:23
 **/
public class ProductVO extends NodeVO {

    public ProductVO() {
    }

    public ProductVO(ProductDo productDo) {
        this.nodeId = productDo.getProductId();
        this.nodeName = productDo.getProductName();
        this.lastNodeId = productDo.getCid();
        Map<String, Object> param = new HashMap<>();
        param.put("sellPoint", productDo.getSellPoint());
        param.put("basePrice", productDo.getBasePrice());
        param.put("image", productDo.getImage());
        param.put("barcode", productDo.getBarcode());
        param.put("productStatus", productDo.getProductStatus());
        param.put("ifCreateProductAttrRelation", productDo.getIfCreateProductAttrRelation());
        param.put("createTime", productDo.getCreateTime());
        param.put("createOper", productDo.getCreateOper());
        param.put("lastupdateTime", productDo.getLastupdateTime());
        param.put("lastupdateOper", productDo.getLastupdateOper());
        param.put("updateCounts", productDo.getUpdateCounts());
        this.paramMap = param;
    }

    @Override
    public String toString() {
        return "ProductVO{" + "nodeId=" + nodeId + ", nodeName='" + nodeName + '\'' + ", nodeIcon='" + nodeIcon + '\'' + ", lastNodeId=" + lastNodeId + ", paramMap=" + paramMap + ", ifNextNode=" + ifNextNode + ", upLevelNodeList=" + upLevelNodeList + '}';
    }
}
