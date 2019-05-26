package com.ymjtt.common.vo;

import com.ymjtt.common.codeinfo.CodeCommonConstant;
import com.ymjtt.common.codeinfo.CodeInfo;

import java.io.Serializable;

/**
 * @auther ywx
 * @date 2019/5/10 9:08
 **/
public class ResultInfoVO<T> implements Serializable {

    private Integer code;
    private String msg;
    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private ResultInfoVO() {
    }

    private ResultInfoVO(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private ResultInfoVO(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private ResultInfoVO(CodeInfo codeInfo) {
        this.code = codeInfo.getCode();
        this.msg = codeInfo.getMsg();
    }

    private ResultInfoVO(CodeInfo codeInfo, T data) {
        this.code = codeInfo.getCode();
        this.msg = codeInfo.getMsg();
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultInfoVO{" + "code=" + code + ", msg='" + msg + '\'' + ", data=" + data + '}';
    }

    /* ====================== 构建方法 ===================== */
    public static ResultInfoVO buildSuccessInfo() {
        return new ResultInfoVO(CodeCommonConstant.SUCCESS);
    }

    public static ResultInfoVO buildSuccessInfo(String msg) {
        return new ResultInfoVO(CodeCommonConstant.SUCCESS.getCode(), msg);
    }

    public static ResultInfoVO buildSuccessInfo(Object data) {
        return new ResultInfoVO(CodeCommonConstant.SUCCESS, data);
    }

    public static ResultInfoVO buildSuccessInfo(String msg, Object data) {
        return new ResultInfoVO(CodeCommonConstant.SUCCESS.getCode(), msg, data);
    }

    public static ResultInfoVO buildFailInfo() {
        return new ResultInfoVO(CodeCommonConstant.FAIL);
    }

    public static ResultInfoVO buildFailInfo(String msg) {
        return new ResultInfoVO(CodeCommonConstant.FAIL.getCode(), msg);
    }
}
