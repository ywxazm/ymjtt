package com.ymjtt.manager.menu.mapper;

import com.ymjtt.manager.menu.xdo.MenuDo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author  ywx
 * @date    2018/11/22 17:47
 */
@Repository
public interface MenuMapper {

    /* Base */
    List<MenuDo> listDO(MenuDo menuDo);

    MenuDo getDO(@Param("id") Long id);

    @Delete("delete from menu where menu_id = #{id}")
    boolean removeDO(@Param("id") Long id);

    @Insert("insert into menu values(null, #{menuName}, #{icon}, #{url}, #{sortOrder}, #{parentId}, timestamp(now()), 'ywx', timestamp(now()), 'ywx', '1')")
    boolean saveDO(MenuDo menuDo);

    boolean updateDO(MenuDo menuDo);

    /* Others */
    List<MenuDo> listByParentId(@Param("parentId") Long parentId);
}
