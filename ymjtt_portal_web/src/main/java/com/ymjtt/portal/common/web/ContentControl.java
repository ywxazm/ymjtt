package com.ymjtt.portal.common.web;

import com.ymjtt.cms.content.service.ContentService;
import com.ymjtt.cms.content.xdo.ContentDo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther ywx
 * @date 2019/2/14 15:05
 **/
@Controller
@RequestMapping("/portal/content")
public class ContentControl {

    @Autowired
    private ContentService contentService;

    @ResponseBody
    @RequestMapping(value = "/listContent", produces = "application/json;charset=utf-8")
    public List<ContentDo> listContent(Long catId) throws IOException {
        ContentDo contentDo = new ContentDo();
        contentDo.setContentCatId(catId);
        return contentService.listDO(contentDo);
    }

    @ResponseBody
    @RequestMapping(value = "/listContents", produces = "application/json;charset=utf-8")
    public List<List<ContentDo>> listContents(String catIds) throws IOException {
        List<List<ContentDo>> list = new ArrayList<>();
        String[] ids = catIds.split(",");
        for(String id : ids) {
            if (!StringUtils.isEmpty(id)) {
                ContentDo contentDo = new ContentDo();
                contentDo.setContentCatId(Long.parseLong(id));
                List<ContentDo> contentDoList = contentService.listDO(contentDo);
                list.add(contentDoList);
            }
        }
        return list;
    }

}
