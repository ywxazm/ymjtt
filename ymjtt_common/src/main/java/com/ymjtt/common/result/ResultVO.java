package com.ymjtt.common.result;

import java.io.Serializable;

/**
 *
 * @auther ywx
 * @date 2018/12/18 8:41
 **/
public class ResultVO<T> implements Serializable {

    private Integer code;
    private String msg;
    private T data;

    public static <T> ResultVO<T> buildSuccessResult(T data) {
        return new ResultVO<>(data);
    }

    public static <T> ResultVO<T> buildErrorResult(CodeResult codeResult) {
        return new ResultVO<>(codeResult);
    }

    public static <T> ResultVO<T> buildErrorResult(CodeResult codeResult, String msg) {
        return new ResultVO<>(codeResult, msg);
    }

    private ResultVO(T data) {
        this.code = 200;
        this.msg = "Success";
        this.data = data;
    }

    private ResultVO(CodeResult codeResult) {
        this.code = codeResult.getCode();
        this.msg = codeResult.getMsg();
    }

    private ResultVO(CodeResult codeResult, String msg) {
        this.code = codeResult.getCode();
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
