package com.ymjtt.manager.menu.xdo;

import com.ymjtt.common.vo.NodeVO;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @auther ywx
 * @date 2019/1/9 17:35
 **/
@Entity
@Table(name = "menu", schema = "ymjtt")
public class MenuDo implements Serializable {
    protected Long menuId;
    protected String menuName;
    protected String icon;
    protected String url;
    protected Integer sortOrder;
    protected Long parentId;
    protected Timestamp createTime;
    protected Timestamp lastupdateTime;
    protected String createOper;
    protected String lastupdateOper;
    protected Integer updateCounts;

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
    @Column(name = "icon")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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
    @Column(name = "sort_order")
    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Basic
    @Column(name = "parent_id")
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
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

    @Basic
    @Column(name = "createOper")
    public String getCreateOper() {
        return createOper;
    }

    public void setCreateOper(String createOper) {
        this.createOper = createOper;
    }

    @Basic
    @Column(name = "lastupdateOper")
    public String getLastupdateOper() {
        return lastupdateOper;
    }

    public void setLastupdateOper(String lastupdateOper) {
        this.lastupdateOper = lastupdateOper;
    }

    @Basic
    @Column(name = "update_counts")
    public Integer getUpdateCounts() {
        return updateCounts;
    }

    public void setUpdateCounts(Integer updateCounts) {
        this.updateCounts = updateCounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuDo menuDo = (MenuDo) o;

        if (menuId != null ? !menuId.equals(menuDo.menuId) : menuDo.menuId != null) return false;
        if (menuName != null ? !menuName.equals(menuDo.menuName) : menuDo.menuName != null) return false;
        if (icon != null ? !icon.equals(menuDo.icon) : menuDo.icon != null) return false;
        if (url != null ? !url.equals(menuDo.url) : menuDo.url != null) return false;
        if (sortOrder != null ? !sortOrder.equals(menuDo.sortOrder) : menuDo.sortOrder != null) return false;
        if (parentId != null ? !parentId.equals(menuDo.parentId) : menuDo.parentId != null) return false;
        if (createTime != null ? !createTime.equals(menuDo.createTime) : menuDo.createTime != null) return false;
        if (lastupdateTime != null ? !lastupdateTime.equals(menuDo.lastupdateTime) : menuDo.lastupdateTime != null)
            return false;
        if (createOper != null ? !createOper.equals(menuDo.createOper) : menuDo.createOper != null) return false;
        if (lastupdateOper != null ? !lastupdateOper.equals(menuDo.lastupdateOper) : menuDo.lastupdateOper != null)
            return false;
        if (updateCounts != null ? !updateCounts.equals(menuDo.updateCounts) : menuDo.updateCounts != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = menuId != null ? menuId.hashCode() : 0;
        result = 31 * result + (menuName != null ? menuName.hashCode() : 0);
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (sortOrder != null ? sortOrder.hashCode() : 0);
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (lastupdateTime != null ? lastupdateTime.hashCode() : 0);
        result = 31 * result + (createOper != null ? createOper.hashCode() : 0);
        result = 31 * result + (lastupdateOper != null ? lastupdateOper.hashCode() : 0);
        result = 31 * result + (updateCounts != null ? updateCounts.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MenuDo{" + "menuId=" + menuId + ", menuName='" + menuName + '\'' + ", icon='" + icon + '\'' + ", url='" + url + '\'' + ", sortOrder=" + sortOrder + ", parentId=" + parentId + ", createTime=" + createTime + ", lastupdateTime=" + lastupdateTime + ", createOper='" + createOper + '\'' + ", lastupdateOper='" + lastupdateOper + '\'' + ", updateCounts=" + updateCounts + '}';
    }
}
