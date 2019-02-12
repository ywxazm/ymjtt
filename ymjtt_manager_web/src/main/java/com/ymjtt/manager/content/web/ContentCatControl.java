package com.ymjtt.manager.content.web;

import com.ymjtt.cms.content.service.ContentCatService;
import com.ymjtt.cms.content.service.ContentService;
import com.ymjtt.cms.content.vo.ContentCatVO;
import com.ymjtt.cms.content.xdo.ContentCatDo;
import com.ymjtt.cms.content.xdo.ContentDo;
import com.ymjtt.common.result.CodeResult;
import com.ymjtt.common.result.DataGridVO;
import com.ymjtt.common.result.ResultVO;
import com.ymjtt.common.util.jedis.HashOper;
import com.ymjtt.common.util.jedis.RedisKey;
import com.ymjtt.common.util.json.JSONConvertUtil;
import com.ymjtt.common.vo.NodeVO;
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

    private HashOper hashOper;

    @Autowired
    public ContentCatControl(HashOper hashOper) {
        this.hashOper = hashOper;
        hashOper.del(RedisKey.CONTENT_CAT);
    }

    @ResponseBody
    @RequestMapping(value = "/list")
    public DataGridVO<ContentCatDo> list(ContentCatDo contentCatDo, Integer page, Integer rows) {
        return new DataGridVO<>(contentCatService.listDO(contentCatDo, page, rows));
    }

    @ResponseBody
    @RequestMapping(value = "/get")
    public ResultVO get(Long id) {
        return ResultVO.buildSuccessResult(CodeResult.GET_SUCCESS, contentCatService.getDO(id));
    }

    @ResponseBody
    @RequestMapping(value = "/save")
    public ResultVO save(ContentCatDo contentCatDo) {
        if (contentCatService.saveDO(contentCatDo)) {
            hashOper.hdel(RedisKey.CONTENT_CAT, contentCatDo.getParentId() + "_son");
            return ResultVO.buildSuccessResult(CodeResult.SAVE_SUCCESS);
        }
        return ResultVO.buildSuccessResult(CodeResult.SAVE_FAIL);
    }

    @ResponseBody
    @RequestMapping(value = "/update")
    public ResultVO update(ContentCatDo contentCatDo) {
        if (contentCatService.updateDO(contentCatDo)) {
            hashOper.hdel(RedisKey.CONTENT_CAT, contentCatDo.getContentCatId() + "_parent");
            hashOper.hdel(RedisKey.CONTENT_CAT, contentCatDo.getContentCatId() + "_son");
            return ResultVO.buildSuccessResult(CodeResult.UPDATE_SUCCESS);
        }
        return ResultVO.buildSuccessResult(CodeResult.UPDATE_FAIL);
    }

    @ResponseBody
    @RequestMapping(value = "/remove")
    public ResultVO remove(Long id) throws IOException {
        if (contentCatService.ifParentNode(id)) {
            return ResultVO.buildFailResult(CodeResult.REMOVE_FAIL, "ContentCatId = " + id + ", Contains Son ContentCat, No Allow Remove");
        }

        ContentDo contentDo = new ContentDo();
        contentDo.setContentCatId(id);
        List<ContentDo> contentDos = contentService.listDO(contentDo);
        if (null != contentDos && !contentDos.isEmpty()) {
            return ResultVO.buildFailResult(CodeResult.REMOVE_FAIL, "ContentCatId = " + id + ", Contains Son Content, No Allow Remove");
        }

        if (contentCatService.removeDO(id)) {
            hashOper.hdel(RedisKey.CONTENT_CAT, id + "_parent");
            hashOper.hdel(RedisKey.CONTENT_CAT, id + "_son");
            return ResultVO.buildSuccessResult(CodeResult.REMOVE_SUCCESS);
        }

        return ResultVO.buildSuccessResult(CodeResult.REMOVE_FAIL);
    }

    //Others
    /**
     * 获取所属子节点
     * @author  ywx
     * @date    2019/1/25 11:20
     * @param   [parentId] 父Id
     * @return  com.ymjtt.common.result.ResultVO
     */
    @ResponseBody
    @RequestMapping("/listByParentId")
    public ResultVO listByParentId(Long parentId) throws IOException {
        String contentCats = hashOper.hget(RedisKey.CONTENT_CAT, parentId + "_son");
        List<NodeVO> contentCatVOList;
        if (StringUtils.isEmpty(contentCats)) {
            contentCatVOList = contentCatService.listByParentId(parentId);
            hashOper.hset(RedisKey.CONTENT_CAT, parentId + "_son", JSONConvertUtil.obj2Json(contentCatVOList));
        }else {
            JSONConvertUtil<NodeVO> jsonConvertUtil = new JSONConvertUtil<>();
            contentCatVOList = jsonConvertUtil.json2List(contentCats, NodeVO.class);
        }
        return ResultVO.buildSuccessResult(contentCatVOList);
    }

    /**
     * 获取DO + 所有上级DO
     * @author  ywx
     * @date    2019/1/25 11:22
     * @param   [id]    ID
     * @return  com.ymjtt.common.result.ResultVO
     */
    @ResponseBody
    @RequestMapping("/getContainParentDo")
    public ResultVO getContainParentDo(Long id) throws IOException {
        ContentCatDo contentCatDo = contentCatService.getDO(id);
        String nodeVOs = hashOper.hget(RedisKey.CONTENT_CAT, id + "_parent");
        List<NodeVO> nodeVOList;
        if (StringUtils.isEmpty(nodeVOs)) {
            nodeVOList = contentCatService.listBySonId(id);
            hashOper.hset(RedisKey.CONTENT_CAT, id + "_parent", JSONConvertUtil.obj2Json(nodeVOList));
        }else {
            JSONConvertUtil<NodeVO> jsonConvertUtil = new JSONConvertUtil<>();
            nodeVOList = jsonConvertUtil.json2List(nodeVOs, NodeVO.class);
        }

        ContentCatVO contentCatVO = new ContentCatVO(contentCatDo);
        contentCatVO.setUpLevelNodeList(nodeVOList);
        return ResultVO.buildSuccessResult(contentCatVO);
    }

}
