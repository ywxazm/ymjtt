package com.ymjtt.test.service.impl.test;

import com.ymjtt.test.service.StudentDoService;
import com.ymjtt.test.xdo.StudentDo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:spring/applicationContext_*.xml"})
public class StudentDoServiceTest {

    @Autowired
    private StudentDoService studentDoService;

    @Test
    public void test01() {
        List<StudentDo> list = studentDoService.listStudentDo();
        list.stream().forEach(System.out::println);
    }
}
