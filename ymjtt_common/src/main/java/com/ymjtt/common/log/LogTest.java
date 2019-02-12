package com.ymjtt.common.log;

import org.springframework.stereotype.Component;

/**
 * @auther ywx
 * @date 2019/1/10 15:38
 **/
@Component
public class LogTest {

    @MethodLog
    public void log(String s) {
        System.out.println("abcdefg" + s);
    }
}
