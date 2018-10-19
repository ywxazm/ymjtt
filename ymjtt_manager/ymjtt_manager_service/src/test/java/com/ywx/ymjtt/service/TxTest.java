package com.ywx.ymjtt.service;


import com.ywx.ymjtt.pojo.test.StudentDo;
import com.ywx.ymjtt.service.test.StudentDoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 事务开始: 第一个statement执行前，创建sqlSession后，开启事务，而不是在进入方法后
 * 事务结束：方法的最后一条语句执行完成后
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext_*.xml"})
public class TxTest {

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
     * 隔离级别
     * 1. 一个资源上可以同时存在多个共享锁，共享锁存在的时候不能加排他锁，可以加更新锁
     * 2. 一个资源只能有一个排他锁，存在排他锁的情况下，不能再加其他类型的锁
     * 3. 一个资源只能有一个更新锁，存在更新锁的情况下，只能加共享锁
     */
    //读未提交  对事务 update 的行加“共享锁”，事务结束释放锁，其它事务不可修改此行
        //对于更新的数据加“共享锁”，其它事务可查询此事务
    @Test
    public void test03001() {
        studentDoService.gengXinDate02();   //加“共享锁”
    }
    @Test
    public void test03002() {
        studentDoService.gengXinDate0201();
    }
    @Test
    public void test03003() {
        List<StudentDo> list = studentDoService.chaXunDate02();
        list.stream().forEach(System.out::println);
    }
    //读已提交  对事务 update 的行加“更新锁”，
    //          对事务 read   的行加“共享锁”，
        //一个事务加“更新锁”后，其它事务不可再添加“更新锁”
    @Test
    public void test03010() {
        studentDoService.gengXinDate03();   //加“更新锁”
    }
    @Test
    public void test03011() {
        studentDoService.gengXinDate0301();   //加“更新锁”
    }
    @Test
    public void test03012() {
        List<StudentDo> list = studentDoService.chaXunDate03();
        list.stream().forEach(System.out::println);
    }
    //可重复读  对事务 update 的行加“更新锁”，其它事务不可再持有“更新锁”，只能够等待此更新锁的释放，可能存在等待超时
    //          对事务 read   的行加“共享锁”，
        //一个事务加“更新锁”后，其它事务不可再添加“更新锁”
    @Test
    public void test03020() {
        studentDoService.gengXinDate04();   //加“更新锁”
    }
    @Test
    public void test03021() {
        studentDoService.gengXinDate0401();   //加“更新锁”，此更新锁处于等待状态，会有等待超时
    }
    @Test
    public void test03022() {
        List<StudentDo> list = studentDoService.chaXunDate04();
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
     */
    @Test
    public void test040() {

    }

    /**
     * 传播行为问题
     */
    @Test
    public void test050() {

    }
}
