package com.ymjtt.test.web.util.test;

import com.ymjtt.test.web.util.FastDFSUtil;
import org.csource.fastdfs.FileInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

/**
 * @auther ywx
 * @date 2018/11/12 9:26
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:spring/applicationContext_*.xml"})
public class FastDFSUtilTest {

    @Autowired
    private FastDFSUtil fastDFSUtil;

    @Test
    public void fastDFSDown() throws Exception {
        fastDFSUtil.setDownPath("D:/");
        fastDFSUtil.fastDFSDown("M00/00/00/wKi-gVvkTNWAH7GDAIzU2E27KwY452.pdf");
    }

    @Test
    public void fastDFSGet()throws Exception {
        FileInfo fileInfo = fastDFSUtil.fastDFSGetFileInfo("M00/00/00/wKi-gVvkTNWAH7GDAIzU2E27KwY452.pdf");
        System.out.println(fileInfo.toString());
    }

    @Test
    public void fastDFSGetFileMate()throws Exception {
        Map<String, String> map = fastDFSUtil.fastDFSGetFileMate("M00/00/00/wKi-gVvkTNWAH7GDAIzU2E27KwY452.pdf");
        if (null == map || map.size() == 0) {
            System.out.println("map is null");
        }else {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        }
    }

    @Test
    public void fastDFSRemove()throws Exception {
        int result = fastDFSUtil.fastDFSRemove("M00/00/00/wKi-gVvkTNWAH7GDAIzU2E27KwY452.pdf");
        System.out.println(result);
    }
}
