package com.ywx.ymjtt.service.impl.test;

import com.ywx.ymjtt.dao.test.StudentDoMapper;
import com.ywx.ymjtt.pojo.test.StudentDo;
import com.ywx.ymjtt.service.test.StudentDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentDoServiceImpl implements StudentDoService {

    @Autowired
    private StudentDoMapper studentDoMapper;

    @Override
    public List<StudentDo> queryAll() {
        return studentDoMapper.query(null);
    }
}
