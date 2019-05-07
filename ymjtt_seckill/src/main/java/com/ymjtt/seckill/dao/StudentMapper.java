package com.ymjtt.seckill.dao;

import com.github.pagehelper.PageInfo;
import com.ymjtt.seckill.domain.StudentDo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @auther ywx
 * @date 2019/4/25 16:52
 **/
@Mapper
public interface StudentMapper {

    @Select("select * from test_student where id = #{id}")
    StudentDo getById(@Param("id") int id);

    @Insert("insert into test_student values(null, #{name}, #{age})")
    Boolean save(StudentDo stu);

    @Select("select * from test_student")
    List<StudentDo> list();
}
