package com.ymjtt.cms.content.vo;

import com.ymjtt.cms.content.xdo.ContentDo;
import com.ymjtt.common.vo.NodeVO;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther ywx
 * @date 2019/1/16 15:46
 **/
public class ContentVO extends NodeVO {

    public ContentVO() {
    }

    public ContentVO(ContentDo contentDo) {
        this.nodeId = contentDo.getContentId();
        this.nodeName = contentDo.getContentName();
        this.lastNodeId = contentDo.getContentCatId();
        Map<String, Object> param = new HashMap<>();
        param.put("sortOrder", contentDo.getSortOrder());
        param.put("contentDesc", contentDo.getContentDesc());
        param.put("url", contentDo.getUrl());
        param.put("image", contentDo.getImage());
        param.put("createTime", contentDo.getCreateTime());
        param.put("createOper", contentDo.getCreateOper());
        param.put("lastupdateTime", contentDo.getLastupdateTime());
        param.put("lastupdateOper", contentDo.getLastupdateOper());
        param.put("updateCounts", contentDo.getUpdateCounts());
        this.paramMap = param;
    }

    @Override
    public String toString() {
        return "ContentVO{" + "nodeId=" + nodeId + ", nodeName='" + nodeName + '\'' + ", nodeIcon='" + nodeIcon + '\'' + ", lastNodeId=" + lastNodeId + ", paramMap=" + paramMap + ", ifNextNode=" + ifNextNode + ", upLevelNodeList=" + upLevelNodeList + '}';
    }
}
