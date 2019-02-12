package com.ymjtt.manager.product.vo;

import com.ymjtt.common.vo.NodeVO;
import com.ymjtt.manager.product.xdo.ProductAttrDo;
import com.ymjtt.manager.product.xdo.ProductAttrValueDo;
import com.ymjtt.manager.product.xdo.ProductCatDo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther ywx
 * @date 2019/1/20 14:25
 **/
public class ProductAttrVO extends NodeVO {

    private String belongName;

    public ProductAttrVO() {
    }

    public ProductAttrVO(ProductAttrDo productAttrDo) {
        this.nodeId = productAttrDo.getProductAttrId();
        this.nodeName = productAttrDo.getProductAttrName();
        this.lastNodeId = productAttrDo.getBelongId();
        Map<String, Object> param = new HashMap<>();
        param.put("attrType", productAttrDo.getAttrType());
        param.put("sortOrder", productAttrDo.getSortOrder());
        param.put("createTime", productAttrDo.getCreateTime());
        param.put("createOper", productAttrDo.getCreateOper());
        param.put("lastupdateTime", productAttrDo.getLastupdateTime());
        param.put("lastupdateOper", productAttrDo.getLastupdateOper());
        param.put("updateCounts", productAttrDo.getUpdateCounts());
        this.paramMap = param;
    }

    public String getBelongName() {
        return belongName;
    }

    public void setBelongName(String belongName) {
        this.belongName = belongName;
    }

    @Override
    public String toString() {
        return "ProductAttrVO{" + "belongName='" + belongName + '\'' + ", nodeId=" + nodeId + ", nodeName='" + nodeName + '\'' + ", nodeIcon='" + nodeIcon + '\'' + ", lastNodeId=" + lastNodeId + ", paramMap=" + paramMap + ", ifNextNode=" + ifNextNode + ", upLevelNodeList=" + upLevelNodeList + '}';
    }
}
