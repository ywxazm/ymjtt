package com.ymjtt.common.fastdfs.test;

import com.ymjtt.common.fastdfs.FastDFSUtil;
import org.csource.common.MyException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashMap;

/**
 * @auther ywx
 * @date 2019/3/7 16:56
 **/
public class FastDFSTest {

    @Autowired
    private FastDFSUtil fastDFSUtil;

    @Test
    public void test01() throws IOException, MyException {
        String path = fastDFSUtil.save("C:\\Users\\ywx_azm\\Desktop\\StringOper.java", new HashMap<>());
        System.out.print(path);
    }
}
