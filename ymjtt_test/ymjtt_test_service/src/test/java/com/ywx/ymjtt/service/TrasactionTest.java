package com.ywx.ymjtt.service;


import com.ywx.ymjtt.xdo.StudentDo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 事务开始: 第一个statement执行前，创建sqlSession后，开启事务，而不是在进入方法后
 * 事务结束：方法的最后一条语句执行完成后
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext_*.xml"})
public class TrasactionTest {

    @Autowired
    private StudentDoService studentDoService;

    /**
     * 只读问题
     */
    @Test
    public void test010() {
        List<StudentDo> list = studentDoService.onlyRead01();       //事务配置为只读时，去修改数据，报错java.sql.SQLException
    }

    /**
     * 超时问题
     * 1.超时异常的报出是在整个方法执行完成之后；而不是超时后即时报出
     * 2.超时的时间指的是： 第一个statement创建 --》 最后一个statement结束
     * 如果先执行查询，再用Thread.sleep(..),然后结束方法，只要查询不超时，则不会报出超时异常
     * 如果先执行查询，再用Thread.sleep(..),然后再执行查询，然后结束方法，只要2次查询开始到完成时间足够，则会报出超时异常
     */
    @Test
    public void test020() {
        List<StudentDo> studentDos = studentDoService.timeOut01();
    }

    /**
     * 锁与锁之间的相互作用
     * 1. 一个资源上可以同时存在多个共享锁，共享锁存在的时候不能加排他锁，可以加更新锁
     * 2. 一个资源只能有一个排他锁，存在排他锁的情况下，不能再加其他类型的锁
     * 3. 一个资源只能有一个更新锁，存在更新锁的情况下，只能加共享锁
     *
     * 隔离级别的理解
     *      每一个事务,即每一亲连接,或者说每一条线程,都有自己的高速缓存(mysql缓存)
     *  1.  读未提交: 从所有线程的高速缓存中取数据
     *  2.  读已提交: 从磁盘中取数据
     *  3.  可重复读: 第一次取数据从磁盘,后面的取数据从自己的高速缓存
     *  4.  串行化:   磁盘只允许一条线程操作
     */
    //读未提交  对事务 update 的行加“共享锁”，事务结束释放锁，其它事务不可修改此行
        //对于更新的数据加“共享锁”，其它事务可查询此事务,但不可修改此数据
    @Test
    public void test03001() {
        studentDoService.gengXinDate02();   //加“共享锁”
    }
    @Test
    public void test03002() {
        studentDoService.gengXinDate0201();     //加"共享锁"后,其它事务不可再加"共享锁"
    }
    @Test
    public void test03003() {
        List<StudentDo> list = studentDoService.chaXunDate02();          //加"共享锁"后,其它事务可加"共享锁"
        list.stream().forEach(System.out::println);
    }
    //串行化    对事务 read, update 的表加锁，其它事务不可读写任何数据 其它事务不可读写任何数据
        //对于任何操作，都加“表锁”
    @Test
    public void test03030() {
        studentDoService.gengXinDate05();   //加“表锁”
    }
    @Test
    public void test03031() {
        studentDoService.gengXinDate0501();   //加“表锁”
    }
    @Test
    public void test03032() {
        List<StudentDo> list = studentDoService.chaXunDate05();
        list.stream().forEach(System.out::println);
    }


    /**
     * 回滚问题
     * 对于检查型异常(继承RunTimeException, Eroor及其子类),默认不回滚
     * 对于检查型异常(继承Exception),默认回滚
     */
    //Exception及其子类是不会回滚的(RunException除外)
    @Test
    public void test040() throws Exception {
        studentDoService.method03();
    }
    //RunException回滚
    @Test
    public void test041() {
        studentDoService.method04();
    }
    //RunException通过配置,实现不回滚
    @Test
    public void test042() {
        studentDoService.method05();
    }
    //Exception通过配置,实现回滚
    @Test
    public void test043() throws Exception {
        studentDoService.method06();
    }

    /**
     * 传播行为问题
     */
    //REQUIRED, 支持当前事务,如果没有事务,则新建事务
    @Test
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void test050() {
        studentDoService.method01();
        studentDoService.method02();
        int i = 10 / 0;
    }
}
