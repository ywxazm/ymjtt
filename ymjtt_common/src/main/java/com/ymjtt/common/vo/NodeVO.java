package com.ymjtt.common.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 树形结构, 后台返回数据类型继承此类
 * @auther ywx
 * @date 2019/1/24 21:30
 **/
public class NodeVO implements Serializable {

    protected Long nodeId;
    protected String nodeName;
    protected String nodeIcon;
    protected Long lastNodeId;
    protected Map<String, Object> paramMap;     /* 存放以上属性之外的所有属性值 */

    protected Boolean ifNextNode;
    protected List<NodeVO> upLevelNodeList;          /* 上级目录列表 */

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodeIcon() {
        return nodeIcon;
    }

    public void setNodeIcon(String nodeIcon) {
        this.nodeIcon = nodeIcon;
    }

    public Long getLastNodeId() {
        return lastNodeId;
    }

    public void setLastNodeId(Long lastNodeId) {
        this.lastNodeId = lastNodeId;
    }

    public Boolean getIfNextNode() {
        return ifNextNode;
    }

    public void setIfNextNode(Boolean ifNextNode) {
        this.ifNextNode = ifNextNode;
    }

    public List<NodeVO> getUpLevelNodeList() {
        return upLevelNodeList;
    }

    public void setUpLevelNodeList(List<NodeVO> upLevelNodeList) {
        this.upLevelNodeList = upLevelNodeList;
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NodeVO nodeVO = (NodeVO) o;

        if (nodeId != null ? !nodeId.equals(nodeVO.nodeId) : nodeVO.nodeId != null) return false;
        if (nodeName != null ? !nodeName.equals(nodeVO.nodeName) : nodeVO.nodeName != null) return false;
        if (nodeIcon != null ? !nodeIcon.equals(nodeVO.nodeIcon) : nodeVO.nodeIcon != null) return false;
        if (lastNodeId != null ? !lastNodeId.equals(nodeVO.lastNodeId) : nodeVO.lastNodeId != null) return false;
        if (ifNextNode != null ? !ifNextNode.equals(nodeVO.ifNextNode) : nodeVO.ifNextNode != null) return false;
        if (upLevelNodeList != null ? !upLevelNodeList.equals(nodeVO.upLevelNodeList) : nodeVO.upLevelNodeList != null)
            return false;
        if (paramMap != null ? !paramMap.equals(nodeVO.paramMap) : nodeVO.paramMap != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nodeId != null ? nodeId.hashCode() : 0;
        result = 31 * result + (nodeName != null ? nodeName.hashCode() : 0);
        result = 31 * result + (nodeIcon != null ? nodeIcon.hashCode() : 0);
        result = 31 * result + (lastNodeId != null ? lastNodeId.hashCode() : 0);
        result = 31 * result + (ifNextNode != null ? ifNextNode.hashCode() : 0);
        result = 31 * result + (upLevelNodeList != null ? upLevelNodeList.hashCode() : 0);
        result = 31 * result + (paramMap != null ? paramMap.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "NodeVO{" + "nodeId=" + nodeId + ", nodeName='" + nodeName + '\'' + ", nodeIcon='" + nodeIcon + '\'' + ", lastNodeId=" + lastNodeId + ", ifNextNode=" + ifNextNode + ", upLevelNodeList=" + upLevelNodeList + ", paramMap=" + paramMap + '}';
    }
}
