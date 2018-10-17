package com.ywx.ymjtt.dao.test;

import com.ywx.ymjtt.pojo.test.StudentDo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDoMapper {

    List<StudentDo> query(StudentDo studentDo);

    void add(StudentDo studentDo);

    void delete(StudentDo studentDo);

    void update(StudentDo studentDo);

}
