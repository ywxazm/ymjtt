package com.ymjtt.common.codeinfo;

import java.io.Serializable;

/**
 * @auther ywx
 * @date 2019/5/10 9:02
 **/
public class CodeInfo implements Serializable {

    protected Integer code;
    protected String msg;

    protected CodeInfo(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "CodeInfo{" + "code=" + code + ", msg='" + msg + '\'' + '}';
    }
}
