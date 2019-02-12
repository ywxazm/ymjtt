package com.ymjtt.cms.content.xdo;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @auther ywx
 * @date 2019/1/22 16:32
 **/
@Entity
@Table(name = "content_cat", schema = "ymjtt")
public class ContentCatDo implements Serializable{
    protected Long contentCatId;
    protected Long parentId;
    protected String contentCatName;
    protected Integer contentCatStatus;
    protected Integer sortOrder;
    protected Timestamp createTime;
    protected String createOper;
    protected Timestamp lastupdateTime;
    protected String lastupdateOper;
    protected Integer updateCounts;

    @Id
    @Column(name = "content_cat_id")
    public Long getContentCatId() {
        return contentCatId;
    }

    public void setContentCatId(Long contentCatId) {
        this.contentCatId = contentCatId;
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
    @Column(name = "content_cat_name")
    public String getContentCatName() {
        return contentCatName;
    }

    public void setContentCatName(String contentCatName) {
        this.contentCatName = contentCatName;
    }

    @Basic
    @Column(name = "content_cat_status")
    public Integer getContentCatStatus() {
        return contentCatStatus;
    }

    public void setContentCatStatus(Integer contentCatStatus) {
        this.contentCatStatus = contentCatStatus;
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
    @Column(name = "createTime")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
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
    @Column(name = "lastupdateTime")
    public Timestamp getLastupdateTime() {
        return lastupdateTime;
    }

    public void setLastupdateTime(Timestamp lastupdateTime) {
        this.lastupdateTime = lastupdateTime;
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

        ContentCatDo that = (ContentCatDo) o;

        if (contentCatId != null ? !contentCatId.equals(that.contentCatId) : that.contentCatId != null) return false;
        if (parentId != null ? !parentId.equals(that.parentId) : that.parentId != null) return false;
        if (contentCatName != null ? !contentCatName.equals(that.contentCatName) : that.contentCatName != null)
            return false;
        if (contentCatStatus != null ? !contentCatStatus.equals(that.contentCatStatus) : that.contentCatStatus != null)
            return false;
        if (sortOrder != null ? !sortOrder.equals(that.sortOrder) : that.sortOrder != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (createOper != null ? !createOper.equals(that.createOper) : that.createOper != null) return false;
        if (lastupdateTime != null ? !lastupdateTime.equals(that.lastupdateTime) : that.lastupdateTime != null)
            return false;
        if (lastupdateOper != null ? !lastupdateOper.equals(that.lastupdateOper) : that.lastupdateOper != null)
            return false;
        if (updateCounts != null ? !updateCounts.equals(that.updateCounts) : that.updateCounts != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = contentCatId != null ? contentCatId.hashCode() : 0;
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (contentCatName != null ? contentCatName.hashCode() : 0);
        result = 31 * result + (contentCatStatus != null ? contentCatStatus.hashCode() : 0);
        result = 31 * result + (sortOrder != null ? sortOrder.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (createOper != null ? createOper.hashCode() : 0);
        result = 31 * result + (lastupdateTime != null ? lastupdateTime.hashCode() : 0);
        result = 31 * result + (lastupdateOper != null ? lastupdateOper.hashCode() : 0);
        result = 31 * result + (updateCounts != null ? updateCounts.hashCode() : 0);
        return result;
    }
}
