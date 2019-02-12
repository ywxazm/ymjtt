package com.ymjtt.manager.menu.vo;

import com.ymjtt.common.vo.NodeVO;
import com.ymjtt.manager.menu.xdo.MenuDo;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther ywx
 * @date 2019/1/26 17:23
 **/
public class MenuVO extends NodeVO {

    public MenuVO() {
    }

    public MenuVO(MenuDo menuDo) {
        this.nodeId = menuDo.getMenuId();
        this.nodeName = menuDo.getMenuName();
        this.nodeIcon = menuDo.getIcon();
        this.lastNodeId = menuDo.getParentId();
        Map<String, Object> param = new HashMap<>();
        param.put("url", menuDo.getUrl());
        param.put("sortOrder", menuDo.getSortOrder());
        param.put("createTime", menuDo.getCreateTime());
        param.put("createOper", menuDo.getCreateOper());
        param.put("lastupdateTime", menuDo.getLastupdateTime());
        param.put("lastupdateOper", menuDo.getLastupdateOper());
        param.put("updateCounts", menuDo.getUpdateCounts());
        this.paramMap = param;
    }

    @Override
    public String toString() {
        return "MenuVO{" + "nodeId=" + nodeId + ", nodeName='" + nodeName + '\'' + ", nodeIcon='" + nodeIcon + '\'' + ", lastNodeId=" + lastNodeId + ", ifNextNode=" + ifNextNode + ", upLevelNodeList=" + upLevelNodeList + ", paramMap=" + paramMap + '}';
    }
}
