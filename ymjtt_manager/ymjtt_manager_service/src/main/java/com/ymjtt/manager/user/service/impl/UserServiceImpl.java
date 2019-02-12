package com.ymjtt.manager.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ymjtt.manager.user.mapper.UserMapper;
import com.ymjtt.manager.user.service.UserService;
import com.ymjtt.manager.user.xdo.UserDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther ywx
 * @date 2019/1/9 11:05
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<UserDo> listDO(UserDo userDo, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        List<UserDo> userDoList = userMapper.listDO(userDo);
        return new PageInfo<>(userDoList);
    }

    @Override
    public UserDo getDO(Long id) {
        return userMapper.getDO(id);
    }

    @Override
    public Boolean removeDO(Long id) {
        return userMapper.removeDO(id);
    }

    @Override
    public Boolean saveDO(UserDo userDo) {
        return userMapper.saveDO(userDo);
    }

    @Override
    public Boolean updateDO(UserDo userDo) {
        return userMapper.updateDO(userDo);
    }


    //Others
}
