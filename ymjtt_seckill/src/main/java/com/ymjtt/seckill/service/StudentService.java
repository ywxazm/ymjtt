package com.ymjtt.seckill.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ymjtt.seckill.dao.StudentMapper;
import com.ymjtt.seckill.domain.StudentDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @auther ywx
 * @date 2019/4/25 16:59
 **/
@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    public StudentDo getById(int id) {
        PageHelper.startPage(2, 1);
        return studentMapper.getById(id);
    }

    @Transactional
    public Boolean save(StudentDo stu) {
        studentMapper.save(stu);
        System.out.println(stu.getId().toString());

        stu.setAge(101);
        studentMapper.save(stu);
        return true;
    }

    public PageInfo<StudentDo> list() {
        PageHelper.startPage(1, 2);
        return new PageInfo<>(studentMapper.list());
    }
}
