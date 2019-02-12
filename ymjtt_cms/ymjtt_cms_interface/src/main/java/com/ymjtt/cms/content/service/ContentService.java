package com.ymjtt.cms.content.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageInfo;
import com.ymjtt.cms.content.xdo.ContentDo;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author  ywx
 * @date    2019/1/16 13:57
 */
public interface ContentService {

    //Base
    PageInfo<ContentDo> listDO(ContentDo contentDo, Integer page, Integer rows);

    ContentDo getDO(Long id);

    boolean removeDO(Long id);

    boolean saveDO(ContentDo contentDo);

    boolean updateDO(ContentDo contentDo);


    //Others
    List<ContentDo> listDO(ContentDo contentDo) throws IOException;
}
