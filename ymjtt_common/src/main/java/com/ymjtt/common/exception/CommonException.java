package com.ymjtt.common.exception;

import com.ymjtt.common.result.CodeResult;
import com.ymjtt.common.util.json.JSONConvertUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 全局异常处理类
 * @auther ywx
 * @date 2018/11/21 16:37
 **/
@Component
public class CommonException implements HandlerExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(CommonException.class);

    @Nullable
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                         @Nullable Object handler, Exception ex) {
        logger.error("process fail , url: {}, class: {} exception, message: {}", request.getRequestURI(),
                handler, ex.toString());

        return ifAjax(request) ? ajaxRequest(request, response, ex) : noAjaxRequest(request, response, ex);
    }

    /**
     * 判断是否ajax请求
     *
     * @param request 请求对象
     * @return true:ajax请求  false:非ajax请求
     */
    private boolean ifAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
    }

    /**
     * ajax请求异常处理
     *
     * @param message 错误信息
     * @return 模型视图对象
     */
    private ModelAndView ajaxRequest(HttpServletRequest request,
                                     HttpServletResponse response,
                                     Exception ex)  {
        if (isSpecialExceptionHandle(ex)) {
            BaseException bex = (BaseException) ex;
            bex.executeForAjax(request, response);
        }else {
            commonAjaxRequestHandler(request, response, ex);
        }
        return null;
    }

    /**
     * 公共Ajax请求处理方式
     * @author  ywx
     * @date    2018/12/16 13:52
     * @param   [request, response, ex]
     * @return  void
     */
    public void commonAjaxRequestHandler(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Exception ex) {
        logger.debug("common Ajax Request Handler : Error Info = {}", ex.toString());
        write(response, CodeResult.DEFAULT_FAIL);
    }

    /**
     * 非ajax请求异常处理
     *
     * @param message 错误信息
     * @param url     错误页url
     * @return 模型视图对象
     */
    private ModelAndView noAjaxRequest(HttpServletRequest request,
                                      HttpServletResponse response,
                                      Exception ex) {

        ModelAndView modelAndView = new ModelAndView();
        if (isSpecialExceptionHandle(ex)) {
            BaseException bex = (BaseException) ex;
            return bex.execute(request, response);
        }else {
            return commonNoAjaxRequestHandler(request, response, ex);
        }
    }

    /**
     * 公共非Ajax请求处理方式
     * @author  ywx
     * @date    2018/12/16 13:52
     * @param   [request, response, ex]
     * @return  void
     */
    public ModelAndView commonNoAjaxRequestHandler(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Exception ex){
        logger.debug("Common No Ajax Request Handler : Error Info = {}", ex.toString());
        Map<String, String> map = null;
        try {
            map = BeanUtils.describe(CodeResult.DEFAULT_FAIL);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            logger.error("CodeResult Bean 2 Map error, the info is = {}", e.getMessage());
        }
        return new ModelAndView("error/exception", map);
    }

    /**
     * 是否为自定义异常
     * @author  ywx
     * @date    2018/12/16 13:23
     * @param   [ex]
     * @return  boolean
     */
    private boolean isSpecialExceptionHandle(Exception ex) {
        return ex instanceof BaseException;
    }

    private static void write(HttpServletResponse response, Object obj) {
        try {
            Writer writer = response.getWriter();
            writer.write(JSONConvertUtil.obj2Json(obj));
            writer.close();
        } catch (IOException e) {
            logger.error("ThreadID is {} write ajax error, error info is: {}", Thread.currentThread().getId(), e.getMessage());
        }
    }
}
