package com.ymjtt.manager.product.xdo;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @auther ywx
 * @date 2019/1/17 15:57
 **/
@Entity
@Table(name = "product_detail", schema = "ymjtt")
public class ProductDetailDo implements Serializable {
    protected Long productDetailId;
    protected Long productId;
    protected String image;
    protected Timestamp createTime;
    protected String createOper;
    protected Timestamp lastupdateTime;
    protected String lastupdateOper;
    protected Integer updateCounts;

    public ProductDetailDo() {
    }

    @Id
    @Column(name = "product_detail_id")
    public Long getProductDetailId() {
        return productDetailId;
    }

    public void setProductDetailId(Long productDetailId) {
        this.productDetailId = productDetailId;
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

        ProductDetailDo that = (ProductDetailDo) o;

        if (productDetailId != null ? !productDetailId.equals(that.productDetailId) : that.productDetailId != null)
            return false;
        if (productId != null ? !productId.equals(that.productId) : that.productId != null) return false;
        if (image != null ? !image.equals(that.image) : that.image != null) return false;
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
        int result = productDetailId != null ? productDetailId.hashCode() : 0;
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (createOper != null ? createOper.hashCode() : 0);
        result = 31 * result + (lastupdateTime != null ? lastupdateTime.hashCode() : 0);
        result = 31 * result + (lastupdateOper != null ? lastupdateOper.hashCode() : 0);
        result = 31 * result + (updateCounts != null ? updateCounts.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProductDetailDo{" + "productDetailId=" + productDetailId + ", productId=" + productId + ", image='" + image + '\'' + ", createTime=" + createTime + ", createOper='" + createOper + '\'' + ", lastupdateTime=" + lastupdateTime + ", lastupdateOper='" + lastupdateOper + '\'' + ", updateCounts=" + updateCounts + '}';
    }
}
