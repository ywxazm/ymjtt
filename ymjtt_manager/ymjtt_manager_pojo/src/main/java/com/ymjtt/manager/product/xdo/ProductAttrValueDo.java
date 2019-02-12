package com.ymjtt.manager.product.xdo;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @auther ywx
 * @date 2019/1/19 21:31
 **/
@Entity
@Table(name = "product_attr_value", schema = "ymjtt")
public class ProductAttrValueDo implements Serializable {
    protected Long productAttrValueId;
    protected String productAttrValueV;
    protected Long productAttrId;
    protected Double valueType;
    protected Double attrParam;
    protected Timestamp createTime;
    protected String createOper;
    protected Timestamp lastupdateTime;
    protected String lastupdateOper;
    protected Integer updateCounts;

    @Id
    @Column(name = "product_attr_value_id")
    public Long getProductAttrValueId() {
        return productAttrValueId;
    }

    public void setProductAttrValueId(Long productAttrValueId) {
        this.productAttrValueId = productAttrValueId;
    }

    @Basic
    @Column(name = "product_attr_value_v")
    public String getProductAttrValueV() {
        return productAttrValueV;
    }

    public void setProductAttrValueV(String productAttrValueV) {
        this.productAttrValueV = productAttrValueV;
    }

    @Basic
    @Column(name = "product_attr_id")
    public Long getProductAttrId() {
        return productAttrId;
    }

    public void setProductAttrId(Long productAttrId) {
        this.productAttrId = productAttrId;
    }

    @Basic
    @Column(name = "value_type")
    public Double getValueType() {
        return valueType;
    }

    public void setValueType(Double valueType) {
        this.valueType = valueType;
    }

    @Basic
    @Column(name = "attr_param")
    public Double getAttrParam() {
        return attrParam;
    }

    public void setAttrParam(Double attrParam) {
        this.attrParam = attrParam;
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

        ProductAttrValueDo that = (ProductAttrValueDo) o;

        if (productAttrValueId != null ? !productAttrValueId.equals(that.productAttrValueId) : that.productAttrValueId != null)
            return false;
        if (productAttrValueV != null ? !productAttrValueV.equals(that.productAttrValueV) : that.productAttrValueV != null)
            return false;
        if (productAttrId != null ? !productAttrId.equals(that.productAttrId) : that.productAttrId != null)
            return false;
        if (valueType != null ? !valueType.equals(that.valueType) : that.valueType != null) return false;
        if (attrParam != null ? !attrParam.equals(that.attrParam) : that.attrParam != null) return false;
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
        int result = productAttrValueId != null ? productAttrValueId.hashCode() : 0;
        result = 31 * result + (productAttrValueV != null ? productAttrValueV.hashCode() : 0);
        result = 31 * result + (productAttrId != null ? productAttrId.hashCode() : 0);
        result = 31 * result + (valueType != null ? valueType.hashCode() : 0);
        result = 31 * result + (attrParam != null ? attrParam.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (createOper != null ? createOper.hashCode() : 0);
        result = 31 * result + (lastupdateTime != null ? lastupdateTime.hashCode() : 0);
        result = 31 * result + (lastupdateOper != null ? lastupdateOper.hashCode() : 0);
        result = 31 * result + (updateCounts != null ? updateCounts.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProductAttrValueDo{" + "productAttrValueId=" + productAttrValueId + ", productAttrValueV='" + productAttrValueV + '\'' + ", productAttrId=" + productAttrId + ", valueType=" + valueType + ", attrParam=" + attrParam + ", createTime=" + createTime + ", createOper='" + createOper + '\'' + ", lastupdateTime=" + lastupdateTime + ", lastupdateOper='" + lastupdateOper + '\'' + ", updateCounts=" + updateCounts + '}';
    }
}
