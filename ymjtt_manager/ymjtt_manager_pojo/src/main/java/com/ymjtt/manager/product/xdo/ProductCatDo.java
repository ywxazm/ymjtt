package com.ymjtt.manager.product.xdo;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 商品类别XDO
 * @auther ywx
 * @date 2018/11/21 9:52
 **/
@Entity
@Table(name = "product_cat", schema = "ymjtt")
public class ProductCatDo implements Serializable{

    protected Long productCatId;
    protected String productCatName;
    protected Integer productCatStatus;     /** 商品类别状态, 0: 失效,  1: 有效 */
    protected Integer sortOrder;
    protected Long parentId;
    protected Timestamp createTime;
    private String createOper;
    protected Timestamp lastupdateTime;
    private String lastupdateOper;
    protected Integer updateCounts;

    @Id
    @Column(name = "product_cat_id")
    public Long getProductCatId() {
        return productCatId;
    }

    public void setProductCatId(Long productCatId) {
        this.productCatId = productCatId;
    }

    @Basic
    @Column(name = "product_cat_name")
    public String getProductCatName() {
        return productCatName;
    }

    public void setProductCatName(String productCatName) {
        this.productCatName = productCatName;
    }

    @Basic
    @Column(name = "product_cat_status")
    public Integer getProductCatStatus() {
        return productCatStatus;
    }

    public void setProductCatStatus(Integer productCatStatus) {
        this.productCatStatus = productCatStatus;
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
    @Column(name = "lastupdateTime")
    public Timestamp getLastupdateTime() {
        return lastupdateTime;
    }

    public void setLastupdateTime(Timestamp lastupdateTime) {
        this.lastupdateTime = lastupdateTime;
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

        ProductCatDo that = (ProductCatDo) o;

        if (productCatId != null ? !productCatId.equals(that.productCatId) : that.productCatId != null) return false;
        if (productCatName != null ? !productCatName.equals(that.productCatName) : that.productCatName != null)
            return false;
        if (productCatStatus != null ? !productCatStatus.equals(that.productCatStatus) : that.productCatStatus != null)
            return false;
        if (sortOrder != null ? !sortOrder.equals(that.sortOrder) : that.sortOrder != null) return false;
        if (parentId != null ? !parentId.equals(that.parentId) : that.parentId != null) return false;
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
        int result = productCatId != null ? productCatId.hashCode() : 0;
        result = 31 * result + (productCatName != null ? productCatName.hashCode() : 0);
        result = 31 * result + (productCatStatus != null ? productCatStatus.hashCode() : 0);
        result = 31 * result + (sortOrder != null ? sortOrder.hashCode() : 0);
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (createOper != null ? createOper.hashCode() : 0);
        result = 31 * result + (lastupdateTime != null ? lastupdateTime.hashCode() : 0);
        result = 31 * result + (lastupdateOper != null ? lastupdateOper.hashCode() : 0);
        result = 31 * result + (updateCounts != null ? updateCounts.hashCode() : 0);
        return result;
    }



    @Override
    public String toString() {
        return "ProductCatDo{" + "productCatId=" + productCatId + ", productCatName='" + productCatName + '\'' + ", productCatStatus=" + productCatStatus + ", sortOrder=" + sortOrder + ", parentId=" + parentId + ", createTime=" + createTime + ", createOper='" + createOper + '\'' + ", lastupdateTime=" + lastupdateTime + ", lastupdateOper='" + lastupdateOper + '\'' + ", updateCounts=" + updateCounts + '}';
    }
}
