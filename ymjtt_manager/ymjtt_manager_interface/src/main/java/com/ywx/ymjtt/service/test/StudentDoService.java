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
    void tianJiaDate02();
    void shanChuDate02();

        //读已提交
    void gengXinDate03();
    void gengXinDate0301();
    List<StudentDo> chaXunDate03();

        //可重复读
    void gengXinDate04();
    void gengXinDate0401();
    List<StudentDo> chaXunDate04();

        //串行化
    void gengXinDate05();
    void gengXinDate0501();
    List<StudentDo> chaXunDate05();
}
