package com.ymjtt.common.vo;

import java.io.Serializable;

/**
 *  商品种类
 * @author  ywx
 * @date    2018/11/20 11:21
 */
public class ProductCatNodeVO implements Serializable {

    /** 种类ID */
    private Long id;
    /** 种类名称 */
    private String name;
    /** 种类状态 */
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductCatNodeVO that = (ProductCatNodeVO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return status != null ? status.equals(that.status) : that.status == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ItemCatNodeVO{" + "id=" + id + ", name='" + name + '\'' + ", status='" + status + '\'' + '}';
    }
}
