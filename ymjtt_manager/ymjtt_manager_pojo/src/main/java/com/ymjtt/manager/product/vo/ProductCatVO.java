package com.ymjtt.manager.product.vo;

import com.ymjtt.common.vo.NodeVO;
import com.ymjtt.manager.product.xdo.ProductCatDo;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther ywx
 * @date 2019/1/27 20:21
 **/
public class ProductCatVO extends NodeVO {

    public ProductCatVO() {
    }

    public ProductCatVO(ProductCatDo productCatDo) {
        this.nodeId = productCatDo.getProductCatId();
        this.nodeName = productCatDo.getProductCatName();
        this.lastNodeId = productCatDo.getParentId();

        Map<String, Object> param = new HashMap<>();
        param.put("productCatStatus", productCatDo.getProductCatStatus());
        param.put("sortOrder", productCatDo.getSortOrder());
        param.put("createTime", productCatDo.getCreateTime());
        param.put("createOper", productCatDo.getCreateOper());
        param.put("lastupdateTime", productCatDo.getLastupdateTime());
        param.put("lastupdateOper", productCatDo.getLastupdateOper());
        param.put("updateCounts", productCatDo.getUpdateCounts());
        this.paramMap = param;
    }

    @Override
    public String toString() {
        return "ProductCatVO{" + "nodeId=" + nodeId + ", nodeName='" + nodeName + '\'' + ", nodeIcon='" + nodeIcon + '\'' + ", lastNodeId=" + lastNodeId + ", paramMap=" + paramMap + ", ifNextNode=" + ifNextNode + ", upLevelNodeList=" + upLevelNodeList + '}';
    }
}
