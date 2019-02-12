package com.ymjtt.manager.menu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ymjtt.common.vo.NodeVO;
import com.ymjtt.manager.menu.mapper.MenuMapper;
import com.ymjtt.manager.menu.service.MenuService;
import com.ymjtt.manager.menu.vo.MenuVO;
import com.ymjtt.manager.menu.xdo.MenuDo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 菜单服务
 * @author  ywx
 * @date    2018/11/21 15:20
 */
@Service
public class MenuServiceImpl implements MenuService {

    private static final Logger logger = LoggerFactory.getLogger(MenuService.class);

    @Autowired
    private MenuMapper menuMapper;

    @Value("${topMenuParentId}")
    private Long topMenuParentId;

    //Base
    @Override
    public PageInfo<MenuDo> listDO(MenuDo menuDo, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<MenuDo> list = menuMapper.listDO(menuDo);
        return new PageInfo<>(list);
    }

    @Override
    public MenuDo getDO(Long id) {
        return menuMapper.getDO(id);
    }

    @Override
    public boolean saveDO(MenuDo menuDo) {
        return menuMapper.saveDO(menuDo);
    }

    @Override
    public boolean removeDO(Long id) {
        return menuMapper.removeDO(id);
    }

    @Override
    public boolean updateDO(MenuDo menuDo) {
        return menuMapper.updateDO(menuDo);
    }

    //Others
    /**
     * 查询子类菜单
     * @author  ywx
     * @date    2019/1/25 11:01
     * @param   [parentId]  父菜单ID
     * @return  java.util.List<com.ymjtt.manager.menu.vo.MenuVO>
     */
    @Override
    public List<NodeVO> listByParentId(Long parentId) {
        List<MenuDo> menuDoList = menuMapper.listByParentId(parentId);
        List<NodeVO> menuVOList = new ArrayList<>();
        for(MenuDo menuDo : menuDoList) {
            List<MenuDo> menuDos = menuMapper.listByParentId(menuDo.getMenuId());
            MenuVO menuVO = new MenuVO(menuDo);
            menuVO.setIfNextNode(menuDos != null && !menuDos.isEmpty());
            menuVOList.add(menuVO);
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
        List<MenuDo> menuDoList = menuMapper.listByParentId(id);
        return null != menuDoList && !menuDoList.isEmpty();
    }

    /**
     * 查找所有父菜单DO
     * @author  ywx
     * @date    2019/1/25 11:19
     * @param   [sonId] 菜单ID
     * @return  java.util.List<com.ymjtt.manager.menu.xdo.MenuDo>
     */
    @Override
    public List<NodeVO> listBySonId(Long sonId) {
        List<NodeVO> menuVOList = new ArrayList<>();
        do {
            MenuDo menuDo = menuMapper.getDO(sonId);
            menuVOList.add(new MenuVO(menuDo));
            sonId = menuDo.getParentId();
        }while (!topMenuParentId.equals(sonId));
        Collections.reverse(menuVOList);
        return menuVOList;
    }

}
