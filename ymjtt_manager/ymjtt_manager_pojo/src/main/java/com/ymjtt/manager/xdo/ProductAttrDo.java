package com.ymjtt.manager.xdo;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @auther ywx
 * @date 2018/12/27 10:38
 **/
@Entity
@Table(name = "product_attr", schema = "ymjtt")
public class ProductAttrDo implements Serializable {
    private Long productAttrId;
    private String productAttrName;
    private Long productImgId;
    private Timestamp createTime;
    private String createOper;
    private Timestamp lastupdateTime;
    private String lastupdateOper;
    private Integer updateCounts;

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
    @Column(name = "product_img_id")
    public Long getProductImgId() {
        return productImgId;
    }

    public void setProductImgId(Long productImgId) {
        this.productImgId = productImgId;
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
        if (productAttrName != null ? !productAttrName.equals(that.productAttrName) : that.productAttrName != null)
            return false;
        if (productImgId != null ? !productImgId.equals(that.productImgId) : that.productImgId != null) return false;
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
        int result = productAttrId != null ? productAttrId.hashCode() : 0;
        result = 31 * result + (productAttrName != null ? productAttrName.hashCode() : 0);
        result = 31 * result + (productImgId != null ? productImgId.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (createOper != null ? createOper.hashCode() : 0);
        result = 31 * result + (lastupdateTime != null ? lastupdateTime.hashCode() : 0);
        result = 31 * result + (lastupdateOper != null ? lastupdateOper.hashCode() : 0);
        result = 31 * result + (updateCounts != null ? updateCounts.hashCode() : 0);
        return result;
    }
}
