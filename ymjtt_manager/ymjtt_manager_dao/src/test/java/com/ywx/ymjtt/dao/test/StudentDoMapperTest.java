package com.ywx.ymjtt.dao.test;

import com.ywx.ymjtt.pojo.test.StudentDo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:applicationContext_*.xml"})    //classpath  :只包含本工程下的文件
public class StudentDoMapperTest {                                          //classpath* :包含本工程及父工程下的文件

    @Autowired
    private StudentDoMapper studentDoMapper;

    @Test
    public void test01() {
        List<StudentDo> list = studentDoMapper.query(null);
        list.stream().forEach(System.out::println);
    }
}
