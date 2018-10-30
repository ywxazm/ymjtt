package com.ywx.ymjtt.test.xdo;

import java.util.Date;

public class OrdersDo {

    private Integer oid;
    private Date createDate;
    private UserDo userDo;

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public UserDo getUserDo() {
        return userDo;
    }

    public void setUserDo(UserDo userDo) {
        this.userDo = userDo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrdersDo ordersDo = (OrdersDo) o;

        if (oid != null ? !oid.equals(ordersDo.oid) : ordersDo.oid != null) return false;
        if (createDate != null ? !createDate.equals(ordersDo.createDate) : ordersDo.createDate != null) return false;
        return userDo != null ? userDo.equals(ordersDo.userDo) : ordersDo.userDo == null;
    }

    @Override
    public int hashCode() {
        int result = oid != null ? oid.hashCode() : 0;
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (userDo != null ? userDo.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OrdersDo{" +
                "oid=" + oid +
                ", createDate=" + createDate +
                ", userDo=" + userDo +
                '}';
    }
}
