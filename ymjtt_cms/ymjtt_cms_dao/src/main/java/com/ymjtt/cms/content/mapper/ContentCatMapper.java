package com.ymjtt.cms.content.mapper;

import com.ymjtt.cms.content.xdo.ContentCatDo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author  ywx
 * @date    2019/1/16 11:00
 */
@Repository
public interface ContentCatMapper {

    /* Base */
    List<ContentCatDo> listDO(ContentCatDo contentCatDo);

    ContentCatDo getDO(@Param("id") Long id);

    @Delete("delete from content_cat where content_cat_id = #{id}")
    boolean removeDO(@Param("id") Long id);

    @Insert("insert into content_cat values(null, #{parentId}, #{contentCatName}, #{contentCatStatus}, #{sortOrder}, timestamp(now()), 'ywx',timestamp(now()), 'ywx', '1')")
    boolean saveDO(ContentCatDo contentCatDo);

    boolean updateDO(ContentCatDo contentCatDo);

    /* Others */
    List<ContentCatDo> listByParentId(@Param("parentId") Long parentId);
}
