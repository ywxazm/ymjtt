package com.ymjtt.cms.content.xdo;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @auther ywx
 * @date 2019/1/22 16:32
 **/
@Entity
@Table(name = "content", schema = "ymjtt")
public class ContentDo implements Serializable{
    protected Long contentId;
    protected Long contentCatId;
    protected String contentName;
    protected Integer sortOrder;
    protected String contentDesc;
    protected String url;
    protected String image;
    protected Timestamp createTime;
    protected String createOper;
    protected Timestamp lastupdateTime;
    protected String lastupdateOper;
    protected Integer updateCounts;

    @Id
    @Column(name = "content_id")
    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    @Basic
    @Column(name = "content_cat_id")
    public Long getContentCatId() {
        return contentCatId;
    }

    public void setContentCatId(Long contentCatId) {
        this.contentCatId = contentCatId;
    }

    @Basic
    @Column(name = "content_name")
    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
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
    @Column(name = "content_desc")
    public String getContentDesc() {
        return contentDesc;
    }

    public void setContentDesc(String contentDesc) {
        this.contentDesc = contentDesc;
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
    @Column(name = "image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

        ContentDo contentDo = (ContentDo) o;

        if (contentId != null ? !contentId.equals(contentDo.contentId) : contentDo.contentId != null) return false;
        if (contentCatId != null ? !contentCatId.equals(contentDo.contentCatId) : contentDo.contentCatId != null)
            return false;
        if (contentName != null ? !contentName.equals(contentDo.contentName) : contentDo.contentName != null)
            return false;
        if (sortOrder != null ? !sortOrder.equals(contentDo.sortOrder) : contentDo.sortOrder != null) return false;
        if (contentDesc != null ? !contentDesc.equals(contentDo.contentDesc) : contentDo.contentDesc != null)
            return false;
        if (url != null ? !url.equals(contentDo.url) : contentDo.url != null) return false;
        if (image != null ? !image.equals(contentDo.image) : contentDo.image != null) return false;
        if (createTime != null ? !createTime.equals(contentDo.createTime) : contentDo.createTime != null) return false;
        if (createOper != null ? !createOper.equals(contentDo.createOper) : contentDo.createOper != null) return false;
        if (lastupdateTime != null ? !lastupdateTime.equals(contentDo.lastupdateTime) : contentDo.lastupdateTime != null)
            return false;
        if (lastupdateOper != null ? !lastupdateOper.equals(contentDo.lastupdateOper) : contentDo.lastupdateOper != null)
            return false;
        if (updateCounts != null ? !updateCounts.equals(contentDo.updateCounts) : contentDo.updateCounts != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = contentId != null ? contentId.hashCode() : 0;
        result = 31 * result + (contentCatId != null ? contentCatId.hashCode() : 0);
        result = 31 * result + (contentName != null ? contentName.hashCode() : 0);
        result = 31 * result + (sortOrder != null ? sortOrder.hashCode() : 0);
        result = 31 * result + (contentDesc != null ? contentDesc.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (createOper != null ? createOper.hashCode() : 0);
        result = 31 * result + (lastupdateTime != null ? lastupdateTime.hashCode() : 0);
        result = 31 * result + (lastupdateOper != null ? lastupdateOper.hashCode() : 0);
        result = 31 * result + (updateCounts != null ? updateCounts.hashCode() : 0);
        return result;
    }
}
