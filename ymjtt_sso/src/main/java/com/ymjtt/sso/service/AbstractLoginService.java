package com.ymjtt.sso.service;

import com.ymjtt.sso.domain.UserDo;
import com.ymjtt.sso.domain.UserVO;

/**
 * @auther ywx
 * @date 2019/5/6 16:45
 **/
public interface AbstractLoginService {

    UserDo login(UserVO userVO);

}
