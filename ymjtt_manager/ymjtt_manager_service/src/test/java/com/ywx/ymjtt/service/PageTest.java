package com.ywx.ymjtt.service;

import com.github.pagehelper.PageHelper;
import com.ywx.ymjtt.pojo.test.StudentDo;
import com.ywx.ymjtt.service.test.StudentDoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:applicationContext_*.xml"})
public class PageTest {

    @Autowired
    private StudentDoService studentDoService;

    //分页的实现
    //ThreadLocal绑定了分页的信息
    @Test
    public void test01() {
        PageHelper.startPage(1,2);
        List<StudentDo> list = studentDoService.query(null);
        list.stream().forEach(System.out::println);
    }

    //因为分页信息与线程绑定,一般不会导致线程安全问题,但如下情况可能发生线程安全问题
    @Test
    public void test02() {
        PageHelper.startPage(1,2);
        if (new String(System.currentTimeMillis() + "").length() % 2 == 0) {
            List<StudentDo> list = studentDoService.query(null);
            list.stream().forEach(System.out::println);
        }

        List<StudentDo> list = studentDoService.query(null);
        list.stream().forEach(System.out::println);                             //上一个分布信息,带到了下一个查询中,查询的结果永远都是2条
    }
}
