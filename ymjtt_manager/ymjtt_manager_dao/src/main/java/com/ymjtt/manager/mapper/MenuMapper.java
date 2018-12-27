package com.ymjtt.manager.mapper;

import com.ymjtt.manager.xdo.MenuDo;
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

    List<MenuDo> listByPid(Long pid);

    MenuDo getById(Long id);

    @Insert("insert into menu values(null, #{menuName}, #{sortOrder}, #{url}, #{parentId}, timestamp(now()), timestamp(now()))")
    boolean add(MenuDo menuDo);

    @Delete("delete from menu where menu_id = #{id}")
    boolean remove(@Param("id") Long id);

    boolean update(MenuDo menuDo);
}
