package com.ymjtt.common.util.file.test;

import com.ymjtt.common.util.file.FileUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * @auther ywx
 * @date 2018/11/16 9:14
 * @info
 **/
public class FileUtilsTest {

    @Test
    public void test01() throws IOException {
        String currentWorkDir = FileUtils.currentWorkDir;
        System.out.println(currentWorkDir);
    }
    @Test
    public void test02() throws IOException {
        String fileName = FileUtils.createFileName("txt");
        System.out.println(fileName);
    }
    @Test
    public void test03() throws IOException {
        String fileExtensionName = FileUtils.getExtensionName("C:\\Users\\ywx_azm\\Desktop\\erp.war");
        System.out.println(fileExtensionName);
    }
    @Test
    public void test04() throws IOException {
        String context = FileUtils.read("C:\\Users\\ywx_azm\\Desktop\\新建文本文档.txt");
        System.out.println(context);
    }
    @Test
    public void test05() throws IOException {
        List<String> list = new FileUtils().splitBySize("C:\\Users\\ywx_azm\\Desktop\\新建文本文档.txt", 100);
        list.stream().forEach(System.out::println);
    }
}
