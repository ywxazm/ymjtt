package com.ywx.ymjtt.common.test;

import org.springframework.stereotype.Component;

@Component
public class CommonTest {

    public Integer commonMethod(int i, int j) {
        return i * 10 + j;
    }

}
