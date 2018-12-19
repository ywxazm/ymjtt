package com.ymjtt.manager.service.impl;

import com.ymjtt.manager.mapper.MenuMapper;
import com.ymjtt.manager.service.MenuService;
import com.ymjtt.manager.xdo.MenuDo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品服务
 * @author  ywx
 * @date    2018/11/21 15:20
 */
@Service
public class MenuServiceImpl implements MenuService {

    private static final Logger logger = LoggerFactory.getLogger(MenuMapper.class);

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<MenuDo> listByPid(Long pid) {
        logger.debug("the thread id is {}, MenuServiceImpl.listByPid() param pid = {}", Thread.currentThread().getId(), pid);

        Long startTime = System.currentTimeMillis();

        pid = (null == pid) ? 0L : pid;

        List<MenuDo> menuDoList = menuMapper.listByPid(pid);

        logger.debug("the thread id is {}, MenuServiceImpl.listByPid() service cast time is {}"
                , Thread.currentThread().getId(), System.currentTimeMillis() - startTime);

        return menuDoList;
    }

    @Override
    public MenuDo getById(Long id) {
        logger.debug("the thread id is {}, MenuServiceImpl.getById() param id = {}", Thread.currentThread().getId(), id);

        Long startTime = System.currentTimeMillis();

        MenuDo menuDo = menuMapper.getById(id);

        logger.debug("the thread id is {}, MenuServiceImpl.getById() service cast time is {}"
                , Thread.currentThread().getId(), System.currentTimeMillis() - startTime);

        return menuDo;
    }

}
