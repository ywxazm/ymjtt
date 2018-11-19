package com.ymjtt.common.util.file.test;

import com.ymjtt.common.util.file.FileUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * @auther ywx
 * @date 2018/11/16 9:14
 * @info
 **/
public class FileUtilsTest {

    @Test
    public void test01() throws IOException {
        String s = FileUtils.read("C:\\Users\\ywx_azm\\Desktop\\新建文本文档.txt", 100);
        System.out.println(s);
    }
}
