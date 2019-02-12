package com.ymjtt.manager.product.web;

import com.github.pagehelper.PageInfo;
import com.ymjtt.common.result.CodeResult;
import com.ymjtt.common.result.DataGridVO;
import com.ymjtt.common.result.ResultVO;
import com.ymjtt.common.util.fastdfs.FastDFSUtil;
import com.ymjtt.common.util.file.FileUtils;
import com.ymjtt.manager.product.service.ProductDetailService;
import com.ymjtt.manager.product.xdo.ProductDetailDo;
import org.apache.commons.lang3.StringUtils;
import org.csource.common.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author  ywx
 * @date    2019/1/17 16:42
 */
@Service
@RequestMapping("/manager/productDetail")
public class ProductDetailControl {

    private static Logger logger = LoggerFactory.getLogger(ProductDetailControl.class);

    @Autowired
    private ProductDetailService productDetailService;

    @Autowired
    private FastDFSUtil fastDFSUtil;

    @ResponseBody
    @RequestMapping(value = "/list")
    public DataGridVO<ProductDetailDo> list(ProductDetailDo productDetailDo, Integer page, Integer rows) {
        PageInfo<ProductDetailDo> pageInfo = productDetailService.listDO(productDetailDo, page, rows);
        return new DataGridVO<>(pageInfo);
    }

    @ResponseBody
    @RequestMapping(value = "/get")
    public ProductDetailDo get(Long id) {
        return productDetailService.getDO(id);
    }

    @ResponseBody
    @RequestMapping(value = "/remove")
    public ResultVO remove(Long id) throws IOException, MyException {
        return ResultVO.buildResult(productDetailService.removeDO(id), CodeResult.REMOVE_SUCCESS, CodeResult.REMOVE_FAIL);
    }

    @ResponseBody
    @RequestMapping(value = "/save")
    public ResultVO save(HttpServletRequest request, MultipartFile[] multipartFiles) throws IOException, MyException {
        String productId = request.getParameter("productId");
        if (StringUtils.isEmpty(productId)) {
            return ResultVO.buildFailResult(CodeResult.SAVE_FAIL, "ProductId Must IS Not null");
        }

        if (null == multipartFiles || multipartFiles.length == 0) {
            return ResultVO.buildFailResult(CodeResult.SAVE_FAIL, "multipartFiles Must IS Not null");
        }

        boolean saveResult = true;
        for (MultipartFile multipartFile : multipartFiles) {
            saveResult = productDetailService.saveDO(Long.parseLong(productId), multipartFile.getBytes(), FileUtils.getExtensionName(multipartFile.getName()));
        }
        return ResultVO.buildResult(saveResult, CodeResult.SAVE_SUCCESS, CodeResult.SAVE_FAIL);
    }

    @ResponseBody
    @RequestMapping(value = "/update")
    public ResultVO update(HttpServletRequest request, MultipartFile[] multipartFiles) throws IOException, MyException {
        String productId = request.getParameter("productId");
        if (StringUtils.isEmpty(productId)) {
            return ResultVO.buildFailResult(CodeResult.SAVE_FAIL, "ProductId Must IS Not null");
        }

        if (null == multipartFiles || multipartFiles.length == 0) {
            return ResultVO.buildFailResult(CodeResult.SAVE_FAIL, "multipartFiles Must IS Not null");
        }

        if (!productDetailService.removeByParentId(Long.parseLong(productId))) {
            return ResultVO.buildFailResult(CodeResult.SAVE_FAIL, "Del Old Image Or Db Fail");
        }

        boolean saveResult = true;
        for (MultipartFile multipartFile : multipartFiles) {
            saveResult = productDetailService.saveDO(Long.parseLong(productId), multipartFile.getBytes(), FileUtils.getExtensionName(multipartFile.getName()));
        }
        return ResultVO.buildResult(saveResult, CodeResult.UPDATE_SUCCESS, CodeResult.SAVE_FAIL);
    }

    //Others
    @ResponseBody
    @RequestMapping(value = "/listNoPage")
    public List<ProductDetailDo> listNoPage(ProductDetailDo productDetailDo) {
        return productDetailService.listNoPage(productDetailDo);
    }
}
