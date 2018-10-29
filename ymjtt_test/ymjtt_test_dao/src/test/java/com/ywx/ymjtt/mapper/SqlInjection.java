package com.ywx.ymjtt.mapper;

import com.ywx.ymjtt.xdo.UserDo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext_db.xml",
                                 "classpath:applicationContext_db_test.xml"})
public class SqlInjection {

    @Autowired
    private UserDoMapper userDoMapper;

    //Sql注入
    //成功注入, 可查询到所有
    //==>  Preparing: SELECT u.uid, u.uname FROM user u WHERE u.uname = 'XXX' or 1 = 1
    //==> Parameters:
    @Test
    public void test01 () {
        String uname = "'XXX' or 1 = 1";
        List<UserDo> list = userDoMapper.Injection(uname);
        list.stream().forEach(System.out::println);
    }

    //无法注入,无法查到结果
    //==>  Preparing: SELECT u.uid, u.uname FROM user u WHERE u.uname = ?               占位符
    //==> Parameters: 'XXX' or 1 = 1(String)                                可理解为: SELECT u.uid, u.uname FROM user u WHERE u.uname = ('XXX' or 1 = 1)
    @Test
    public void test02 () {
        String uname = "'XXX' or 1 = 1";
        List<UserDo> list = userDoMapper.NoInjection(uname);
        list.stream().forEach(System.out::println);
    }
}
