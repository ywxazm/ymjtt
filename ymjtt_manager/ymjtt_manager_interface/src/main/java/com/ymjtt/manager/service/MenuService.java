package com.ymjtt.manager.service;

import com.ymjtt.manager.vo.MenuVo;
import com.ymjtt.manager.xdo.MenuDo;

import java.util.List;

/**
 *
 * @author  ywx
 * @date    2018/11/22 17:57
 */
public interface MenuService {

    List<MenuVo> listByPid(Long pid);

    MenuDo getById(Long id);

    boolean add(MenuDo menuDo);

    boolean remove(Long id);

    boolean update(MenuDo menuDo);

}
