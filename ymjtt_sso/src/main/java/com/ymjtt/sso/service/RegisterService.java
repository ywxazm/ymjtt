package com.ymjtt.sso.service;

import com.ymjtt.sso.dao.UserDao;
import com.ymjtt.sso.domain.UserDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther ywx
 * @date 2019/4/30 17:40
 **/
@Service
public class RegisterService {

    @Autowired
    private UserDao userDao;

    /**
     * 用户是否存在
     * @author  ywx
     * @date    2019/5/5 15:04
     * @param   [userDo]
     * @return  boolean
     *              true: 存在
     *              false: 不存在
     */
    public boolean isExist(UserDo userDo) {
        return userDao.getDO(userDo) != null;
    }

}
