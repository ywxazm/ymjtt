package com.ywx.ymjtt.service.impl.test;

import com.ywx.ymjtt.dao.test.StudentDoMapper;
import com.ywx.ymjtt.pojo.test.StudentDo;
import com.ywx.ymjtt.service.test.StudentDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
public class StudentDoServiceImpl implements StudentDoService {

    @Autowired
    private StudentDoMapper studentDoMapper;

    @Override
    public List<StudentDo> queryAll() {
        return studentDoMapper.query(null);
    }

    @Override
    public List<StudentDo> query(StudentDo studentDo) {
        return studentDoMapper.query(studentDo);
    }

    @Override
    public void add(StudentDo studentDo) {
        studentDoMapper.add(studentDo);
    }

    @Override
    public void delete(StudentDo studentDo) {
        studentDoMapper.delete(studentDo);
    }

    @Override
    public void update(StudentDo studentDo) {
        studentDoMapper.update(studentDo);
    }


    //以下 for transaction test
    @Override
    public List<StudentDo> onlyRead01() {
        StudentDo stu = new StudentDo();
        stu.setName("张三");
        stu.setAge(99);
        studentDoMapper.add(stu);
        return null;
    }

    @Override
    public List<StudentDo> timeOut01() {
        StudentDo stu = new StudentDo();
        stu.setName("张三");
        stu.setAge(99);
        studentDoMapper.add(stu);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        studentDoMapper.add(stu);           //关闭后，不会报出异常
        return null;
    }

    //读未提交
    @Override
    public void gengXinDate02() {
        List<StudentDo> list = studentDoMapper.query(null);
        StudentDo stu = list.get(list.size() - 1);
        stu.setAge(new Random().nextInt(100));
        studentDoMapper.update(stu);

        try {
            Thread.sleep(600000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void gengXinDate0201() {
        List<StudentDo> list = studentDoMapper.query(null);
        StudentDo stu = list.get(list.size() - 1);
        stu.setAge(new Random().nextInt(100));
        studentDoMapper.update(stu);

        try {
            Thread.sleep(600000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<StudentDo> chaXunDate02() {
        return studentDoMapper.query(null);
    }
    @Override
    public void tianJiaDate02() {

    }
    @Override
    public void shanChuDate02() {

    }

    //读已提交
    @Override
    public void gengXinDate03() {
        List<StudentDo> list = studentDoMapper.query(null);
        StudentDo stu = list.get(list.size() - 1);
        stu.setAge(new Random().nextInt(100));
        studentDoMapper.update(stu);

        try {
            Thread.sleep(600000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void gengXinDate0301() {
        List<StudentDo> list = studentDoMapper.query(null);
        StudentDo stu = list.get(list.size() - 1);
        stu.setAge(new Random().nextInt(100));
        studentDoMapper.update(stu);

        try {
            Thread.sleep(600000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<StudentDo> chaXunDate03() {
        return studentDoMapper.query(null);
    }

    //可重复读
    @Override
    public void gengXinDate04() {
        List<StudentDo> list = studentDoMapper.query(null);
        StudentDo stu = list.get(list.size() - 1);
        stu.setAge(new Random().nextInt(100));
        studentDoMapper.update(stu);

        try {
            Thread.sleep(600000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void gengXinDate0401() {
        List<StudentDo> list = studentDoMapper.query(null);
        StudentDo stu = list.get(list.size() - 1);
        stu.setAge(new Random().nextInt(100));
        studentDoMapper.update(stu);

        try {
            Thread.sleep(600000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<StudentDo> chaXunDate04() {
        return studentDoMapper.query(null);
    }

    //串行化
    @Override
    public void gengXinDate05() {
        List<StudentDo> list = studentDoMapper.query(null);
        StudentDo stu = list.get(list.size() - 1);
        stu.setAge(new Random().nextInt(100));
        studentDoMapper.update(stu);

        try {
            Thread.sleep(600000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void gengXinDate0501() {
        List<StudentDo> list = studentDoMapper.query(null);
        StudentDo stu = list.get(list.size() - 1);
        stu.setAge(new Random().nextInt(100));
        studentDoMapper.update(stu);

        try {
            Thread.sleep(600000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<StudentDo> chaXunDate05() {
        return studentDoMapper.query(null);
    }


}
