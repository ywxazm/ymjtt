package com.ymjtt.manager.product.web;

import com.ymjtt.common.result.CodeResult;
import com.ymjtt.common.result.DataGridVO;
import com.ymjtt.common.result.ResultVO;
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
        return new DataGridVO<>(productAttrValueService.listDO(productAttrValueDo, page, rows));
    }

    @ResponseBody
    @RequestMapping(value = "/get")
    public ProductAttrValueDo get(Long id) {        //TODO: 指定参数必传的注解, 如果不传, id = null, 报错
        return productAttrValueService.getDO(id);
    }

    @ResponseBody
    @RequestMapping(value = "/remove")
    public ResultVO remove(Long id) {
        return ResultVO.buildResult(productAttrValueService.removeDO(id), CodeResult.REMOVE_SUCCESS, CodeResult.REMOVE_FAIL);
    }

    @ResponseBody
    @RequestMapping(value = "/save")
    public ResultVO save(ProductAttrValueDo productAttrValueDo) {
        return ResultVO.buildResult(productAttrValueService.saveDO(productAttrValueDo), CodeResult.SAVE_SUCCESS, CodeResult.SAVE_FAIL);
    }

    @ResponseBody
    @RequestMapping(value = "/update")
    public ResultVO update(ProductAttrValueDo productAttrValueDo){
        return ResultVO.buildResult(productAttrValueService.updateDO(productAttrValueDo), CodeResult.UPDATE_SUCCESS, CodeResult.UPDATE_FAIL);
    }


    /* Others */
    @ResponseBody
    @RequestMapping(value = "/listNoPage")
    public ResultVO listNoPage(ProductAttrValueDo productAttrValueDo){
        return ResultVO.buildSuccessResult(productAttrValueService.listNoPage(productAttrValueDo));
    }
}
