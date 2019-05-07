package com.ymjtt.common.result;

import java.io.Serializable;

/**
 * 1.全流程操作成功, 才为成功, 否则为失败
 * 2.数据操作失败, 不带自定义消息
 * 3.可预估的操作失败, 带自定义消息
 * @auther ywx
 * @date 2018/12/18 8:41
 **/
public class ResultVO<T> implements Serializable {

    private Integer code;   //编码, 由CodeResult统一定义;
    private String msg;     //信息, 由CodeResult统一定义;
    private T data;         //错误时为Null, 成功时, 如有数据, 则放于此字段, 否则亦为Null

    /** save/del/update一般是此方法返回 */
    public static <T> ResultVO<T> buildResult(Boolean result, CodeResult successResult, CodeResult errorResult) {
        return result ? buildSuccessResult(successResult) : buildSuccessResult(errorResult);
    }

    /** 失败, 不带自定义消息 */
    public static <T> ResultVO<T> buildFailResult(CodeResult codeResult) {
        return new ResultVO<>(codeResult);
    }

    /** 失败, 带自定义消息 */
    public static <T> ResultVO<T> buildFailResult(CodeResult codeResult, String message) {
        return new ResultVO<>(codeResult, message);
    }

    /** 成功, 不返回数据*/
    public static <T> ResultVO<T> buildSuccessResult(CodeResult codeResult) {
        return new ResultVO<>(codeResult);
    }

    /** 成功, 返回数据*/
    public static <T> ResultVO<T> buildSuccessResult(CodeResult codeResult, T data) {
        return new ResultVO<>(codeResult, data);
    }

    /** 成功, 返回数据(使用默认CodeResult) */
    public static <T> ResultVO<T> buildSuccessResult(T data) {
        return new ResultVO<>(CodeResult.COMMON_SUCCESS, data);
    }

    private ResultVO(CodeResult codeResult) {
        this.code = codeResult.getCode();
        this.msg = codeResult.getMsg();
    }

    private ResultVO(CodeResult codeResult, T data) {
        this.code = codeResult.getCode();
        this.msg = codeResult.getMsg();
        this.data = data;
    }

    private ResultVO(CodeResult codeResult, String message) {       //message为自定义增加的消息
        this.code = codeResult.getCode();
        this.msg = codeResult.getMsg() + ", " + message;
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

    @Override
    public String toString() {
        return "ResultVO{" + "code=" + code + ", msg='" + msg + '\'' + ", data=" + data + '}';
    }
}
