package com.ymjtt.manager.product.web;

import com.github.pagehelper.PageInfo;
import com.ymjtt.common.vo.DataGridVO;
import com.ymjtt.common.vo.ResultInfoVO;
import com.ymjtt.manager.product.service.ProductAttrValueService;
import com.ymjtt.manager.product.xdo.ProductAttrValueDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 商品
 * @author  ywx
 * @date    2018/11/21 15:53
 */
@Service
@RequestMapping("/manager/productAttrValue")
public class ProductAttrValueControl {

    @Autowired
    private ProductAttrValueService productAttrValueService;

    @ResponseBody
    @RequestMapping(value = "/list")
    public DataGridVO<ProductAttrValueDo> list(ProductAttrValueDo productAttrValueDo, Integer page, Integer rows) {
        PageInfo<ProductAttrValueDo> pageInfo = productAttrValueService.listDO(productAttrValueDo, page, rows);
        return new DataGridVO<>(pageInfo.getList(), pageInfo.getTotal());
    }

    @ResponseBody
    @RequestMapping(value = "/get")
    public ProductAttrValueDo get(Long id) {        //TODO: 指定参数必传的注解, 如果不传, id = null, 报错
        return productAttrValueService.getDO(id);
    }

    @ResponseBody
    @RequestMapping(value = "/remove")
    public ResultInfoVO remove(Long id) {
        return productAttrValueService.removeDO(id) ? ResultInfoVO.buildSuccessInfo() : ResultInfoVO.buildFailInfo();
    }

    @ResponseBody
    @RequestMapping(value = "/save")
    public ResultInfoVO save(ProductAttrValueDo productAttrValueDo) {
        return productAttrValueService.saveDO(productAttrValueDo) ? ResultInfoVO.buildSuccessInfo() : ResultInfoVO.buildFailInfo();
    }

    @ResponseBody
    @RequestMapping(value = "/update")
    public ResultInfoVO update(ProductAttrValueDo productAttrValueDo){
        return productAttrValueService.updateDO(productAttrValueDo) ? ResultInfoVO.buildSuccessInfo() : ResultInfoVO.buildFailInfo();
    }


    /* Others */
    @ResponseBody
    @RequestMapping(value = "/listNoPage")
    public ResultInfoVO listNoPage(ProductAttrValueDo productAttrValueDo){
        return ResultInfoVO.buildSuccessInfo(productAttrValueService.listNoPage(productAttrValueDo));
    }
}
