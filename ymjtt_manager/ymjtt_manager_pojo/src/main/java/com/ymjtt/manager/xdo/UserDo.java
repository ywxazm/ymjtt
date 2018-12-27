package com.ymjtt.manager.xdo;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @auther ywx
 * @date 2018/12/27 10:38
 **/
@Entity
@Table(name = "user", schema = "ymjtt")
public class UserDo implements Serializable{
    private Long userId;
    private String userName;
    private String email;
    private Integer phone;
    private Integer userType;
    private Timestamp createTime;
    private Timestamp lastupdateTime;
    private Integer updateCounts;

    @Id
    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "phone")
    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
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
    @Column(name = "user_type")
    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDo userDo = (UserDo) o;

        if (userId != null ? !userId.equals(userDo.userId) : userDo.userId != null) return false;
        if (userName != null ? !userName.equals(userDo.userName) : userDo.userName != null) return false;
        if (email != null ? !email.equals(userDo.email) : userDo.email != null) return false;
        if (phone != null ? !phone.equals(userDo.phone) : userDo.phone != null) return false;
        if (createTime != null ? !createTime.equals(userDo.createTime) : userDo.createTime != null) return false;
        if (lastupdateTime != null ? !lastupdateTime.equals(userDo.lastupdateTime) : userDo.lastupdateTime != null)
            return false;
        if (updateCounts != null ? !updateCounts.equals(userDo.updateCounts) : userDo.updateCounts != null)
            return false;
        if (userType != null ? !userType.equals(userDo.userType) : userDo.userType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (lastupdateTime != null ? lastupdateTime.hashCode() : 0);
        result = 31 * result + (updateCounts != null ? updateCounts.hashCode() : 0);
        result = 31 * result + (userType != null ? userType.hashCode() : 0);
        return result;
    }
}
