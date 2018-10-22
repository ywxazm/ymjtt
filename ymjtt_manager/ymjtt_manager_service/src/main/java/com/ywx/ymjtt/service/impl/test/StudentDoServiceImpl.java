package com.ywx.ymjtt.service.impl.test;

import com.ywx.ymjtt.dao.test.StudentDoMapper;
import com.ywx.ymjtt.pojo.test.StudentDo;
import com.ywx.ymjtt.service.test.StudentDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Random;

@Service
//@Scope(value = "prototype")     //只有单例,才会在Spring创建时,同时创建实例
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

    //传播行为
    @Override
    public void method01() {
        System.out.println("-------------method01.start--------------");
        StudentDo stu = new StudentDo();
        stu.setName("method01");
        stu.setAge(10);
        studentDoMapper.add(stu);
        System.out.println("-------------method01.end--------------");
    }

    @Override
    public void method02() {
        System.out.println("-------------method02.start--------------");
        StudentDo stu = new StudentDo();
        stu.setName("method02");
        stu.setAge(11);
        studentDoMapper.add(stu);
//        int i = 10 / 0;
        System.out.println("-------------method02.end--------------");
    }

    @Override
    public void method03() throws Exception {
        StudentDo stu = new StudentDo();
        stu.setName("method03");
        stu.setAge(11);
        studentDoMapper.add(stu);

        throw new Exception("aa");
    }

    @Override
    public void method04() {
        StudentDo stu = new StudentDo();
        stu.setName("method04");
        stu.setAge(12);
        studentDoMapper.add(stu);

        throw new RuntimeException("aa");
    }

    @Override
    public void method05() {
        StudentDo stu = new StudentDo();
        stu.setName("method05");
        stu.setAge(13);
        studentDoMapper.add(stu);

        throw new RuntimeException("aa");
    }

    @Override
    public void method06() throws Exception {
        StudentDo stu = new StudentDo();
        stu.setName("method06");
        stu.setAge(14);
        studentDoMapper.add(stu);

        throw new Exception("aa");
    }



    //BeanPorpertyTest
    @PostConstruct      //随Spring的启动而启动, 只出现在单例时
    @Override
    public void test01() {
        System.out.println("----------@PostConstruct----------");
    }

    //死锁
    @Override
    public void update2(StudentDo studentDo) throws InterruptedException {
        studentDoMapper.update(studentDo);
        Thread.sleep(4000);
    }


}
