package com.ymjtt.test.service.impl;

import com.ymjtt.test.mapper.StudentDoMapper;
import com.ymjtt.test.service.StudentDoService;
import com.ymjtt.test.xdo.StudentDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;


//@Scope(value = "prototype")     //只有单例,才会在Spring创建时,同时创建实例
@Service
public class StudentDoServiceImpl implements StudentDoService {

    @Autowired
    private StudentDoMapper studentDoMapper;

    @Override
    public List<StudentDo> listStudentDo() {
        long start = System.currentTimeMillis();
        List<StudentDo> list = studentDoMapper.getStudentDo(null);
        System.out.println("the db query cast time is " + (System.currentTimeMillis() - start));
        return list;
    }

    @Override
    public List<StudentDo> getStudentDo(StudentDo studentDo) {
        return studentDoMapper.getStudentDo(studentDo);
    }

    @Override
    public void saveStudentDo(StudentDo studentDo) {
        studentDoMapper.saveStudentDo(studentDo);
    }

    @Override
    public void removeStudentDo(StudentDo studentDo) {
        studentDoMapper.removeStudentDo(studentDo);
    }

    @Override
    public void updateStudentDo(StudentDo studentDo) {
        studentDoMapper.updateStudentDo(studentDo);
    }


    //以下 for transaction test
    @Override
    public List<StudentDo> onlyRead01() {
        StudentDo stu = new StudentDo();
        stu.setName("张三");
        stu.setAge(99);
        studentDoMapper.saveStudentDo(stu);
        return null;
    }

    @Override
    public List<StudentDo> timeOut01() {
        StudentDo stu = new StudentDo();
        stu.setName("张三");
        stu.setAge(99);
        studentDoMapper.saveStudentDo(stu);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        studentDoMapper.saveStudentDo(stu);           //关闭后，不会报出异常
        return null;
    }

    //读未提交
    @Override
    public void gengXinDate02() {
        List<StudentDo> list = studentDoMapper.getStudentDo(null);
        StudentDo stu = list.get(list.size() - 1);
        stu.setAge(new Random().nextInt(100));
        studentDoMapper.updateStudentDo(stu);

        try {
            Thread.sleep(600000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void gengXinDate0201() {
        List<StudentDo> list = studentDoMapper.getStudentDo(null);
        StudentDo stu = list.get(list.size() - 1);
        stu.setAge(new Random().nextInt(100));
        studentDoMapper.updateStudentDo(stu);

        try {
            Thread.sleep(600000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<StudentDo> chaXunDate02() {
        return studentDoMapper.getStudentDo(null);
    }

    //串行化
    @Override
    public void gengXinDate05() {
        List<StudentDo> list = studentDoMapper.getStudentDo(null);
        StudentDo stu = list.get(list.size() - 1);
        stu.setAge(new Random().nextInt(100));
        studentDoMapper.updateStudentDo(stu);

        try {
            Thread.sleep(600000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void gengXinDate0501() {
        List<StudentDo> list = studentDoMapper.getStudentDo(null);
        StudentDo stu = list.get(list.size() - 1);
        stu.setAge(new Random().nextInt(100));
        studentDoMapper.updateStudentDo(stu);

        try {
            Thread.sleep(600000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<StudentDo> chaXunDate05() {
        return studentDoMapper.getStudentDo(null);
    }

    //传播行为
    @Override
    public void method01() {
        System.out.println("-------------method01.start--------------");
        StudentDo stu = new StudentDo();
        stu.setName("method01");
        stu.setAge(10);
        studentDoMapper.saveStudentDo(stu);
        System.out.println("-------------method01.end--------------");
    }

    @Override
    public void method02() {
        System.out.println("-------------method02.start--------------");
        StudentDo stu = new StudentDo();
        stu.setName("method02");
        stu.setAge(11);
        studentDoMapper.saveStudentDo(stu);
//        int i = 10 / 0;
        System.out.println("-------------method02.end--------------");
    }

    @Override
    public void method03() throws Exception {
        StudentDo stu = new StudentDo();
        stu.setName("method03");
        stu.setAge(11);
        studentDoMapper.saveStudentDo(stu);

        throw new Exception("aa");  //检查型异常, 提交
    }

    @Override
    public void method04() {
        StudentDo stu = new StudentDo();
        stu.setName("method04");
        stu.setAge(12);
        studentDoMapper.saveStudentDo(stu);

        throw new RuntimeException("aa"); //非检查型异常, 回滚
    }

    @Override
    public void method05() {
        StudentDo stu = new StudentDo();
        stu.setName("method05");
        stu.setAge(13);
        studentDoMapper.saveStudentDo(stu);

        throw new RuntimeException("aa");
    }

    @Override
    public void method06() throws Exception {
        StudentDo stu = new StudentDo();
        stu.setName("method06");
        stu.setAge(14);
        studentDoMapper.saveStudentDo(stu);

        throw new Exception("aa");
    }
}
