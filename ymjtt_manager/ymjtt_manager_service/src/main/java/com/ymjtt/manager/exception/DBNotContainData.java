package com.ymjtt.manager.exception;

import com.ymjtt.common.exception.BaseException;
import com.ymjtt.common.result.CodeResult;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 数据库中不包含数据异常
 * @auther ywx
 * @date 2019/1/21 21:49
 **/
public class DBNotContainData extends BaseException {

    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("error/exception", bean2Map(CodeResult.DB_NOT_CONTAIN_DATA));
    }

    @Override
    public void executeForAjax(HttpServletRequest request, HttpServletResponse response) {
        write(response, bean2Map(CodeResult.DB_NOT_CONTAIN_DATA));
    }
}
