package com.ymjtt.manager.user.xdo;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @auther ywx
 * @date 2019/1/14 17:57
 **/
@Entity
@Table(name = "user", schema = "ymjtt")
public class UserDo implements Serializable{
    protected Long userId;
    protected String userName;
    protected transient String pwd;
    protected String image;
    protected String email;
    protected Long phone;
    protected Integer status;
    protected Integer userType;
    protected Timestamp createTime;
    protected String createOper;
    protected Timestamp lastupdateTime;
    protected String lastupdateOper;
    protected Integer updateCounts;

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
    @Column(name = "pwd")
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
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
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "phone")
    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "user_type")
    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
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

        UserDo userDo = (UserDo) o;

        if (userId != null ? !userId.equals(userDo.userId) : userDo.userId != null) return false;
        if (userName != null ? !userName.equals(userDo.userName) : userDo.userName != null) return false;
        if (pwd != null ? !pwd.equals(userDo.pwd) : userDo.pwd != null) return false;
        if (image != null ? !image.equals(userDo.image) : userDo.image != null) return false;
        if (email != null ? !email.equals(userDo.email) : userDo.email != null) return false;
        if (phone != null ? !phone.equals(userDo.phone) : userDo.phone != null) return false;
        if (status != null ? !status.equals(userDo.status) : userDo.status != null) return false;
        if (userType != null ? !userType.equals(userDo.userType) : userDo.userType != null) return false;
        if (createTime != null ? !createTime.equals(userDo.createTime) : userDo.createTime != null) return false;
        if (createOper != null ? !createOper.equals(userDo.createOper) : userDo.createOper != null) return false;
        if (lastupdateTime != null ? !lastupdateTime.equals(userDo.lastupdateTime) : userDo.lastupdateTime != null)
            return false;
        if (lastupdateOper != null ? !lastupdateOper.equals(userDo.lastupdateOper) : userDo.lastupdateOper != null)
            return false;
        if (updateCounts != null ? !updateCounts.equals(userDo.updateCounts) : userDo.updateCounts != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (pwd != null ? pwd.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (userType != null ? userType.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (createOper != null ? createOper.hashCode() : 0);
        result = 31 * result + (lastupdateTime != null ? lastupdateTime.hashCode() : 0);
        result = 31 * result + (lastupdateOper != null ? lastupdateOper.hashCode() : 0);
        result = 31 * result + (updateCounts != null ? updateCounts.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserDo{" + "userId=" + userId + ", userName='" + userName + '\'' + ", pwd='" + pwd + '\'' + ", image='" + image + '\'' + ", email='" + email + '\'' + ", phone=" + phone + ", status=" + status + ", userType=" + userType + ", createTime=" + createTime + ", createOper='" + createOper + '\'' + ", lastupdateTime=" + lastupdateTime + ", lastupdateOper='" + lastupdateOper + '\'' + ", updateCounts=" + updateCounts + '}';
    }


}
