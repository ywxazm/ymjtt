package com.ymjtt.common.exception;

import com.ymjtt.common.result.CodeResult;
import com.ymjtt.common.util.json.JSONConvertUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * 处理所有在Handler中产生的异常
 * 所有自定义异常继承此类
 * @author  ywx
 * @date    2018/12/16 13:30
 * @param
 * @return
 */
public abstract class BaseException extends RuntimeException {

    private static final Logger logger = LoggerFactory.getLogger(BaseException.class);

    /* 非ajax请求 */
    public abstract ModelAndView execute(HttpServletRequest request, HttpServletResponse response);

    /* ajax请求 */
    public abstract void executeForAjax(HttpServletRequest request, HttpServletResponse response);

    /**
     * CodeResult Bean 转 Map
     * @param codeResult
     * @return
     */
    protected static Map<String, String> bean2Map(CodeResult codeResult) {
        try {
            return BeanUtils.describe(codeResult);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            logger.error("CodeResult Bean 2 Map error, the info is = {}", e.getMessage());
        }
        return new HashMap<>();
    }

    protected static void write(HttpServletResponse response, Object obj) {
        try {
            Writer writer = response.getWriter();
            writer.write(JSONConvertUtil.obj2Json(obj));
            writer.close();
        } catch (IOException e) {
            logger.error("ThreadID is {} write ajax error, error info is: {}", Thread.currentThread().getId(), e.getMessage());
        }
    }
}
