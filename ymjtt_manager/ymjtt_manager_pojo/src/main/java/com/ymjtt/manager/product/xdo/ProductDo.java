package com.ymjtt.manager.product.xdo;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @auther ywx
 * @date 2018/11/27 11:03
 **/
@Entity
@Table(name = "product", schema = "ymjtt")
public class ProductDo implements Serializable{
    protected Long productId;
    protected String productName;
    protected String sellPoint;
    protected Double basePrice;
    protected Double basePriceAgain;
    protected String barcode;
    protected String image;
    protected Long cid;
    protected Byte productStatus;
    protected Integer ifCreateProductAttrRelation;
    protected Timestamp createTime;
    protected String createOper;
    protected Timestamp lastupdateTime;
    protected String lastupdateOper;
    protected Integer updateCounts;


    @Id
    @Column(name = "product_id")
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "product_name")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Basic
    @Column(name = "base_price")
    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public Double getBasePriceAgain() {
        return basePriceAgain;
    }

    public void setBasePriceAgain(Double basePriceAgain) {
        this.basePriceAgain = basePriceAgain;
    }

    @Basic
    @Column(name = "sell_point")
    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
    }

    @Basic
    @Column(name = "barcode")
    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
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
    @Column(name = "cid")
    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    @Basic
    @Column(name = "product_status")
    public Byte getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Byte productStatus) {
        this.productStatus = productStatus;
    }

    @Basic
    @Column(name = "if_create_product_attr_relation")
    public Integer getIfCreateProductAttrRelation() {
        return ifCreateProductAttrRelation;
    }

    public void setIfCreateProductAttrRelation(Integer ifCreateProductAttrRelation) {
        this.ifCreateProductAttrRelation = ifCreateProductAttrRelation;
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
    @Column(name = "update_counts")
    public Integer getUpdateCounts() {
        return updateCounts;
    }

    public void setUpdateCounts(Integer updateCounts) {
        this.updateCounts = updateCounts;
    }

    @Basic
    @Column(name = "lastupdateOper")
    public String getLastupdateOper() {
        return lastupdateOper;
    }

    public void setLastupdateOper(String lastupdateOper) {
        this.lastupdateOper = lastupdateOper;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductDo productDo = (ProductDo) o;

        if (productId != null ? !productId.equals(productDo.productId) : productDo.productId != null) return false;
        if (productName != null ? !productName.equals(productDo.productName) : productDo.productName != null)
            return false;
        if (sellPoint != null ? !sellPoint.equals(productDo.sellPoint) : productDo.sellPoint != null) return false;
        if (basePrice != null ? !basePrice.equals(productDo.basePrice) : productDo.basePrice != null) return false;
        if (basePriceAgain != null ? !basePriceAgain.equals(productDo.basePriceAgain) : productDo.basePriceAgain != null)
            return false;
        if (barcode != null ? !barcode.equals(productDo.barcode) : productDo.barcode != null) return false;
        if (image != null ? !image.equals(productDo.image) : productDo.image != null) return false;
        if (cid != null ? !cid.equals(productDo.cid) : productDo.cid != null) return false;
        if (productStatus != null ? !productStatus.equals(productDo.productStatus) : productDo.productStatus != null)
            return false;
        if (ifCreateProductAttrRelation != null ? !ifCreateProductAttrRelation.equals(productDo.ifCreateProductAttrRelation) : productDo.ifCreateProductAttrRelation != null)
            return false;
        if (createTime != null ? !createTime.equals(productDo.createTime) : productDo.createTime != null) return false;
        if (createOper != null ? !createOper.equals(productDo.createOper) : productDo.createOper != null) return false;
        if (lastupdateTime != null ? !lastupdateTime.equals(productDo.lastupdateTime) : productDo.lastupdateTime != null)
            return false;
        if (lastupdateOper != null ? !lastupdateOper.equals(productDo.lastupdateOper) : productDo.lastupdateOper != null)
            return false;
        if (updateCounts != null ? !updateCounts.equals(productDo.updateCounts) : productDo.updateCounts != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = productId != null ? productId.hashCode() : 0;
        result = 31 * result + (productName != null ? productName.hashCode() : 0);
        result = 31 * result + (sellPoint != null ? sellPoint.hashCode() : 0);
        result = 31 * result + (basePrice != null ? basePrice.hashCode() : 0);
        result = 31 * result + (basePriceAgain != null ? basePriceAgain.hashCode() : 0);
        result = 31 * result + (barcode != null ? barcode.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (cid != null ? cid.hashCode() : 0);
        result = 31 * result + (productStatus != null ? productStatus.hashCode() : 0);
        result = 31 * result + (ifCreateProductAttrRelation != null ? ifCreateProductAttrRelation.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (createOper != null ? createOper.hashCode() : 0);
        result = 31 * result + (lastupdateTime != null ? lastupdateTime.hashCode() : 0);
        result = 31 * result + (lastupdateOper != null ? lastupdateOper.hashCode() : 0);
        result = 31 * result + (updateCounts != null ? updateCounts.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProductDo{" + "productId=" + productId + ", productName='" + productName + '\'' + ", sellPoint='" + sellPoint + '\'' + ", basePrice=" + basePrice + ", basePriceAgain=" + basePriceAgain + ", barcode='" + barcode + '\'' + ", image='" + image + '\'' + ", cid=" + cid + ", productStatus=" + productStatus + ", ifCreateProductAttrRelation=" + ifCreateProductAttrRelation + ", createTime=" + createTime + ", createOper='" + createOper + '\'' + ", lastupdateTime=" + lastupdateTime + ", lastupdateOper='" + lastupdateOper + '\'' + ", updateCounts=" + updateCounts + '}';
    }
}
