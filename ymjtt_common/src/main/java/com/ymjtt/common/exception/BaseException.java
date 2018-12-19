package com.ymjtt.common.exception;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理所有在Handler中产生的异常
 * 所有自定义异常继承此类
 * @author  ywx
 * @date    2018/12/16 13:30
 * @param
 * @return
 */
public abstract class BaseException extends RuntimeException {

    /* 公用的Ajax异常code */
    public static final Integer COMMON_AJAX_CODE = 50000;

    /* 公用的非Ajax异常code */
    public static final Integer COMMON_NO_AJAX_CODE = 50010;

    protected Integer code;

    protected String msg;

    private BaseException() {
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

    /* 非ajax请求 */
    public abstract ModelAndView execute(HttpServletRequest request, HttpServletResponse response);

    /* ajax请求 */
    public abstract void executeForAjax(HttpServletRequest request, HttpServletResponse response);
}
