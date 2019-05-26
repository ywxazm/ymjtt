package com.ymjtt.sso.vo;


import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.SQLException;

/**
 * @auther ywx
 * @date 2019/5/6 15:27
 **/
public class LoginVO implements Serializable{

    @NotNull
    private String userNick;
    private String pwd;
    private String code;

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "LoginVO{" + "userNick='" + userNick + '\'' + ", pwd='" + pwd + '\'' + ", code='" + code + '\'' + '}';
    }
}
