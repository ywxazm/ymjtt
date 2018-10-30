package com.ywx.ymjtt.test.mapper.test;

import com.ywx.ymjtt.test.mapper.StudentDoMapper;
import com.ywx.ymjtt.test.xdo.StudentDo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext_db.xml",
                                 "classpath:applicationContext_db_test.xml"})    //classpath  :只包含本工程下的文件
public class StudentDoMapperTest {                                               //classpath* :包含本工程及父工程下的文件

    @Autowired
    private StudentDoMapper studentDoMapper;

    @Test
    public void test01() {
        List<StudentDo> list = studentDoMapper.getStudentDo(null);
        list.stream().forEach(System.out::println);
    }

    @Test
    public void test02() {
        StudentDo stu = new StudentDo();
        stu.setName("张三");
        stu.setAge(23);
        studentDoMapper.saveStudentDo(stu);
    }

    @Test
    public void test03() {
        List<StudentDo> list = studentDoMapper.getStudentDo(null);
        StudentDo studentDo = list.get(list.size() - 1);
        studentDoMapper.removeStudentDo(studentDo);
    }

    @Test
    public void test04() {
        List<StudentDo> list = studentDoMapper.getStudentDo(null);
        StudentDo studentDo = list.get(list.size() - 1);
        studentDo.setAge(99);
        studentDoMapper.updateStudentDo(studentDo);
    }
}
