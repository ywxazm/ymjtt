package com.ymjtt.test.mapper;

import com.ymjtt.test.dto.StudentAlia;
import com.ymjtt.test.xdo.StudentDo;
import com.ymjtt.test.vo.StudentVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface StudentDoMapper {

    List<StudentDo> getStudentDo(StudentDo studentDo);

    void saveStudentDo(StudentDo studentDo);

    void removeStudentDo(StudentDo studentDo);

    void updateStudentDo(StudentDo studentDo);

    //ParameterType Test
    //简单参数
    StudentDo getStudentDoById(Integer id);
    StudentDo getStudentDoByName(String name);
    //pojo参数
    StudentDo getStudentDoByPojo(StudentDo studentDo);
    //包装pojo参数
    StudentDo getStudentDoByPojoVo(StudentVo studentVo);
    //String参数切割类型
    List<StudentDo> getStudentDoByIds(String ids);
    //数组参数类型
    List<StudentDo> listStudentDoByArray(int[] ids);
    //List参数类型
    List<StudentDo> ListStudentDoByList(List<Integer> ids);
    //Map参数
    StudentDo ListStudentDoByMap(Map map);


    //ResultType Test
    //简单类型
    int countStudentDo();
    //POJO类型          如上
    //List集合类型      如上
    //ResultMap类型
    StudentAlia getStudentVoById(int id);


}
