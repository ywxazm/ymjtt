package com.ymjtt.cms.content.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ymjtt.cms.content.mapper.ContentCatMapper;
import com.ymjtt.cms.content.service.ContentCatService;
import com.ymjtt.cms.content.vo.ContentCatVO;
import com.ymjtt.cms.content.xdo.ContentCatDo;
import com.ymjtt.common.vo.NodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author  ywx
 * @date    2019/1/27 15:24
 */
@Service
public class ContentCatServiceImpl implements ContentCatService {

    @Autowired
    private ContentCatMapper contentCatMapper;

    @Value("${topContentCatParentId}")
    private Long topContentCatParentId;

    @Override
    public PageInfo<ContentCatDo> listDO(ContentCatDo contentCatDo, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<ContentCatDo> contentCatDoList = contentCatMapper.listDO(contentCatDo);
        return new PageInfo<>(contentCatDoList);
    }

    @Override
    public ContentCatDo getDO(Long id) {
        return contentCatMapper.getDO(id);
    }

    @Override
    public boolean removeDO(Long id) {
        return contentCatMapper.removeDO(id);
    }

    @Override
    public boolean saveDO(ContentCatDo contentCatDo) {
        return contentCatMapper.saveDO(contentCatDo);
    }

    @Override
    public boolean updateDO(ContentCatDo contentCatDo) {
        return contentCatMapper.updateDO(contentCatDo);
    }




    //Others
    /**
     * 查询子
     * @author  ywx
     * @date    2019/1/27 15:16
     * @param   [parentId]  父ID
     * @return  java.util.List<com.ymjtt.common.vo.NodeVO>
     */
    @Override
    public List<NodeVO> listByParentId(Long parentId) {
        List<ContentCatDo> contentCatDoList = contentCatMapper.listByParentId(parentId);
        List<NodeVO> menuVOList = new ArrayList<>();
        for(ContentCatDo contentCatDo : contentCatDoList) {
            List<ContentCatDo> contentCatDos = contentCatMapper.listByParentId(contentCatDo.getContentCatId());
            ContentCatVO contentCatVO = new ContentCatVO(contentCatDo);
            contentCatVO.setIfNextNode(contentCatDos != null && !contentCatDos.isEmpty());
            menuVOList.add(contentCatVO);
        }
        return menuVOList;
    }

    /**
     * 查询是否有子类菜单
     * @author  ywx
     * @date    2019/1/25 11:01
     * @param   [parentId]  菜单ID
     * @return  java.util.List<com.ymjtt.manager.menu.vo.MenuVO>
     */
    @Override
    public boolean ifParentNode(Long id) {
        List<ContentCatDo> contentCatDoList = contentCatMapper.listByParentId(id);
        return null != contentCatDoList && !contentCatDoList.isEmpty();
    }

    /**
     * 查找所有DO
     * @author  ywx
     * @date    2019/1/25 11:19
     * @param   [sonId] ID
     * @return  java.util.List<com.ymjtt.manager.menu.xdo.MenuDo>
     */
    @Override
    public List<NodeVO> listBySonId(Long sonId) {
        List<NodeVO> menuVOList = new ArrayList<>();
        do {
            ContentCatDo contentCatDo = contentCatMapper.getDO(sonId);
            menuVOList.add(new ContentCatVO(contentCatDo));
            sonId = contentCatDo.getParentId();
        }while (!topContentCatParentId.equals(sonId));
        Collections.reverse(menuVOList);
        return menuVOList;
    }
}
