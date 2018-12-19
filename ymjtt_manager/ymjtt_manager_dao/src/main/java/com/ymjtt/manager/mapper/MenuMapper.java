package com.ymjtt.manager.mapper;

import com.ymjtt.manager.xdo.MenuDo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author  ywx
 * @date    2018/11/22 17:47
 */
@Repository
public interface MenuMapper {

    List<MenuDo> listByPid(Long pid);

    MenuDo getById(Long id);
}
