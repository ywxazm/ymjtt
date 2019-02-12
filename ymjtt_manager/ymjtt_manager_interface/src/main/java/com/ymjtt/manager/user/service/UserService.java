package com.ymjtt.manager.user.service;

import com.github.pagehelper.PageInfo;
import com.ymjtt.manager.user.xdo.UserDo;

/**
 * @auther ywx
 * @date 2019/1/9 11:03
 **/
public interface UserService {

    //Base
    PageInfo<UserDo> listDO(UserDo userDo, Integer page, Integer rows);

    UserDo getDO(Long id);

    Boolean removeDO(Long id);

    Boolean saveDO(UserDo userDo);

    Boolean updateDO(UserDo userDo);

    //Others

}
