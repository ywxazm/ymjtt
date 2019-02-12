package com.ymjtt.manager.product.xdo;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @auther ywx
 * @date 2019/1/21 9:59
 **/
@Entity
@Table(name = "product_attr_relation", schema = "ymjtt")
public class ProductAttrRelationDo implements Serializable {
    private Long productAttrRelationId;
    private Long productId;
    private Long productAttrId;
    private Long productAttrValueId;
    private Integer buildType;
    private Timestamp createTime;
    private String createOper;

    @Id
    @Column(name = "product_attr_relation_id")
    public Long getProductAttrRelationId() {
        return productAttrRelationId;
    }

    public void setProductAttrRelationId(Long productAttrRelationId) {
        this.productAttrRelationId = productAttrRelationId;
    }

    @Id
    @Column(name = "product_attr_relation_id")
    public Long getProductAttrRelation() {
        return productAttrRelationId;
    }

    public void setProductAttrRelation(Long productAttrRelationId) {
        this.productAttrRelationId = productAttrRelationId;
    }

    @Basic
    @Column(name = "product_id")
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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
    @Column(name = "product_attr_value_id")
    public Long getProductAttrValueId() {
        return productAttrValueId;
    }

    public void setProductAttrValueId(Long productAttrValueId) {
        this.productAttrValueId = productAttrValueId;
    }

    @Basic
    @Column(name = "build_type")
    public Integer getBuildType() {
        return buildType;
    }

    public void setBuildType(Integer buildType) {
        this.buildType = buildType;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductAttrRelationDo that = (ProductAttrRelationDo) o;

        if (productAttrRelationId != null ? !productAttrRelationId.equals(that.productAttrRelationId) : that.productAttrRelationId != null)
            return false;
        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;
        if (productAttrId != null ? !productAttrId.equals(that.productAttrId) : that.productAttrId != null)
            return false;
        if (productAttrValueId != null ? !productAttrValueId.equals(that.productAttrValueId) : that.productAttrValueId != null)
            return false;
        if (buildType != null ? !buildType.equals(that.buildType) : that.buildType != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (createOper != null ? !createOper.equals(that.createOper) : that.createOper != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = productAttrRelationId != null ? productAttrRelationId.hashCode() : 0;
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        result = 31 * result + (productAttrId != null ? productAttrId.hashCode() : 0);
        result = 31 * result + (productAttrValueId != null ? productAttrValueId.hashCode() : 0);
        result = 31 * result + (buildType != null ? buildType.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (createOper != null ? createOper.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProductAttrRelationDo{" + "productAttrRelationId=" + productAttrRelationId + ", productId=" + productId + ", productAttrId=" + productAttrId + ", productAttrValueId=" + productAttrValueId + ", buildType=" + buildType + ", createTime=" + createTime + ", createOper='" + createOper + '\'' + '}';
    }
}
