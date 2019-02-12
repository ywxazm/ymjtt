package com.ymjtt.common.util.fastdfs.test;

import com.ymjtt.common.util.fastdfs.FastDFSUtil;
import com.ymjtt.common.util.file.FileUtils;
import org.csource.common.MyException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author  ywx
 * @date    2018/12/18 16:59
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/*.xml"})
public class FastDFSUtilTest {

    @Autowired
    private FastDFSUtil fastDFSUtil;

    @Test
    public void remove() throws IOException, MyException {
        String filePath = "http://192.168.190.129/group1/M00/00/04/wKi-gVxPvkyAA0GVAACgIuTmy1Q953.jpg";
        boolean result = fastDFSUtil.remove(filePath);
        System.out.println(result);
    }

    @Test
    public void save() throws IOException, MyException {
        String filePath = "C:\\Users\\ywx_azm\\Desktop\\H+\\static\\img\\webuploader.png";
        Map<String, String> map = new HashMap<>();
        map.put("a", "aaaaa");
        map.put("b", "bbbbb");
        map.put("c", "ccccc");
        String path = fastDFSUtil.save(filePath, map);
        System.out.println(path);
    }
}
