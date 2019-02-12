package com.ymjtt.cms.content.mapper;

import com.ymjtt.cms.content.xdo.ContentDo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author  ywx
 * @date    2019/1/9 9:51
 */
@Repository
public interface ContentMapper {

    /* Base */
    List<ContentDo> listDO(ContentDo contentDo);

    ContentDo getDO(Long id);

    @Insert("insert into content values(null, #{contentCatId}, #{contentName}, #{sortOrder}, #{contentDesc}, #{url}, #{image}, timestamp(now()), 'ywx',timestamp(now()), 'ywx', '1')")
    boolean saveDO(ContentDo contentDo);

    @Delete("delete from content where content_id = #{contentId}")
    boolean removeDO(@Param("contentId") Long contentId);

    boolean updateDO(ContentDo contentDo);

    /* Others */
}
