package com.ymjtt.base.staticproxy;

/**
 * @auther ywx
 * @date 2019/6/3 15:36
 **/
public class UserService implements IUserService {
    @Override
    public void save() {
        System.out.println("保存数据");
    }
}
