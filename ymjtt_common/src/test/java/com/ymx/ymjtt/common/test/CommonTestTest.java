package com.ymx.ymjtt.common.test;

import com.ywx.ymjtt.common.test.CommonTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext_common.xml"})
public class CommonTestTest {

    @Autowired
    private CommonTest commonTest;

    @Test
    public void test01() {
        System.out.println(commonTest.commonMethod(1, 2));
    }
}
