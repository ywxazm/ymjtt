package com.ymjtt.test.service.impl.test;

import com.ymjtt.test.service.StudentDoService;
import com.ymjtt.test.xdo.StudentDo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:applicationContext_*.xml"})
public class MybatisCacheTest {

    @Autowired
    private StudentDoService studentDoService;

    //默认开启一级缓存
    //sqlSession绑定一个map,Map维护缓存
    @Test
    @Transactional      //在没有事务的时候,会去创建2个sqlSession对象,不能够作用一级缓存
    public void test01() {
        List<StudentDo> list01 = studentDoService.listStudentDo(); //发sql
        list01.stream().forEach(System.out::println);
        list01.get(list01.size() - 1).setAge(0);            //这里更改了但未提交,下面使用的是缓存的数据,与数据库是不符的
        System.out.println("--------区分2次查询分隔线---------");
        List<StudentDo> list02 = studentDoService.listStudentDo();  //不发sql
        list02.stream().forEach(System.out::println);
    }

    //二级缓存
    //二级缓存是sqlSessionFactory级别的,即使创建了不同的sqlSession,也不影响
    @Test
    public void test02() {
        List<StudentDo> list01 = studentDoService.listStudentDo(); //发sql
        list01.stream().forEach(System.out::println);
        list01.get(list01.size() - 1).setAge(0);
        System.out.println("--------区分2次查询分隔线---------");
        List<StudentDo> list02 = studentDoService.listStudentDo();  //不发sql
        list02.stream().forEach(System.out::println);
    }
}
