package com.ymjtt.test.mapper.test;

import com.ymjtt.test.mapper.StudentDoMapper;
import com.ymjtt.test.xdo.StudentDo;
import com.ymjtt.test.vo.StudentVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext_db.xml"})
public class ParameterTypeTest {

    @Autowired
    private StudentDoMapper studentDoMapper;

    //简单参数类型
    @Test
    public void test01() {
        StudentDo stu01 = studentDoMapper.getStudentDoById(2);
        StudentDo stu02 = studentDoMapper.getStudentDoByName("小明");
        StudentDo stu03 = studentDoMapper.getStudentDoByName(null);
        System.out.println("id为2:  " + stu01);
        System.out.println("name为小明:  " + stu02);
        System.out.println("name为null:  " + stu03);
    }
    //pojo参数类型
    @Test
    public void test02() {
        StudentDo studentDo = new StudentDo();
        studentDo.setId(3);
        StudentDo stu = studentDoMapper.getStudentDoByPojo(studentDo);
        System.out.println(stu);
    }
    //包装pojo参数类型
    @Test
    public void test03() {
        StudentDo Do = new StudentDo();
        Do.setId(3);
        StudentVo Vo = new StudentVo();
        Vo.setStudentDo(Do);
        StudentDo studentDo = studentDoMapper.getStudentDoByPojoVo(Vo);
        System.out.println(studentDo);
    }
    //String参数切割类型
    @Test
    public void test04() {
        String ids = "1,2,4";
        List<StudentDo> list = studentDoMapper.getStudentDoByIds(ids);
        list.stream().forEach(System.out::println);
    }
    //数组参数类型
    @Test
    public void test05() {
        int[] stus = {1, 3};
        List<StudentDo> list = studentDoMapper.listStudentDoByArray(stus);
        list.stream().forEach(System.out::println);
    }
    //List参数类型
    @Test
    public void test06() {
        Integer id01 = 1;
        Integer id02 = 2;

        List<Integer> list = new ArrayList<>();
        list.add(id01);
        list.add(id02);

        studentDoMapper.ListStudentDoByList(list).stream().forEach(System.out::println);
    }
    //Map参数类型
    @Test
    public void test07() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", 3);
        map.put("name", "小");
        StudentDo studentDo = studentDoMapper.ListStudentDoByMap(map);
        System.out.println(studentDo);
    }
}
