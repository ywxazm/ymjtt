package com.ymjtt.sso.web.control;

import com.ymjtt.common.util.cookie.CookieUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 页面访问
 * @author  ywx
 * @date    2019/4/28 11:22
 */
@Controller
@RequestMapping("/sso")
public class PageControl {

    private static final Logger LOGGER = LoggerFactory.getLogger(PageControl.class);

    @Value("${COOKIE_USER_TOKEN}")
    private String COOKIE_USER_TOKEN;

    /**
     * 页面跳转
     * @author  ywx
     * @date    2019/4/29 15:21
     * @param   [funcPage] login/register/lockscreen/forgetPwd
     * @return  java.lang.String
     */
    @RequestMapping("/{funcPage}")
    public String toPage(@PathVariable("funcPage") String funcPage, HttpServletRequest request) {
        LOGGER.debug("Thread id is {}, cookie is {}", Thread.currentThread().getId()
                , CookieUtils.getCookieValue(request, COOKIE_USER_TOKEN));
        return funcPage;
    }

}
