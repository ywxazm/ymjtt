package com.ymjtt.common.exceptionHandler;

import com.ymjtt.common.exception.AbstractSpecialException;
import com.ymjtt.common.result.CodeResult;
import com.ymjtt.common.util.json.JSONConvertUtil;
import com.ymjtt.common.util.objPackage.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * 全局异常处理器类
 * @auther ywx
 * @date 2018/11/21 16:37
 **/
public class GlobalExceptionHandler implements HandlerExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private String errorPage = "error/exception";

    @Nullable
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                         @Nullable Object handler, Exception ex) {
        logger.error("process fail , url: {}, handler: {} exception, message: {}", request.getRequestURI(),
                handler, ex.getMessage());

        if (isAjax(request)) {
            ajaxResolve(request, response, ex, CodeResult.COMMON_FAIL, errorPage);
            return null;
        }

        return resolve(request, response, ex, CodeResult.COMMON_FAIL, errorPage);
    }

    private ModelAndView resolve(HttpServletRequest request, HttpServletResponse response, Exception ex,
                                CodeResult codeResult, String errorPage) {
        logger.info("ThreadId: {} resolve(), error info = {}", Thread.currentThread().getId(), ex.getMessage());

        ModelAndView modelAndView = new ModelAndView();
        if (isSpecialExceptionHandle(ex)) {
            AbstractSpecialException bex = (AbstractSpecialException) ex;
            return bex.resolve(request, response, ex, codeResult, errorPage);
        }else {
            return new ModelAndView(errorPage, BeanUtil.bean2Map(codeResult));
        }
    }

    private void ajaxResolve(HttpServletRequest request, HttpServletResponse response, Exception ex,
                            CodeResult codeResult, String errorPage) {
        logger.info("ThreadId: {} ajaxResolve(), error info = {}", Thread.currentThread().getId(), ex.getMessage());

        if (isSpecialExceptionHandle(ex)) {
            IBaseExceptionHandler bex = (IBaseExceptionHandler) ex;
            bex.ajaxResolve(request, response, ex, codeResult, errorPage);
        }else {
            write(response, codeResult);
        }
    }

    /**
     * 错误信息写页面
     * @author  ywx
     * @date    2019/5/7 10:07
     * @param   [response, obj]
     * @return  void
     */
    private void write(HttpServletResponse response, Object obj) {
        try {
            Writer writer = response.getWriter();
            writer.write(JSONConvertUtil.obj2Json(obj));
            writer.close();
        } catch (IOException e) {
            logger.error("ThreadID is {} write ajax error, error info is: {}", Thread.currentThread().getId(), e.getMessage());
        }
    }

    /**
     * 是否为ajax请求
     * @author  ywx
     * @date    2019/5/7 10:08
     * @param   [request]
     * @return  boolean
     */
    private boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
    }

    /**
     * 是否为自定义异常
     * @author  ywx
     * @date    2019/5/7 10:09
     * @param   [ex]
     * @return  boolean
     */
    private boolean isSpecialExceptionHandle(Exception ex) {
        return ex instanceof AbstractSpecialException;
    }

}
