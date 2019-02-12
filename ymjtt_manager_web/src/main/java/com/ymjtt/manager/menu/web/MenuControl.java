package com.ymjtt.manager.menu.web;

import com.ymjtt.common.result.CodeResult;
import com.ymjtt.common.result.DataGridVO;
import com.ymjtt.common.result.ResultVO;
import com.ymjtt.common.util.jedis.HashOper;
import com.ymjtt.common.util.jedis.RedisKey;
import com.ymjtt.common.util.json.JSONConvertUtil;
import com.ymjtt.common.vo.NodeVO;
import com.ymjtt.manager.menu.service.MenuService;
import com.ymjtt.manager.menu.vo.MenuVO;
import com.ymjtt.manager.menu.xdo.MenuDo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

/**
 * 菜单管理
 * @auther ywx
 * @date 2018/11/22 17:21
 **/
@Controller
@RequestMapping("/manager/menu")
public class MenuControl {

    @Autowired      //执行Construct后
    private MenuService menuService;

    private HashOper hashOper;    //存储方式, RedisKey.MENU, menuId, sonMenuDo

    public MenuControl() {
    }

    @Autowired      //执行Construct前, 会先去注入HashOper
    public MenuControl(HashOper hashOper) {
        this.hashOper = hashOper;
        hashOper.del(RedisKey.MENU);
    }

    @ResponseBody
    @RequestMapping("/list")
    public DataGridVO<MenuDo> list(MenuDo menuDo, Integer page, Integer rows) {
        return new DataGridVO<>(menuService.listDO(menuDo, page, rows));
    }

    @ResponseBody
    @RequestMapping("/get")
    public ResultVO get(Long id) {
        return ResultVO.buildSuccessResult(CodeResult.GET_SUCCESS, menuService.getDO(id));
    }

    @ResponseBody
    @RequestMapping("/save")
    public ResultVO save(MenuDo menuDo) {
        if (menuService.saveDO(menuDo)) {
            hashOper.del(RedisKey.MENU);
            return ResultVO.buildSuccessResult(CodeResult.SAVE_SUCCESS);
        }
        return ResultVO.buildFailResult(CodeResult.SAVE_FAIL);
    }

    @ResponseBody
    @RequestMapping("/remove")
    public ResultVO remove(Long id) {
        if (menuService.ifParentNode(id)) {
            return ResultVO.buildFailResult(CodeResult.REMOVE_FAIL, "MenuId = " + id + ", Contains Son Menus, No Allow Remove");
        }

        if (menuService.removeDO(id)) {
            hashOper.del(RedisKey.MENU);
            return ResultVO.buildSuccessResult(CodeResult.REMOVE_SUCCESS);
        }

        return ResultVO.buildFailResult(CodeResult.REMOVE_FAIL);
    }

    @ResponseBody
    @RequestMapping("/update")
    public ResultVO update(MenuDo menuDo) {
        if (menuService.updateDO(menuDo)) {
            hashOper.del(RedisKey.MENU);
            return ResultVO.buildSuccessResult(CodeResult.UPDATE_SUCCESS);
        }
        return ResultVO.buildFailResult(CodeResult.UPDATE_FAIL);
    }

    //Others
    /**
     * 获取所属子菜单VO
     * @author  ywx
     * @date    2019/1/25 11:20
     * @param   [parentId] 父菜单Id
     * @return  com.ymjtt.common.result.ResultVO
     */
    @ResponseBody
    @RequestMapping("/listByParentId")
    public ResultVO listByParentId(Long parentId) throws IOException {
        String menus = hashOper.hget(RedisKey.MENU, parentId + "_son");
        List<NodeVO> menuVOList;
        if (StringUtils.isEmpty(menus)) {
            menuVOList = menuService.listByParentId(parentId);
            hashOper.hset(RedisKey.MENU, parentId + "_son", JSONConvertUtil.obj2Json(menuVOList));
        }else {
            JSONConvertUtil<NodeVO> jsonConvertUtil = new JSONConvertUtil<>();
            menuVOList = jsonConvertUtil.json2List(menus, NodeVO.class);
        }
        return ResultVO.buildSuccessResult(menuVOList);
    }

    /**
     * 获取DO + 所有上级菜单DO
     * @author  ywx
     * @date    2019/1/25 11:22
     * @param   [id]    菜单ID
     * @return  com.ymjtt.common.result.ResultVO
     */
    @ResponseBody
    @RequestMapping("/getContainParentDo")
    public ResultVO getContainParentDo(Long id) throws IOException {
        MenuDo menuDo = menuService.getDO(id);
        String nodeVOs = hashOper.hget(RedisKey.MENU, id + "_parent");
        List<NodeVO> nodeVOList;
        if (StringUtils.isEmpty(nodeVOs)) {
            nodeVOList = menuService.listBySonId(id);
            hashOper.hset(RedisKey.MENU, id + "_parent", JSONConvertUtil.obj2Json(nodeVOList));
        }else {
            JSONConvertUtil<NodeVO> jsonConvertUtil = new JSONConvertUtil<>();
            nodeVOList = jsonConvertUtil.json2List(nodeVOs, NodeVO.class);
        }

        MenuVO menuVO = new MenuVO(menuDo);
        menuVO.setUpLevelNodeList(nodeVOList);
        return ResultVO.buildSuccessResult(menuVO);
    }

}
