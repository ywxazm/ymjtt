package com.ymjtt.manager.content.web;

import com.github.pagehelper.PageInfo;
import com.ymjtt.cms.content.service.ContentCatService;
import com.ymjtt.cms.content.service.ContentService;
import com.ymjtt.cms.content.vo.ContentVO;
import com.ymjtt.cms.content.xdo.ContentDo;
import com.ymjtt.common.fastdfs.FastDFSUtil;
import com.ymjtt.common.result.CodeResult;
import com.ymjtt.common.result.DataGridVO;
import com.ymjtt.common.result.ResultVO;
import com.ymjtt.common.util.objPackage.BeanUtil;
import com.ymjtt.common.vo.NodeVO;
import org.apache.commons.lang3.StringUtils;
import org.csource.common.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

/**
 * @auther ywx
 * @date 2019/1/16 14:40
 **/
@Controller
@RequestMapping("/manager/content")
public class ContentControl {

    @Autowired
    private ContentService contentService;

    @Autowired
    private ContentCatService contentCatService;

    @Autowired
    FastDFSUtil fastDFSUtil;

    @Autowired
    private BeanUtil<ContentDo> beanUtil;

    @ResponseBody
    @RequestMapping(value = "/list")
    public DataGridVO<ContentDo> list(ContentDo contentDo, Integer page, Integer rows) {
        PageInfo<ContentDo> pageInfo = contentService.listDO(contentDo, page, rows);
        return new DataGridVO<>(pageInfo.getList(), pageInfo.getTotal());
    }

    @ResponseBody
    @RequestMapping(value = "/get")
    public ResultVO get(Long id) {
        return ResultVO.buildSuccessResult(contentService.getDO(id));
    }

    @ResponseBody
    @RequestMapping(value = "/remove")
    public ResultVO remove(Long id) throws IOException, MyException {
        ContentDo contentDo = contentService.getDO(id);
        if (null == contentDo) {
            return ResultVO.buildFailResult(CodeResult.REMOVE_FAIL, "Have No Id = " + id + " ContentDo");
        }

        if (!StringUtils.isEmpty(contentDo.getImage())) {
            if (!fastDFSUtil.remove(contentDo.getImage())) {
                return ResultVO.buildFailResult(CodeResult.REMOVE_FAIL, "Remove Obj Image Fail");
            }
        }
        return ResultVO.buildResult(contentService.removeDO(id), CodeResult.REMOVE_SUCCESS, CodeResult.REMOVE_FAIL);
    }

    @ResponseBody
    @RequestMapping(value = "/save")
    public ResultVO save(HttpServletRequest request, MultipartFile[] multipartFiles) throws InvocationTargetException, IllegalAccessException, IOException, MyException {
        ContentDo contentDo = beanUtil.buildObj(request, new ContentDo());

        if (null != multipartFiles && multipartFiles.length != 0) {
            if (multipartFiles.length > 1) {
                throw new IllegalArgumentException("Content Image Count Must = 1");
            }

            String imgPath = fastDFSUtil.save(multipartFiles[0], new HashMap<>());
            contentDo.setImage(imgPath);
        }

        return ResultVO.buildResult(contentService.saveDO(contentDo), CodeResult.SAVE_SUCCESS, CodeResult.SAVE_FAIL);
    }

    @ResponseBody
    @RequestMapping(value = "/update")
    public ResultVO update(HttpServletRequest request, MultipartFile[] multipartFiles) throws InvocationTargetException, IllegalAccessException, IOException, MyException {
        ContentDo contentDo = beanUtil.buildObj(request, new ContentDo());

        if (null != multipartFiles && multipartFiles.length != 0) {
            if (multipartFiles.length > 1) {
                throw new IllegalArgumentException("Content Image Count Must = 1");
            }

            ContentDo c = contentService.getDO(Long.parseLong(request.getParameter("contentId")));
            boolean removeImgResult = true;
            if (!org.springframework.util.StringUtils.isEmpty(c.getImage())) {
                removeImgResult = fastDFSUtil.remove(c.getImage());
            }

            if (removeImgResult) {
                String imgPath = fastDFSUtil.save(multipartFiles[0], new HashMap<>());
                contentDo.setImage(imgPath);
            }else {
                return ResultVO.buildFailResult(CodeResult.UPDATE_FAIL, "FastDFS Remove Old Image Fail");
            }
        }
        return ResultVO.buildResult(contentService.updateDO(contentDo), CodeResult.UPDATE_SUCCESS, CodeResult.UPDATE_FAIL);
    }


    //Others
    /**
     * 内容DO + 父类别
     * @author  ywx
     * @date    2019/1/30 8:50
     * @param   [contentId] 内容ID
     * @return  com.ymjtt.common.result.ResultVO
     */
    @ResponseBody
    @RequestMapping(value = "/getContainParentDo")
    public ResultVO getContainParentDo(Long contentId) {
        ContentDo contentDo = contentService.getDO(contentId);
        List<NodeVO> nodeVOList = contentCatService.listBySonId(contentDo.getContentCatId());
        nodeVOList.add(new ContentVO(contentDo));
        ContentVO contentVO = new ContentVO(contentDo);
        contentVO.setUpLevelNodeList(nodeVOList);
        return ResultVO.buildSuccessResult(contentVO);
    }
}
