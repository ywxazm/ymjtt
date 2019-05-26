package com.ymjtt.common.exceptionHandler;

import com.ymjtt.common.exception.AbstractSpecialException;
import com.ymjtt.common.exception.util.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理器类
 * @auther ywx
 * @date 2018/11/21 16:37
 **/
@Component
public class GlobalExceptionHandler implements HandlerExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Nullable
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                         @Nullable Object handler, Exception ex) {

        logger.error("Into GlobalExceptionHandler, Url Is {}, handler Is {}, message Is {}", request.getRequestURI(),
                handler, ex.getMessage());

        if (ExceptionUtil.isAjax(request)) {
            if (ExceptionUtil.isSpecialExceptionHandle(ex)) {
                AbstractSpecialException bex = (AbstractSpecialException) ex;
                bex.resolveException(response, bex);
                return null;
            }

            ExceptionUtil.write(response, ex.getMessage());
            return null;

        }else {
            ModelAndView modelAndView = new ModelAndView();
            if (ExceptionUtil.isSpecialExceptionHandle(ex)) {
                AbstractSpecialException bex = (AbstractSpecialException) ex;
                return bex.resolveException(modelAndView, bex);
            }

            modelAndView.setViewName(ExceptionUtil.ERROR_PAGE);
            modelAndView.addObject("msg", ex.getMessage());
            return modelAndView;
        }

    }

}
