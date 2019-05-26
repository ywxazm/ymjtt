package com.ymjtt.manager.content.web;

import com.github.pagehelper.PageInfo;
import com.ymjtt.cms.content.service.ContentCatService;
import com.ymjtt.cms.content.service.ContentService;
import com.ymjtt.cms.content.vo.ContentVO;
import com.ymjtt.cms.content.xdo.ContentDo;
import com.ymjtt.common.fastdfs.FastDFSUtil;
import com.ymjtt.common.util.objPackage.BeanUtil;
import com.ymjtt.common.vo.DataGridVO;
import com.ymjtt.common.vo.NodeVO;
import com.ymjtt.common.vo.ResultInfoVO;
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
    public ResultInfoVO get(Long id) {
        return ResultInfoVO.buildSuccessInfo(contentService.getDO(id));
    }

    @ResponseBody
    @RequestMapping(value = "/remove")
    public ResultInfoVO remove(Long id) throws IOException, MyException {
        ContentDo contentDo = contentService.getDO(id);
        if (null == contentDo) {
            return ResultInfoVO.buildFailInfo("Have No Id = " + id + " ContentDo");
        }

        if (!StringUtils.isEmpty(contentDo.getImage())) {
            if (!fastDFSUtil.remove(contentDo.getImage())) {
                return ResultInfoVO.buildFailInfo("Remove Obj Image Fail");
            }
        }

        if (contentService.removeDO(id)) {
            return ResultInfoVO.buildSuccessInfo();
        }
        return ResultInfoVO.buildFailInfo();
    }

    @ResponseBody
    @RequestMapping(value = "/save")
    public ResultInfoVO save(HttpServletRequest request, MultipartFile[] multipartFiles) throws InvocationTargetException, IllegalAccessException, IOException, MyException {
        ContentDo contentDo = beanUtil.buildObj(request, new ContentDo());

        if (null != multipartFiles && multipartFiles.length != 0) {
            if (multipartFiles.length > 1) {
                throw new IllegalArgumentException("Content Image Count Must = 1");
            }

            String imgPath = fastDFSUtil.save(multipartFiles[0], new HashMap<>());
            contentDo.setImage(imgPath);
        }

        if (contentService.saveDO(contentDo)) {
            return ResultInfoVO.buildSuccessInfo();
        }
        return ResultInfoVO.buildFailInfo();
    }

    @ResponseBody
    @RequestMapping(value = "/update")
    public ResultInfoVO update(HttpServletRequest request, MultipartFile[] multipartFiles) throws InvocationTargetException, IllegalAccessException, IOException, MyException {
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
                return ResultInfoVO.buildFailInfo("FastDFS Remove Old Image Fail");
            }
        }

        if (contentService.updateDO(contentDo)) {
            return ResultInfoVO.buildSuccessInfo();
        }
        return ResultInfoVO.buildFailInfo();
    }


    //Others
    /**
     * 内容DO + 父类别
     * @author  ywx
     * @date    2019/1/30 8:50
     * @param   [contentId] 内容ID
     * @return  com.ymjtt.common.result.ResultInfoVO
     */
    @ResponseBody
    @RequestMapping(value = "/getContainParentDo")
    public ResultInfoVO getContainParentDo(Long contentId) {
        ContentDo contentDo = contentService.getDO(contentId);
        List<NodeVO> nodeVOList = contentCatService.listBySonId(contentDo.getContentCatId());
        nodeVOList.add(new ContentVO(contentDo));
        ContentVO contentVO = new ContentVO(contentDo);
        contentVO.setUpLevelNodeList(nodeVOList);
        return ResultInfoVO.buildSuccessInfo(contentVO);
    }
}
