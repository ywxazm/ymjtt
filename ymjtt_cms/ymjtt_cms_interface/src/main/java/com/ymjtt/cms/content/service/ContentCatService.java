package com.ymjtt.cms.content.service;

import com.github.pagehelper.PageInfo;
import com.ymjtt.cms.content.xdo.ContentCatDo;
import com.ymjtt.common.vo.NodeVO;

import java.util.List;

/**
 * @auther ywx
 * @date 2019/1/16 13:53
 **/
public interface ContentCatService {

    //Base
    PageInfo<ContentCatDo> listDO(ContentCatDo contentCatDo, Integer page, Integer rows);

    ContentCatDo getDO(Long id);

    boolean removeDO(Long id);

    boolean saveDO(ContentCatDo contentCatDo);

    boolean updateDO(ContentCatDo contentCatDo);


    //Others
    List<NodeVO> listByParentId(Long parentId);

    List<NodeVO> listBySonId(Long sonId);

    boolean ifParentNode(Long id);

}
