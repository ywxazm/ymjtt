package com.ymjtt.sso.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ymjtt.common.redis.StringOper;
import com.ymjtt.common.util.json.JSONConvertUtil;
import com.ymjtt.common.vo.ResultInfoVO;
import com.ymjtt.sso.dao.UserMapper;
import com.ymjtt.sso.service.CommonService;
import com.ymjtt.sso.service.RegisterService;
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
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CommonService commonService;

    @Autowired
    private StringOper stringOper;

    @Value("${USER_INFO_PREFIX}")
    private String USER_INFO_PREFIX;

    @Override
    public boolean isExistUser(String userNick) {
        return null == userMapper.getDO(userNick.trim());
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public ResultInfoVO register(LoginVO loginVO) throws JsonProcessingException {
        UserDo userDo = new UserDo();
        String userNick = loginVO.getUserNick().trim();
        int aliasType = commonService.aliasType(userNick);
        switch (aliasType) {
            case 0 : userDo.setPhone(Long.parseLong(userNick)); break;
            case 1 : userDo.setEmail(userNick); break;
            case 2 : userDo.setUserName(userNick); break;
        }

        String salt = UUID.randomUUID().toString().replace("-", "");
        userDo.setSalt(salt);
        userDo.setPwd(MD5Util.generatePwd(salt, loginVO.getPwd()));

        if (userMapper.save(userDo)) {
            UserDo u = userMapper.getDO(userNick);
            String token = UUID.randomUUID().toString().replace("-", "");
            u.setPwd(null);
            u.setSalt(null);
            stringOper.set(USER_INFO_PREFIX + token, JSONConvertUtil.obj2Json(u));
            stringOper.expire(USER_INFO_PREFIX + token, 60000);
            return ResultInfoVO.buildSuccessInfo("register success", token);
        }
        return ResultInfoVO.buildFailInfo("register fail");
    }

}
