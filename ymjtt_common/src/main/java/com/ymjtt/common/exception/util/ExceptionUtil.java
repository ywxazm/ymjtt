package com.ymjtt.common.exception.util;

import com.ymjtt.common.exception.AbstractSpecialException;
import com.ymjtt.common.util.json.JSONConvertUtil;
import com.ymjtt.common.vo.ResultInfoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auther ywx
 * @date 2019/5/7 15:15
 **/
public class ExceptionUtil {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionUtil.class);

    public static final String ERROR_PAGE = "errorPage/500";

    /**
     * 错误信息写页面
     * @author  ywx
     * @date    2019/5/7 10:07
     * @param   [response, obj]
     * @return  void
     */
    public static void write(HttpServletResponse response, String exceptionMsg) {
        try {
            ServletOutputStream servletOutputStream = response.getOutputStream();
            servletOutputStream.write(JSONConvertUtil.obj2Json(ResultInfoVO.buildFailInfo(exceptionMsg)).getBytes());
            servletOutputStream.close();
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
    public static boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"));
    }

    /**
     * 是否为自定义异常
     * @author  ywx
     * @date    2019/5/7 10:09
     * @param   [ex]
     * @return  boolean
     */
    public static boolean isSpecialExceptionHandle(Exception ex) {
        return ex instanceof AbstractSpecialException;
    }

}
