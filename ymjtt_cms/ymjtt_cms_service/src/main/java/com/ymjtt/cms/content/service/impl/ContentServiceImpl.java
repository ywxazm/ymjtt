package com.ymjtt.cms.content.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ymjtt.cms.content.mapper.ContentMapper;
import com.ymjtt.cms.content.service.ContentService;
import com.ymjtt.cms.content.xdo.ContentDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @auther ywx
 * @date 2019/1/16 13:58
 **/
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public PageInfo<ContentDo> listDO(ContentDo contentDo, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<ContentDo> contentDoList = contentMapper.listDO(contentDo);
        return new PageInfo<>(contentDoList);
    }

    @Override
    public ContentDo getDO(Long id) {
        return contentMapper.getDO(id);
    }

    @Override
    public boolean removeDO(Long id) {
        return contentMapper.removeDO(id);
    }

    @Override
    public boolean saveDO(ContentDo contentDo) {
        return contentMapper.saveDO(contentDo);
    }

    @Override
    public boolean updateDO(ContentDo contentDo) {
        return contentMapper.updateDO(contentDo);
    }

    //Others
    @Override
    public List<ContentDo> listDO(ContentDo contentDo) throws IOException {
        return contentMapper.listDO(contentDo);
    }
}
