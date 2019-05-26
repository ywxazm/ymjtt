package com.ymjtt.sso.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ymjtt.common.redis.StringOper;
import com.ymjtt.common.util.json.JSONConvertUtil;
import com.ymjtt.common.vo.ResultInfoVO;
import com.ymjtt.sso.dao.UserMapper;
import com.ymjtt.sso.exception.UserException;
import com.ymjtt.sso.service.LoginService;
import com.ymjtt.sso.util.MD5Util;
import com.ymjtt.sso.vo.LoginVO;
import com.ymjtt.sso.xdo.UserDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @auther ywx
 * @date 2019/4/30 17:40
 **/
@Service
public class LoginServiceImpl implements LoginService {

    @Value("${USER_INFO_PREFIX}")
    private String USER_INFO_PREFIX;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StringOper stringOper;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public ResultInfoVO login(LoginVO loginVO) throws UserException, JsonProcessingException {
        UserDo dbUserDo = userMapper.getDO(loginVO.getUserNick());
        if (null == dbUserDo) {
            throw new UserException("user not found exception");
        }

        String dbPwd = dbUserDo.getPwd();
        String generatePwd = MD5Util.generatePwd(dbUserDo.getSalt(), loginVO.getPwd());
        boolean loginResult = dbPwd.equals(generatePwd) && userMapper.loginIncr(dbUserDo.getUserId().toString());
        if (loginResult) {
            String token = UUID.randomUUID().toString().replace("-", "");
            dbUserDo.setPwd(null);
            dbUserDo.setSalt(null);
            stringOper.set(USER_INFO_PREFIX + token, JSONConvertUtil.obj2Json(dbUserDo));
            stringOper.expire(USER_INFO_PREFIX + token, 60000);
            return ResultInfoVO.buildSuccessInfo("login success", token);
        }
        return ResultInfoVO.buildFailInfo("login fail");
    }

    @Override
    public ResultInfoVO loginOut(String token) {
        stringOper.del(USER_INFO_PREFIX + token);
        return ResultInfoVO.buildSuccessInfo("loginOut success");
    }

    @Override
    public ResultInfoVO isLogin(String token) {
        String u = stringOper.get(USER_INFO_PREFIX + token);
        stringOper.expire(USER_INFO_PREFIX + token, 60000);
        return u == null ? ResultInfoVO.buildFailInfo() : ResultInfoVO.buildSuccessInfo();
    }
}
