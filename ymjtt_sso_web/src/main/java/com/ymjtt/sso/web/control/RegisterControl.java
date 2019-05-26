package com.ymjtt.sso.web.control;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ymjtt.common.util.cookie.CookieUtils;
import com.ymjtt.common.vo.ResultInfoVO;
import com.ymjtt.sso.exception.UserException;
import com.ymjtt.sso.service.RegisterService;
import com.ymjtt.sso.vo.LoginVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @auther ywx
 * @date 2019/4/30 17:37
 **/
@RequestMapping("/register")
@Controller
public class RegisterControl {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterControl.class);

    @Autowired
    private RegisterService registerService;

    @Value("${COOKIE_USER_TOKEN}")
    private String COOKIE_USER_TOKEN;

    @ResponseBody
    @RequestMapping("/isExistUser/{userNick}")
    public ResultInfoVO isExistUser(@PathVariable("userNick") String userNick) {
        return registerService.isExistUser(userNick)
                ? ResultInfoVO.buildSuccessInfo("userNick Ok", true)
                : ResultInfoVO.buildSuccessInfo("userNick exist", false);
    }

    @ResponseBody
    @RequestMapping(value = "/register")
    public ResultInfoVO register(@RequestBody @Valid LoginVO loginVO, HttpServletRequest request
            , HttpServletResponse response) throws JsonProcessingException {

        registerCheck(loginVO);

        ResultInfoVO resultInfoVO = registerService.register(loginVO);
        if (resultInfoVO.getCode() == 20000) {
            CookieUtils.setCookie(request, response, COOKIE_USER_TOKEN, resultInfoVO.getData().toString(), 3600 * 24 * 7);
            return resultInfoVO;
        }

        return ResultInfoVO.buildFailInfo("register fail");
    }

    /**
     * 注册用户信息校验
     * @author  ywx
     * @date    2019/5/16 16:47
     * @param   [loginVO]
     * @return  void
     */
    private void registerCheck(LoginVO loginVO) {
        //用户是否存在
        boolean isExist = registerService.isExistUser(loginVO.getUserNick());
        if (!isExist) {
            LOGGER.info("Thread ID is {}, register info {}", Thread.currentThread().getId(), "user exist");
            throw new UserException("user exist");
        }

        //密码是否有效
        if (StringUtils.isEmpty(loginVO.getPwd()) || loginVO.getPwd().length() < 8) {
            LOGGER.info("Thread ID is {}, register info {}", Thread.currentThread().getId(), "pwd length < 8");
            throw new UserException("pwd length < 8");
        }
    }


}
