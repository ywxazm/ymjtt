package com.ymjtt.portal.index.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageInfo;
import com.ymjtt.cms.content.service.ContentService;
import com.ymjtt.cms.content.xdo.ContentDo;
import com.ymjtt.common.vo.NodeVO;
import com.ymjtt.manager.product.service.ProductCatService;
import com.ymjtt.manager.product.xdo.ProductCatDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

/**
 * @auther ywx
 * @date 2019/1/16 22:26
 **/
@Controller
@RequestMapping("/portal/listProcess")
public class ListProcessControl {

    @Autowired
    private ProductCatService productCatService;

    @Autowired
    private ContentService contentService;

    @Value("${productCatParentId}")
    private Long productCatParentId;

    @ResponseBody
    @RequestMapping(value = "/listProductCat", produces = "application/json;charset=utf-8")
    public List<ProductCatDo>  listProductCat(Long pid) {
        ProductCatDo productCatDo = new ProductCatDo();
        productCatDo.setParentId(pid);
        PageInfo<ProductCatDo> pageInfo = productCatService.listDO(productCatDo, 1, 100);
        return pageInfo.getList();
    }

    @ResponseBody
    @RequestMapping(value = "/listContent", produces = "application/json;charset=utf-8")
    public List<ContentDo> listContent(Long catId) throws IOException {
        ContentDo contentDo = new ContentDo();
        contentDo.setContentCatId(catId);
        return contentService.listDO(contentDo);
    }
}
