package com.ymjtt.manager.content.web;

import com.github.pagehelper.PageInfo;
import com.ymjtt.cms.content.service.ContentCatService;
import com.ymjtt.cms.content.service.ContentService;
import com.ymjtt.cms.content.vo.ContentCatVO;
import com.ymjtt.cms.content.xdo.ContentCatDo;
import com.ymjtt.cms.content.xdo.ContentDo;
import com.ymjtt.common.redis.HashOper;
import com.ymjtt.common.redis.constant.RedisKeyConstant;
import com.ymjtt.common.util.json.JSONConvertUtil;
import com.ymjtt.common.vo.DataGridVO;
import com.ymjtt.common.vo.NodeVO;
import com.ymjtt.common.vo.ResultInfoVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

/**
 * @auther ywx
 * @date 2019/1/16 14:39
 **/
@Controller
@RequestMapping("/manager/contentCat")
public class ContentCatControl {

    @Autowired
    private ContentCatService contentCatService;

    @Autowired
    private ContentService contentService;

    @Autowired
    private HashOper hashOper;

    @ResponseBody
    @RequestMapping(value = "/list")
    public DataGridVO<ContentCatDo> list(ContentCatDo contentCatDo, Integer page, Integer rows) {
        PageInfo<ContentCatDo> pageInfo = contentCatService.listDO(contentCatDo, page, rows);
        return new DataGridVO<>(pageInfo.getList(), pageInfo.getTotal());
    }

    @ResponseBody
    @RequestMapping(value = "/get")
    public ResultInfoVO get(Long id) {
        return ResultInfoVO.buildSuccessInfo(contentCatService.getDO(id));
    }

    @ResponseBody
    @RequestMapping(value = "/save")
    public ResultInfoVO save(ContentCatDo contentCatDo) {
        if (contentCatService.saveDO(contentCatDo)) {
            hashOper.del(RedisKeyConstant.CONTENT_CAT);
            return ResultInfoVO.buildSuccessInfo();
        }
        return ResultInfoVO.buildSuccessInfo("save fail");
    }

    @ResponseBody
    @RequestMapping(value = "/update")
    public ResultInfoVO update(ContentCatDo contentCatDo) {
        if (contentCatService.updateDO(contentCatDo)) {
            hashOper.del(RedisKeyConstant.CONTENT_CAT);
            return ResultInfoVO.buildSuccessInfo();
        }
        return ResultInfoVO.buildFailInfo("update fail");
    }

    @ResponseBody
    @RequestMapping(value = "/remove")
    public ResultInfoVO remove(Long id) throws IOException {
        if (contentCatService.ifParentNode(id)) {
            return ResultInfoVO.buildFailInfo("ContentCatId = " + id + ", Contains Son ContentCat, No Allow Remove");
        }

        ContentDo contentDo = new ContentDo();
        contentDo.setContentCatId(id);
        List<ContentDo> contentDos = contentService.listDO(contentDo);
        if (null != contentDos && !contentDos.isEmpty()) {
            return ResultInfoVO.buildFailInfo("ContentCatId = " + id + ", Contains Son Content, No Allow Remove");
        }

        if (contentCatService.removeDO(id)) {
            hashOper.del(RedisKeyConstant.CONTENT_CAT);
            return ResultInfoVO.buildSuccessInfo();
        }

        return ResultInfoVO.buildFailInfo();
    }

    //Others
    /**
     * 获取所属子节点
     * @author  ywx
     * @date    2019/1/25 11:20
     * @param   [parentId] 父Id
     * @return  com.ymjtt.common.result.ResultInfoVO
     */
    @ResponseBody
    @RequestMapping("/listByParentId")
    public ResultInfoVO listByParentId(Long parentId) throws IOException {
        String contentCats = hashOper.hget(RedisKeyConstant.CONTENT_CAT, parentId + "_son");
        List<NodeVO> contentCatVOList;
        if (StringUtils.isEmpty(contentCats)) {
            contentCatVOList = contentCatService.listByParentId(parentId);
            hashOper.hset(RedisKeyConstant.CONTENT_CAT, parentId + "_son", JSONConvertUtil.obj2Json(contentCatVOList));
        }else {
            JSONConvertUtil<NodeVO> jsonConvertUtil = new JSONConvertUtil<>();
            contentCatVOList = jsonConvertUtil.json2List(contentCats, NodeVO.class);
        }
        return ResultInfoVO.buildSuccessInfo(contentCatVOList);
    }

    /**
     * 获取DO + 所有上级DO
     * @author  ywx
     * @date    2019/1/25 11:22
     * @param   [id]    ID
     * @return  com.ymjtt.common.result.ResultInfoVO
     */
    @ResponseBody
    @RequestMapping("/getContainParentDo")
    public ResultInfoVO getContainParentDo(Long id) throws IOException {
        ContentCatDo contentCatDo = contentCatService.getDO(id);
        String nodeVOs = hashOper.hget(RedisKeyConstant.CONTENT_CAT, id + "_parent");
        List<NodeVO> nodeVOList;
        if (StringUtils.isEmpty(nodeVOs)) {
            nodeVOList = contentCatService.listBySonId(id);
            hashOper.hset(RedisKeyConstant.CONTENT_CAT, id + "_parent", JSONConvertUtil.obj2Json(nodeVOList));
        }else {
            JSONConvertUtil<NodeVO> jsonConvertUtil = new JSONConvertUtil<>();
            nodeVOList = jsonConvertUtil.json2List(nodeVOs, NodeVO.class);
        }

        ContentCatVO contentCatVO = new ContentCatVO(contentCatDo);
        contentCatVO.setUpLevelNodeList(nodeVOList);
        return ResultInfoVO.buildSuccessInfo(contentCatVO);
    }

}
