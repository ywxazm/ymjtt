package com.ymjtt.sso.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面访问
 * @author  ywx
 * @date    2019/4/28 11:22
 */
@Controller
@RequestMapping("/sso")
public class PageControl {

    /**
     * 页面跳转
     * @author  ywx
     * @date    2019/4/29 15:21
     * @param   [funcPage] login/register/lockscreen/forgetPwd
     * @return  java.lang.String
     */
    @RequestMapping("/{funcPage}")
    public String toPage(@PathVariable("funcPage") String funcPage) {
        return funcPage;
    }

}
