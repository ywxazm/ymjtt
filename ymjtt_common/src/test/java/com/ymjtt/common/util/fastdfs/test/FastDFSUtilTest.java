package com.ymjtt.common.util.fastdfs.test;

import com.ymjtt.common.util.fastdfs.FastDFSUtil;
import org.csource.common.MyException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
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
        String filePath = "http://192.168.190.129:80/group1/M00/00/03/wKi-gVwYOJeAQjuDAAA-JXnuIsw374.jpg";
        Map<String, String> map = fastDFSUtil.remove(filePath);
    }
}
