package com.ymjtt.common.util.data.test;

import com.ymjtt.common.util.date.DateUtils;
import org.junit.Test;

/**
 * @auther ywx
 * @date 2019/1/23 21:35
 **/
public class DataTest {

    @Test
    public void test01() {
        System.out.println(DateUtils.LocalDateTime2String(DateUtils.getCurrentDateTime(), DateUtils.DATE_NOFUll_FORMAT));
    }
}
