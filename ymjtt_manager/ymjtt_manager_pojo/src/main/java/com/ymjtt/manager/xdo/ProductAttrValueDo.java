package com.ymjtt.manager.xdo;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @auther ywx
 * @date 2018/12/27 10:38
 **/
@Entity
@Table(name = "product_attr_value", schema = "ymjtt")
public class ProductAttrValueDo implements Serializable {
    private Long productAttrValueId;
    private String productAttrValueV;
    private Long productAttrId;
    private Timestamp createTime;
    private String createOper;
    private Timestamp lastupdateTime;
    private String lastupdateOper;
    private Integer updateCounts;

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
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (createOper != null ? createOper.hashCode() : 0);
        result = 31 * result + (lastupdateTime != null ? lastupdateTime.hashCode() : 0);
        result = 31 * result + (lastupdateOper != null ? lastupdateOper.hashCode() : 0);
        result = 31 * result + (updateCounts != null ? updateCounts.hashCode() : 0);
        return result;
    }
}
