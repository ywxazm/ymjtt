package com.ymjtt.manager.menu.service;

import com.github.pagehelper.PageInfo;
import com.ymjtt.common.vo.NodeVO;
import com.ymjtt.manager.menu.xdo.MenuDo;

import java.util.List;

/**
 *
 * @author  ywx
 * @date    2018/11/22 17:57
 */
public interface MenuService{

    //Base
    PageInfo<MenuDo> listDO(MenuDo menuDo, Integer page, Integer rows);

    MenuDo getDO(Long id);

    boolean removeDO(Long id);

    boolean saveDO(MenuDo menuDo);

    boolean updateDO(MenuDo menuDo);

    //Others
    List<NodeVO> listByParentId(Long parentId);

    List<NodeVO> listBySonId(Long sonId);

    boolean ifParentNode(Long id);

}
