package com.ywx.ymjtt.mapper;

import com.ywx.ymjtt.dto.StudentAlia;
import com.ywx.ymjtt.xdo.StudentDo;
import com.ywx.ymjtt.vo.StudentVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface StudentDoMapper {

    List<StudentDo> query(StudentDo studentDo);

    void add(StudentDo studentDo);

    void delete(StudentDo studentDo);

    void update(StudentDo studentDo);

    //ParameterType Test
    //简单参数
    StudentDo queryById(Integer id);
    StudentDo queryByName(String name);
    //pojo参数
    StudentDo queryByPojo(StudentDo studentDo);
    //包装pojo参数
    StudentDo queryByPojoVo(StudentVo studentVo);
    //String参数切割类型
    List<StudentDo> queryByIds(String ids);
    //数组参数类型
    List<StudentDo> queryByArray(int[] ids);
    //List参数类型
    List<StudentDo> queryByList(List<Integer> ids);
    //Map参数
    StudentDo queryByMap(Map map);


    //ResultType Test
    //简单类型
    int count();
    //POJO类型          如上
    //List集合类型      如上
    //ResultMap类型
    StudentAlia queryVoById(int id);


}
