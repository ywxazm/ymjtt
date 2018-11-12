package com.ymjtt.test.web.test;

import com.ymjtt.test.web.util.OkHttp3Util;
import okhttp3.Response;
import org.junit.Test;

import java.io.IOException;

/**
 * @auther ywx
 * @date 2018/11/6 17:53
 **/
public class StudentDoControllerTest {

    private static final String addr = "http://localhost:9001/test_web";

    @Test
    public void test01() throws IOException {
        Response response = OkHttp3Util.getSyn(addr + "/listStudentDo");
        System.out.println(response);
        System.out.println(response.body().string());
    }
}
