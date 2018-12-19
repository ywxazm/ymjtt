package com.ymjtt.manager.xdo;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @auther ywx
 * @date 2018/11/22 17:50
 **/
@Entity
@Table(name = "menu", schema = "ymjtt")
public class MenuDo implements Serializable{
    private Long menuId;
    private String menuName;
    private Byte sortOrder;
    private String url;
    private Byte parentId;
    private Timestamp createTime;
    private Timestamp lastupdateTime;

    @Id
    @Column(name = "menu_id")
    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    @Basic
    @Column(name = "menu_name")
    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    @Basic
    @Column(name = "sort_order")
    public Byte getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Byte sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "parent_id")
    public Byte getParentId() {
        return parentId;
    }

    public void setParentId(Byte parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "createTime")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "lastupdateTime")
    public Timestamp getLastupdateTime() {
        return lastupdateTime;
    }

    public void setLastupdateTime(Timestamp lastupdateTime) {
        this.lastupdateTime = lastupdateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuDo menuDo = (MenuDo) o;

        if (menuId != null ? !menuId.equals(menuDo.menuId) : menuDo.menuId != null) return false;
        if (menuName != null ? !menuName.equals(menuDo.menuName) : menuDo.menuName != null) return false;
        if (sortOrder != null ? !sortOrder.equals(menuDo.sortOrder) : menuDo.sortOrder != null) return false;
        if (url != null ? !url.equals(menuDo.url) : menuDo.url != null) return false;
        if (parentId != null ? !parentId.equals(menuDo.parentId) : menuDo.parentId != null) return false;
        if (createTime != null ? !createTime.equals(menuDo.createTime) : menuDo.createTime != null) return false;
        if (lastupdateTime != null ? !lastupdateTime.equals(menuDo.lastupdateTime) : menuDo.lastupdateTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = menuId != null ? menuId.hashCode() : 0;
        result = 31 * result + (menuName != null ? menuName.hashCode() : 0);
        result = 31 * result + (sortOrder != null ? sortOrder.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (lastupdateTime != null ? lastupdateTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MenuDo{" + "menuId=" + menuId + ", menuName='" + menuName + '\'' + ", sortOrder=" + sortOrder + ", url='" + url + '\'' + ", parentId=" + parentId + ", createTime=" + createTime + ", lastupdateTime=" + lastupdateTime + '}';
    }
}
