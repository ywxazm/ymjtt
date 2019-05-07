package com.ymjtt.portal.page.web;

import com.ymjtt.manager.product.service.ProductService;
import com.ymjtt.manager.product.xdo.ProductDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 页面访问
 * @auther ywx
 * @date 2018/11/21 22:17
 **/
@Controller
public class PageControl {

    @Autowired
    private ProductService productService;

    /**
     * 首页的访问方式
     * @author  ywx
     * @date    2018/12/9 20:28
     * @param   []
     * @return  java.lang.String
     */
    @RequestMapping("/")
    public String index() {
        return "portal/index";
}

    /**
     * 其它Html页面访问方式
     * @author  ywx
     * @date    2018/12/9 20:29
     * @param   [path01, page02]
     * @return  java.lang.String
     */
    @RequestMapping("/htmlPage/{path01}/{page02}")
    public String showPage(@PathVariable("path01") String path01, @PathVariable("page02") String page02) {
        return path01 + "/" + page02;
    }

    /**
     * 商品DO + 商品详情视图
     * @author  ywx
     * @date    2019/2/12 17:21
     * @param   [productId, modelAndView]  商品ID
     * @return  org.springframework.web.servlet.ModelAndView
     */
    @RequestMapping("/getProductDetailPage")
    public ModelAndView getProductDetailPage(Long productId, ModelAndView modelAndView) {
        modelAndView.setViewName("product/productDetail");
        ProductDo productDo = productService.getDO(productId);
        modelAndView.addObject(productDo);
        return modelAndView;
    }

}
