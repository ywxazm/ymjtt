package com.ymjtt.manager.product.xdo;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @auther ywx
 * @date 2019/1/20 13:44
 **/
@Entity
@Table(name = "product_attr", schema = "ymjtt")
public class ProductAttrDo implements Serializable{

    protected static final Integer ATTR_TYPE_PRODUCT = 1;
    protected static final Integer ATTR_TYPE_PRODUCTCAT = 2;

    protected Long productAttrId;
    protected String productAttrName;
    /* 商品ID Or 商品类别ID */
    protected Long belongId;
    /* 1 商品  2 商品类别 */
    protected Integer attrType;
    protected Timestamp createTime;
    protected String createOper;
    protected Timestamp lastupdateTime;
    protected String lastupdateOper;
    protected Integer updateCounts;
    protected Integer sortOrder;

    @Id
    @Column(name = "product_attr_id")
    public Long getProductAttrId() {
        return productAttrId;
    }

    public void setProductAttrId(Long productAttrId) {
        this.productAttrId = productAttrId;
    }

    @Basic
    @Column(name = "product_attr_name")
    public String getProductAttrName() {
        return productAttrName;
    }

    public void setProductAttrName(String productAttrName) {
        this.productAttrName = productAttrName;
    }

    @Basic
    @Column(name = "belong_id")
    public Long getBelongId() {
        return belongId;
    }

    public void setBelongId(Long belongId) {
        this.belongId = belongId;
    }

    @Basic
    @Column(name = "attr_type")
    public Integer getAttrType() {
        return attrType;
    }

    public void setAttrType(Integer attrType) {
        this.attrType = attrType;
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

        ProductAttrDo that = (ProductAttrDo) o;

        if (productAttrId != null ? !productAttrId.equals(that.productAttrId) : that.productAttrId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = productAttrId != null ? productAttrId.hashCode() : 0;
        result = 31 * result + (productAttrName != null ? productAttrName.hashCode() : 0);
        result = 31 * result + (belongId != null ? belongId.hashCode() : 0);
        result = 31 * result + (attrType != null ? attrType.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (createOper != null ? createOper.hashCode() : 0);
        result = 31 * result + (lastupdateTime != null ? lastupdateTime.hashCode() : 0);
        result = 31 * result + (lastupdateOper != null ? lastupdateOper.hashCode() : 0);
        result = 31 * result + (updateCounts != null ? updateCounts.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProductAttrDo{" + "productAttrId=" + productAttrId + ", productAttrName='" + productAttrName + '\'' + ", belongId=" + belongId + ", attrType=" + attrType + ", createTime=" + createTime + ", createOper='" + createOper + '\'' + ", lastupdateTime=" + lastupdateTime + ", lastupdateOper='" + lastupdateOper + '\'' + ", updateCounts=" + updateCounts + ", sortOrder=" + sortOrder + '}';
    }

}
