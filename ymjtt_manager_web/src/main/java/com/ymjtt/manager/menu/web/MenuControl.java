package com.ymjtt.manager.menu.web;

import com.github.pagehelper.PageInfo;
import com.ymjtt.common.redis.HashOper;
import com.ymjtt.common.redis.constant.RedisKeyConstant;
import com.ymjtt.common.util.json.JSONConvertUtil;
import com.ymjtt.common.vo.DataGridVO;
import com.ymjtt.common.vo.NodeVO;
import com.ymjtt.common.vo.ResultInfoVO;
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

    @Autowired
    private HashOper hashOper;    //存储方式, RedisKey.MENU, menuId, sonMenuDo

    @ResponseBody
    @RequestMapping("/list")
    public DataGridVO<MenuDo> list(MenuDo menuDo, Integer page, Integer rows) {
        PageInfo<MenuDo> pageInfo = menuService.listDO(menuDo, page, rows);
        return new DataGridVO<>(pageInfo.getList(), pageInfo.getTotal());
    }

    @ResponseBody
    @RequestMapping("/get")
    public ResultInfoVO get(Long id) {
        return ResultInfoVO.buildSuccessInfo(menuService.getDO(id));
    }

    @ResponseBody
    @RequestMapping("/save")
    public ResultInfoVO save(MenuDo menuDo) {
        if (menuService.saveDO(menuDo)) {
            hashOper.del(RedisKeyConstant.MENU);
            return ResultInfoVO.buildSuccessInfo();
        }
        return ResultInfoVO.buildFailInfo();
    }

    @ResponseBody
    @RequestMapping("/remove")
    public ResultInfoVO remove(Long id) {
        if (menuService.ifParentNode(id)) {
            return ResultInfoVO.buildFailInfo("MenuId = " + id + ", Contains Son Menus, No Allow Remove");
        }

        if (menuService.removeDO(id)) {
            hashOper.del(RedisKeyConstant.MENU);
            return ResultInfoVO.buildSuccessInfo();
        }

        return ResultInfoVO.buildFailInfo();
    }

    @ResponseBody
    @RequestMapping("/update")
    public ResultInfoVO update(MenuDo menuDo) {
        if (menuService.updateDO(menuDo)) {
            hashOper.del(RedisKeyConstant.MENU);
            return ResultInfoVO.buildSuccessInfo();
        }
        return ResultInfoVO.buildFailInfo();
    }

    //Others
    /**
     * 获取所属子菜单VO
     * @author  ywx
     * @date    2019/1/25 11:20
     * @param   [parentId] 父菜单Id
     * @return  com.ymjtt.common.result.ResultInfoVO
     */
    @ResponseBody
    @RequestMapping("/listByParentId")
    public ResultInfoVO listByParentId(Long parentId) throws IOException {
        String menus = hashOper.hget(RedisKeyConstant.MENU, parentId + "_son");
        List<NodeVO> menuVOList;
        if (StringUtils.isEmpty(menus)) {
            menuVOList = menuService.listByParentId(parentId);
            hashOper.hset(RedisKeyConstant.MENU, parentId + "_son", JSONConvertUtil.obj2Json(menuVOList));
        }else {
            JSONConvertUtil<NodeVO> jsonConvertUtil = new JSONConvertUtil<>();
            menuVOList = jsonConvertUtil.json2List(menus, NodeVO.class);
        }
        return ResultInfoVO.buildSuccessInfo(menuVOList);
    }

    /**
     * 获取DO + 所有上级菜单DO
     * @author  ywx
     * @date    2019/1/25 11:22
     * @param   [id]    菜单ID
     * @return  com.ymjtt.common.result.ResultInfoVO
     */
    @ResponseBody
    @RequestMapping("/getContainParentDo")
    public ResultInfoVO getContainParentDo(Long id) throws IOException {
        MenuDo menuDo = menuService.getDO(id);
        String nodeVOs = hashOper.hget(RedisKeyConstant.MENU, id + "_parent");
        List<NodeVO> nodeVOList;
        if (StringUtils.isEmpty(nodeVOs)) {
            nodeVOList = menuService.listBySonId(id);
            hashOper.hset(RedisKeyConstant.MENU, id + "_parent", JSONConvertUtil.obj2Json(nodeVOList));
        }else {
            JSONConvertUtil<NodeVO> jsonConvertUtil = new JSONConvertUtil<>();
            nodeVOList = jsonConvertUtil.json2List(nodeVOs, NodeVO.class);
        }

        MenuVO menuVO = new MenuVO(menuDo);
        menuVO.setUpLevelNodeList(nodeVOList);
        return ResultInfoVO.buildSuccessInfo(menuVO);
    }

}
