package com.ymjtt.portal.search.web;

import com.github.pagehelper.PageInfo;
import com.ymjtt.common.result.DataGridVO;
import com.ymjtt.manager.product.service.ProductService;
import com.ymjtt.manager.product.xdo.ProductDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @auther ywx
 * @date 2019/1/20 22:22
 **/
@Controller
@RequestMapping("/portal/product")
public class ProductControl {

    /** 商品搜索页面, 显示第几页 */
    private static final Integer defaultPage = 1;

    /** 商品搜索页面, 每页显示数 */
    private static final Integer defaultRows = 50;

    @Autowired
    private ProductService productService;

    @ResponseBody
    @RequestMapping(value = "/list")
    public DataGridVO<ProductDo> list(ProductDo productDo) {
        PageInfo<ProductDo> pageInfo = productService.listDO(productDo, 1, 50);
        return new DataGridVO<>(pageInfo);
    }
}
