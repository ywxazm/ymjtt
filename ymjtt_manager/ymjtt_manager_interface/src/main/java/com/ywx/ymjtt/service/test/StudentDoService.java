package com.ywx.ymjtt.service.test;

import com.ywx.ymjtt.pojo.test.StudentDo;

import java.util.List;

public interface StudentDoService {

    List<StudentDo> queryAll();

    List<StudentDo> query(StudentDo studentDo);

    void add(StudentDo studentDo);

    void delete(StudentDo studentDo);

    void update(StudentDo studentDo);

}
