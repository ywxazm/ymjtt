package com.ymjtt.sso.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ymjtt.common.vo.ResultInfoVO;
import com.ymjtt.sso.exception.UserException;
import com.ymjtt.sso.vo.LoginVO;

/**
 * @auther ywx
 * @date 2019/5/6 16:45
 **/
public interface LoginService {

    ResultInfoVO login(LoginVO loginVO) throws UserException, JsonProcessingException;

    ResultInfoVO loginOut(String token);

    ResultInfoVO isLogin(String token);
}
