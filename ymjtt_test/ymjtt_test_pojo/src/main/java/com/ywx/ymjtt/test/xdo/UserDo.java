package com.ywx.ymjtt.test.xdo;

import java.util.List;

public class UserDo {

    private Integer uid;
    private String uname;
    private List<OrdersDo> ordersDos;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public List<OrdersDo> getOrdersDos() {
        return ordersDos;
    }

    public void setOrdersDos(List<OrdersDo> ordersDos) {
        this.ordersDos = ordersDos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDo userDo = (UserDo) o;

        if (uid != null ? !uid.equals(userDo.uid) : userDo.uid != null) return false;
        if (uname != null ? !uname.equals(userDo.uname) : userDo.uname != null) return false;
        return ordersDos != null ? ordersDos.equals(userDo.ordersDos) : userDo.ordersDos == null;
    }

    @Override
    public int hashCode() {
        int result = uid != null ? uid.hashCode() : 0;
        result = 31 * result + (uname != null ? uname.hashCode() : 0);
        result = 31 * result + (ordersDos != null ? ordersDos.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserDo{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", ordersDos=" + ordersDos +
                '}';
    }
}
