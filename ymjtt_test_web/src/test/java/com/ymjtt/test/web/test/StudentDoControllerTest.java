package com.ymjtt.test.web.test;

import com.ymjtt.test.service.StudentDoService;
import com.ymjtt.test.web.util.OkHttp3Util;
import okhttp3.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * @auther ywx
 * @date 2018/11/6 17:53
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/springmvc.xml"})
public class StudentDoControllerTest {

    private static final String addr = "http://localhost:9001/test_web";

    @Autowired
    private StudentDoService studentDoService;

    @Test
    public void test01() throws IOException {
        Response response = OkHttp3Util.getSyn(addr + "/listStudentDo");
        System.out.println(response);
        System.out.println(response.body().string());
    }

    @Test
    public void test02() throws Exception {
        studentDoService.method03();
    }
}
