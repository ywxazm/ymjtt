package com.ymjtt.common.exception;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

/**
 * @auther ywx
 * @date 2019/5/9 16:08
 **/
public interface SpecialException {

    void resolveException(HttpServletResponse response, Exception ex);

    ModelAndView resolveException(ModelAndView modelAndView, Exception ex);

}
