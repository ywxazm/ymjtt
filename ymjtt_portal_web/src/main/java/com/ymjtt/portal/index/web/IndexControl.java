package com.ymjtt.portal.index.web;

import com.github.pagehelper.PageInfo;
import com.ymjtt.manager.product.service.ProductCatService;
import com.ymjtt.manager.product.xdo.ProductCatDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @auther ywx
 * @date 2019/1/16 22:26
 **/
@Controller
@RequestMapping("/portal/indexControl")
public class IndexControl {

    @Autowired
    private ProductCatService productCatService;

    @ResponseBody
    @RequestMapping(value = "/listProductCat", produces = "application/json;charset=utf-8")
    public List<ProductCatDo>  listProductCat(Long pid) {
        ProductCatDo productCatDo = new ProductCatDo();
        productCatDo.setParentId(pid);
        PageInfo<ProductCatDo> pageInfo = productCatService.listDO(productCatDo, 1, 100);
        return pageInfo.getList();
    }

}
