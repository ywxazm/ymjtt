package com.ymjtt.manager.vo;

import com.ymjtt.manager.xdo.MenuDo;

/**
 * @auther ywx
 * @date 2018/12/27 14:53
 **/
public class MenuVo extends MenuDo {
    private Integer parentMenu; //是否为父菜单

    public MenuVo() {       //TODO:反序列化时,dubbo是采用Hessian（比jdk自带反序列化高效）,选取参数最少的构造实例化对象
    }

    public MenuVo(MenuDo menuDo) {
        this.menuId = menuDo.getMenuId();
        this.menuName = menuDo.getMenuName();
        this.sortOrder = menuDo.getSortOrder();
        this.url = menuDo.getUrl();
        this.parentId = menuDo.getParentId();
        this.createTime = menuDo.getCreateTime();
        this.lastupdateTime = menuDo.getLastupdateTime();
    }

    public Integer getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(Integer parentMenu) {
        this.parentMenu = parentMenu;
    }
}
