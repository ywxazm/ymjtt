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
    /** 是否为父种类 */
    private Boolean parentType;

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

    public Boolean getParentType() {
        return parentType;
    }

    public void setParentType(Boolean parentType) {
        this.parentType = parentType;
    }
}
