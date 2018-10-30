package com.ymjtt.test.mapper.test;

import com.ymjtt.test.dto.StudentAlia;
import com.ymjtt.test.mapper.StudentDoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext_db.xml"})
public class ResultTypeTest {

    @Autowired
    private StudentDoMapper studentDoMapper;

    //ResultType Test
    //简单类型
    @Test
    public void test01 () {
        int count = studentDoMapper.countStudentDo();
        System.out.println(count);
    }
    //POJO类型          如上
    //List集合类型      如上
    //ResultMap类型   pojo中的属性与数据库列的名称不一致时,会使用
    @Test
    public void test02() {
        StudentAlia studentAlia = studentDoMapper.getStudentVoById(2);
        System.out.println(studentAlia);
    }
}
