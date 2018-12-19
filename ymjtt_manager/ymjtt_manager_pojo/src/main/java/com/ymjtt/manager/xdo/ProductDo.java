package com.ymjtt.manager.xdo;

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
    private Long productId;
    private String title;
    private String sellPoint;
    private Double price;
    private Double priceAgain;
    private String barcode;
    private String image;
    private Long cid;
    private Byte productStatus;
    private Timestamp createTime;
    private String createOper;
    private Timestamp lastupdateTime;
    private Integer updateCounts;
    private String lastupdateOper;

    @Id
    @Column(name = "product_id")
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
    @Column(name = "price")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPriceAgain() {
        return priceAgain;
    }

    public void setPriceAgain(Double priceAgain) {
        this.priceAgain = priceAgain;
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
        if (title != null ? !title.equals(productDo.title) : productDo.title != null) return false;
        if (sellPoint != null ? !sellPoint.equals(productDo.sellPoint) : productDo.sellPoint != null) return false;
        if (price != null ? !price.equals(productDo.price) : productDo.price != null) return false;
        if (priceAgain != null ? !priceAgain.equals(productDo.priceAgain) : productDo.priceAgain != null) return false;
        if (barcode != null ? !barcode.equals(productDo.barcode) : productDo.barcode != null) return false;
        if (image != null ? !image.equals(productDo.image) : productDo.image != null) return false;
        if (cid != null ? !cid.equals(productDo.cid) : productDo.cid != null) return false;
        if (productStatus != null ? !productStatus.equals(productDo.productStatus) : productDo.productStatus != null)
            return false;
        if (createTime != null ? !createTime.equals(productDo.createTime) : productDo.createTime != null) return false;
        if (createOper != null ? !createOper.equals(productDo.createOper) : productDo.createOper != null) return false;
        if (lastupdateTime != null ? !lastupdateTime.equals(productDo.lastupdateTime) : productDo.lastupdateTime != null)
            return false;
        if (updateCounts != null ? !updateCounts.equals(productDo.updateCounts) : productDo.updateCounts != null)
            return false;
        return lastupdateOper != null ? lastupdateOper.equals(productDo.lastupdateOper) : productDo.lastupdateOper == null;
    }

    @Override
    public int hashCode() {
        int result = productId != null ? productId.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (sellPoint != null ? sellPoint.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (priceAgain != null ? priceAgain.hashCode() : 0);
        result = 31 * result + (barcode != null ? barcode.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (cid != null ? cid.hashCode() : 0);
        result = 31 * result + (productStatus != null ? productStatus.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (createOper != null ? createOper.hashCode() : 0);
        result = 31 * result + (lastupdateTime != null ? lastupdateTime.hashCode() : 0);
        result = 31 * result + (updateCounts != null ? updateCounts.hashCode() : 0);
        result = 31 * result + (lastupdateOper != null ? lastupdateOper.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProductDo{" + "productId=" + productId + ", title='" + title + '\'' + ", sellPoint='" + sellPoint + '\'' + ", price=" + price + ", priceAgain=" + priceAgain + ", barcode='" + barcode + '\'' + ", image='" + image + '\'' + ", cid=" + cid + ", productStatus=" + productStatus + ", createTime=" + createTime + ", createOper='" + createOper + '\'' + ", lastupdateTime=" + lastupdateTime + ", updateCounts=" + updateCounts + ", lastupdateOper='" + lastupdateOper + '\'' + '}';
    }
}
