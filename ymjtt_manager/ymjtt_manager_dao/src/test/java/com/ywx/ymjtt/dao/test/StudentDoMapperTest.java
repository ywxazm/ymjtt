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

    @Test
    public void test02() {
        StudentDo stu = new StudentDo();
        stu.setName("张三");
        stu.setAge(23);
        studentDoMapper.add(stu);
    }

    @Test
    public void test03() {
        List<StudentDo> list = studentDoMapper.query(null);
        StudentDo studentDo = list.get(list.size() - 1);
        studentDoMapper.delete(studentDo);
    }

    @Test
    public void test04() {
        List<StudentDo> list = studentDoMapper.query(null);
        StudentDo studentDo = list.get(list.size() - 1);
        studentDo.setAge(99);
        studentDoMapper.update(studentDo);
    }
}
