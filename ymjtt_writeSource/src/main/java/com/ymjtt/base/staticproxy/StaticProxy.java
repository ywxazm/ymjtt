package com.ymjtt.base.staticproxy;

/**
 * @auther ywx
 * @date 2019/6/3 15:37
 **/
public class StaticProxy implements IUserService {

    private IUserService userService;

    public StaticProxy(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void save() {                       //静态代理劣: 有多少需要代理的类, 则需要写多少代理对象类, 太复杂; 动态代理不需要生成代理对象
        System.out.println("开启事务");
        userService.save();
        System.out.println("关闭事务");
    }


    public static void main(String[] args) {
        IUserService userService = new UserService();
        IUserService staticProxy = new StaticProxy(userService);
        staticProxy.save();
    }
}
