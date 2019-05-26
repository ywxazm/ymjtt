package com.ymjtt.common.exception.instance;

import com.ymjtt.common.exception.AbstractSpecialException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

/**
 * @auther ywx
 * @date 2019/5/10 10:00
 **/
public class FastDFSIntException extends AbstractSpecialException {

    public FastDFSIntException(String msg) {
        super(msg);
    }

    @Override
    public void resolveException(HttpServletResponse response, Exception ex) {
        super.resolveException(response, ex);
    }

    @Override
    public ModelAndView resolveException(ModelAndView modelAndView, Exception ex) {
        return super.resolveException(modelAndView, ex);
    }
}
