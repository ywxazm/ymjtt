package com.ymjtt.sso.service;

import com.ymjtt.sso.dao.UserDao;
import com.ymjtt.sso.dao.UserMapper;
import com.ymjtt.sso.domain.UserDo;
import com.ymjtt.sso.domain.UserVO;
import com.ymjtt.sso.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

/**
 * @auther ywx
 * @date 2019/4/30 17:40
 **/
@Service
public class LoginService implements AbstractLoginService {

    @Autowired
    private UserMapper userMapper;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean login(UserDo userDo) {
        UserDo dbUserDo = userDao.getDO(userDo);
        if (null == dbUserDo) {
            System.out.println("用户不存在");
            return false;
        }

        String dbPwd = dbUserDo.getPwd();
        String generatePwd = MD5Util.generatePwd(dbUserDo.getSalt(), userDo.getPwd());
        if (dbPwd.equals(generatePwd)) {
            return true;
        }
        return false;
    }



    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public UserDo login(UserVO userVO) {
        UserDo dbUserDo = userMapper.getDO(userVO);
        if (null == dbUserDo) {
            throw
        }

        return null;
    }
}
