package com.ywx.ymjtt.test.service;

import com.ywx.ymjtt.test.xdo.StudentDo;

import java.util.List;

public interface StudentDoService {

    List<StudentDo> listStudentDo();

    List<StudentDo> getStudentDo(StudentDo studentDo);

    void saveStudentDo(StudentDo studentDo);

    void removeStudentDo(StudentDo studentDo);

    void updateStudentDo(StudentDo studentDo);

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
