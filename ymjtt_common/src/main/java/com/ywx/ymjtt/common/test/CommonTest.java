package com.ywx.ymjtt.common.test;

import org.springframework.stereotype.Component;

@Component              //@Component并不体现MVC层级，只是用于被<context:component-scan>进行包扫描
public class CommonTest {

    public Integer commonMethod(int i, int j) {
        return i * 10 + j;
    }

}
