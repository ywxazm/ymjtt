package com.ywx.ymjtt.service.test;

import com.ywx.ymjtt.pojo.test.StudentDo;

import java.util.List;

public interface StudentDoService {

    List<StudentDo> queryAll();

    List<StudentDo> query(StudentDo studentDo);

    void add(StudentDo studentDo);

    void delete(StudentDo studentDo);

    void update(StudentDo studentDo);

    //以下 for transaction test
    List<StudentDo> onlyRead01();
    List<StudentDo> timeOut01();

        //读未提交
    void gengXinDate02();
    void gengXinDate0201();
    List<StudentDo> chaXunDate02();

        //串行化
    void gengXinDate05();
    void gengXinDate0501();
    List<StudentDo> chaXunDate05();

        //传播行为
    void method01();
    void method02();

        //回滚问题
    void method03() throws Exception;
    void method04();
    void method05();
    void method06() throws Exception;


    //BeanPorpertyTest
    //void test01();

}
