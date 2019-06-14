package com.ymjtt.sso.web.control;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ymjtt.common.redis.StringOper;
import com.ymjtt.common.util.cookie.CookieUtils;
import com.ymjtt.common.vo.ResultInfoVO;
import com.ymjtt.sso.exception.UserException;
import com.ymjtt.sso.service.LoginService;
import com.ymjtt.sso.vo.LoginVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;

/**
 * @auther ywx
 * @date 2019/5/7 17:45
 **/
@Controller
@RequestMapping("/login")
public class LoginControl {

    private ThreadLocal<Object> threadLocal = new ThreadLocal<>();

    @Value("${COOKIE_USER_TOKEN}")
    private String COOKIE_USER_TOKEN;

    @Value("${USER_INFO_PREFIX}")
    private String USER_INFO_PREFIX;

    @Autowired
    private LoginService loginService;

    @Autowired
    private StringOper stringOper;

    @ResponseBody
    @RequestMapping(value = "/sign_up", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResultInfoVO login(@RequestBody @Valid LoginVO loginVO, HttpServletResponse response, HttpServletRequest request) throws UserException, JsonProcessingException {

        String token = CookieUtils.getCookieValue(request, COOKIE_USER_TOKEN);
        String userInfo = stringOper.get(USER_INFO_PREFIX + token);
        if (!StringUtils.isEmpty(userInfo)) {
            return ResultInfoVO.buildSuccessInfo("user login before");
        }

        ResultInfoVO resultInfoVO = loginService.login(loginVO);
        if (resultInfoVO.getCode() == 20000) {
            CookieUtils.setCookie(request, response, COOKIE_USER_TOKEN, resultInfoVO.getData().toString(), 3600 * 24 * 7);
            return resultInfoVO;
        }

        throw new UserException("user or password is not match exception");
    }

    @ResponseBody
    @RequestMapping(value = "/loginOut")
    public ResultInfoVO loginOut(HttpServletResponse response, HttpServletRequest request) {

        String token = CookieUtils.getCookieValue(request, COOKIE_USER_TOKEN);
        ResultInfoVO resultInfoVO = loginService.loginOut(token);

        if (resultInfoVO.getCode() == 20000) {
            CookieUtils.deleteCookie(request, response, COOKIE_USER_TOKEN);
            return resultInfoVO;
        }

        throw new UserException("loginOut fail");
    }
}
