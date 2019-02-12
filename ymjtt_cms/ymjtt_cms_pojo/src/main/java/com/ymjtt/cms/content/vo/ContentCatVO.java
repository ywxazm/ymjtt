package com.ymjtt.cms.content.vo;

import com.ymjtt.cms.content.xdo.ContentCatDo;
import com.ymjtt.common.vo.NodeVO;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther ywx
 * @date 2019/1/16 15:16
 **/
public class ContentCatVO extends NodeVO {

    public ContentCatVO() {
    }

    public ContentCatVO(ContentCatDo contentCatDo) {
        this.nodeId = contentCatDo.getContentCatId();
        this.nodeName = contentCatDo.getContentCatName();
        this.lastNodeId = contentCatDo.getParentId();

        Map<String, Object> param = new HashMap<>();
        param.put("contentCatStatus", contentCatDo.getContentCatStatus());
        param.put("sortOrder", contentCatDo.getSortOrder());
        param.put("createTime", contentCatDo.getCreateTime());
        param.put("createOper", contentCatDo.getCreateOper());
        param.put("lastupdateTime", contentCatDo.getLastupdateTime());
        param.put("lastupdateOper", contentCatDo.getLastupdateOper());
        param.put("updateCounts", contentCatDo.getUpdateCounts());
        this.paramMap = param;
    }

    @Override
    public String toString() {
        return "ContentCatVO{" + "nodeId=" + nodeId + ", nodeName='" + nodeName + '\'' + ", nodeIcon='" + nodeIcon + '\'' + ", lastNodeId=" + lastNodeId + ", paramMap=" + paramMap + ", ifNextNode=" + ifNextNode + ", upLevelNodeList=" + upLevelNodeList + '}';
    }
}
