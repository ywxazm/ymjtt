package com.ymjtt.common.log.test;

import com.ymjtt.common.log.LogTest;
import com.ymjtt.common.util.fastdfs.FastDFSUtil;
import org.csource.common.MyException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.Map;

/**
 *
 * @author  ywx
 * @date    2018/12/18 16:59
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/*.xml"})
public class LogTestt {

    @Autowired
    private LogTest logTest;

    @Test
    public void test01() {
        System.out.println("-------start--------");
        logTest.log("12345");
    }

}
