
package com.ymjtt.test.xcopy.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther ywx
 * @date 2019/2/20 17:47
 **/
public class XCopy {

    @Test
    public void test01() {
        List<Integer> list = new ArrayList<>();
        list.add(100);
        list.add(110);
        list.add(120);
        list.add(130);

        for (int i = 0; i < list.size(); i++) {
            list.add(i);

            if (list.get(i) % 2 == 0) {
                list.remove(i);
            }
        }

        list.stream().forEach(System.out::println);

    }

}
