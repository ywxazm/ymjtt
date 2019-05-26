package com.ymjtt.manager.interceptor;

import com.ymjtt.common.codeinfo.CodeCommonConstant;
import com.ymjtt.common.util.cookie.CookieUtils;
import com.ymjtt.sso.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户权限拦截器
 * @auther ywx
 * @date 2019/5/17 16:17
 **/
@Component
@PropertySource("classpath:properties/other.properties")
public class AuthorityInterceptor implements HandlerInterceptor {

    @Autowired
    private LoginService loginService;

    @Value("${COOKIE_USER_TOKEN}")
    private String COOKIE_USER_TOKEN;

    @Value("${REDIRECT_LOCATION_LOGIN}")
    private String REDIRECT_LOCATION_LOGIN;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
        boolean b = CodeCommonConstant.COMMON_SUCCESS.equals(loginService.isLogin(CookieUtils.getCookieValue(request, COOKIE_USER_TOKEN)).getCode());
        if (!b) {
            response.sendRedirect(REDIRECT_LOCATION_LOGIN);
        }
        return b;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
