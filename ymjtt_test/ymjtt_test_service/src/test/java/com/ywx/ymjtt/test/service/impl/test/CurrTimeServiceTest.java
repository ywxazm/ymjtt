package com.ywx.ymjtt.test.service.impl.test;

import com.ywx.ymjtt.test.service.CurrTimeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext_*.xml"})
public class CurrTimeServiceTest {

    @Autowired
    private CurrTimeService currTimeService;

    @Test
    public void test01() {
        System.out.println("current time is --------> " + currTimeService.getCurrTime());
    }

}
